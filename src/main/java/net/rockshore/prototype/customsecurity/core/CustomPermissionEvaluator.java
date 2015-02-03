package net.rockshore.prototype.customsecurity.core;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.security.acl.Permission;

/**
 *
 * NOT SURE WE NEED THIS.. THINK WE CAN JUST REPLACE THE ACL SERVICE AND INTERCEPT WHERE ACLS ARE WRITTEN AND READ FROM
 *
 * Created by neildunlop on 29/01/15.
 * Customer Permission Evaluator ensures that REPP User Manager is asked whether the user has the appropriate permissions
 * when trying to evaluate a Spring Security Expression.
 * (From: http://krams915.blogspot.co.uk/2011/01/spring-security-simple-acl-using.html)
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {


    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId, String targetType, Object permission) {
        //logger.debug("Evaluating expression using hasPermission signature #2");

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        boolean hasPermission = false;

        if (authentication != null && permission instanceof String) {

            //implement the permission checking of your application here
            //you can just check if the input permission is within your permission list
            //In my example, the user object contains a HashMap which stored the permission of the user.
            //The HashMap<String, PrivilegeResult> is populated during using login by filter. This will not be shown in this example

//            User user = SecurityUtil.getUserCredential();
//            HashMap<String, PrivilegeResult> pMap = user.getPrivilegeMap();
//            PrivilegeResult privResult = pMap.get(permission);
//            hasPermission = privResult.isAllowAccess();


        }
        else {

            hasPermission = false;

        }

        return hasPermission;
    }


    private Boolean hasPermission(String role, Object permission, Object targetObjectType, Object targetObjectId) {


        //this would obviously be a call to User Manager

//        //logger.debug("Check if role exists: " + role);
//        if (permissionsMap.containsKey(role) ) {
//            //logger.debug("Role exists: " + role);
//
//            // Retrieve userPermission object
//            Permission userPermission = (Permission) permissionsMap.get(role);
//
//            // Check if domain exists in Map
////            logger.debug("Check if domain exists: " + domain.getClass().getName());
//            if ( userPermission.getObjects().containsKey(targetObjectType.getClass().getName())){
////                logger.debug("Domain exists: " + domain.getClass().getName());
//
//                // Loop the internal list and see if the class' full name matches
////                logger.debug("Check if permission exists: " + permission);
//                for (String action: userPermission.getObjects().get(targetObjectType.getClass().getName()) ) {
//                    if (action.equals(permission)) {
////                        logger.debug("Permission exists: " + action);
////                        logger.debug("Permission Granted!");
//                        return true;
//                    }
//                }
//            }
//        }
//
//        // By default, do not give permission
//        //logger.debug("Permission Denied!");
        return false;
    }
}

