package net.rockshore.prototype.customsecurity.core;

import net.rockshore.prototype.customsecurity.core.testobjects.TestObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.PrincipalSid;

import static junit.framework.Assert.assertFalse;

/**
 * Created by neildunlop on 30/01/15.
 * Tests to confirm the CustomAclManager performs as expected.
 */
public class CustomAclManagerTests {

    private static final String STANDARD_USER_ROLE_NAME = "user";
    private static final String ADMIN_USER_ROLE_NAME = "admin";
    TestObject testObject = null;

    @Before
    public void setup() {

        TestObject testObject = new TestObject(22, "Test Thing", "/", null, null);
    }

    @Test
    public void standardUserShouldNotHaveReadAccessToAdminOnlyDomainObject() {

        CustomAclManager aclManager = new CustomAclManager();
        boolean isGranted = aclManager.isPermissionGranted(TestObject.class, testObject.getId(), new PrincipalSid(STANDARD_USER_ROLE_NAME), BasePermission.READ);
        assertFalse(isGranted);
    }
}
