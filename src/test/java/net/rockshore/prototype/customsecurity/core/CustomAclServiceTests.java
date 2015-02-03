package net.rockshore.prototype.customsecurity.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Tests to confirm the CustomAclService works as expected.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring-security-xml"})
public class CustomAclServiceTests {

//    @Test
//    public void canCreatePermissionToReadSpecificObject() {
//        ObjectIdentity oi = new ObjectIdentityImpl(Foo.class, new Long(44));
//        Sid sid = new PrincipalSid("Samantha");
//        Permission p = BasePermission.ADMINISTRATION;
//
//        // Create or update the relevant ACL
//        MutableAcl acl = null;
//        try {
//            acl = (MutableAcl) aclService.readAclById(oi);
//        } catch (NotFoundException nfe) {
//            acl = aclService.createAcl(oi);
//        }
//
//        // Now grant some permissions via an access control entry (ACE)
//        acl.insertAce(acl.getEntries().length, p, sid, true);
//        aclService.updateAcl(acl);
//    }
}
