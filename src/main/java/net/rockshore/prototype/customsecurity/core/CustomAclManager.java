package net.rockshore.prototype.customsecurity.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Logger;


/**
 * Created by neildunlop on 30/01/15.
 * Simple class that manages Access Control Lists for users and domain objects.
 *
 * Need to find where this is used in Spring.  Suspect that its the two autowired services that we really need
 * to override.
 */
public class CustomAclManager {

    private final static Logger LOGGER = Logger.getLogger(CustomAclManager.class.getName());

    //Think we will need custom version of this to make sure that ACL's are written the the UM API and not a DB
    @Autowired
    private MutableAclService aclService;

    //This this is redundant... we dont want to write to the DB, we want to write to UM API
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Add a permission for the given object.
     *
     * @param clazz Domain class
     * @param identifier Id of the object the given domain
     * @param sid Security Identifier of the person, role or group that wants access, could be a {@link org.springframework.security.acls.domain.PrincipalSid} or a {@link org.springframework.security.acls.domain.GrantedAuthoritySid}
     * @param permission The permission desired by the SID on the based on {@link org.springframework.security.acls.domain.BasePermission}
     */
    public <T> void addPermission(Class<T> clazz, Serializable identifier, Sid sid, Permission permission) {

        ObjectIdentity identity = new ObjectIdentityImpl(clazz, identifier);
        MutableAcl acl = findOrCreateAcl(identity);

        //assume this is a quick way of check the permission exists??  Will throw an exception if it doesn't exist?
        isPermissionGranted(permission, sid, acl);

        //this is essentially our 'flush to storage' method.
        aclService.updateAcl(acl);
    }


    /**
     * Remove a permission from the given object
     *
     * @param clazz Domain class that you want to remove the permission for.
     * @param identifier Id from the given domain you want to remove the permission for.
     * @param sid Security Identifier of the person, group or role that you want to remove the permission for, could be a {@link org.springframework.security.acls.domain.PrincipalSid} or a {@link org.springframework.security.acls.domain.GrantedAuthoritySid}
     * @param permission The permission you want to remove, based on {@link org.springframework.security.acls.domain.BasePermission}
     */
    public <T> void removePermission(Class<T> clazz, Serializable identifier, Sid sid, Permission permission) {

        ObjectIdentity identity = new ObjectIdentityImpl(clazz.getCanonicalName(), identifier);
        MutableAcl acl = (MutableAcl) aclService.readAclById(identity);

        AccessControlEntry[] entries = acl.getEntries().toArray(new AccessControlEntry[acl.getEntries().size()]);

        for (int i = 0; i < acl.getEntries().size(); i++) {
            if (entries[i].getSid().equals(sid) && entries[i].getPermission().equals(permission)) {
                acl.deleteAce(i);
            }
        }

        aclService.updateAcl(acl);
    }


    /**
     * Check whether the given object has permission
     *
     * @param clazz Domain class
     * @param identifier Id from the given domain
         * @param sid Security Identifier, could be a {@link org.springframework.security.acls.domain.PrincipalSid} or a {@link org.springframework.security.acls.domain.GrantedAuthoritySid}
     * @param permission The permission based on {@link org.springframework.security.acls.domain.BasePermission}
     * @return true or false
     */
    public <T> boolean isPermissionGranted(Class<T> clazz, Serializable identifier, Sid sid, Permission permission) {

        ObjectIdentity identity = new ObjectIdentityImpl(clazz.getCanonicalName(), identifier);
        MutableAcl acl = (MutableAcl) aclService.readAclById(identity);
        boolean isGranted = false;

        try {
            isGranted = acl.isGranted(Arrays.asList(permission), Arrays.asList(sid), false);
        } catch (NotFoundException e) {
            //log.info("Unable to find an ACE for the given object", e);
        } catch (UnloadedSidException e) {
            //log.error("Unloaded Sid", e);
        }

        return isGranted;
    }

    private void isPermissionGranted(Permission permission, Sid sid, MutableAcl acl) {
        try {
            //arguments are permissions to check, sid's to check against and 'administrative mode'
            acl.isGranted(Arrays.asList(permission), Arrays.asList(sid), false);
        } catch (NotFoundException e) {
            acl.insertAce(acl.getEntries().size(), permission, sid, true);
        }
    }

    /**
     * Finds an ACL using the supplied ID or creates a new ACL with that ID if it could not be found.
     * @param identity Id of the ACL to find or create.
     * @return The found or created ACL.
     */
    private MutableAcl findOrCreateAcl(ObjectIdentity identity) {

        MutableAcl acl;

        try {
            acl = (MutableAcl) aclService.readAclById(identity);
        } catch (NotFoundException e) {
            acl = aclService.createAcl(identity);
        }
        return acl;
    }

    public void deleteAllGrantedAcl() {

        //why is this not done through the ACL service?
//        jdbcTemplate.update("delete from acl_entry");
//        jdbcTemplate.update("delete from acl_object_identity");
//        jdbcTemplate.update("delete from acl_sid");
//        jdbcTemplate.update("delete from acl_class");

    }



}
