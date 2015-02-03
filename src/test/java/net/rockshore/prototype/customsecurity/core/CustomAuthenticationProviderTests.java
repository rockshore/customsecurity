package net.rockshore.prototype.customsecurity.core;

import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by neildunlop on 29/01/15.
 * Tests to confirm the custom authentication provider works as expected.
 */
public class CustomAuthenticationProviderTests {

    @Test
    public void shouldReturnPopulatedAuthenticationObjectWhenValidCredentialsAreSupplied() {

        String validUsername = CustomAuthenticationProvider.ADMIN_USERNAME;
        String validPassword = CustomAuthenticationProvider.ADMIN_PASSWORD;

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Authentication testAuthenticationToken = new UsernamePasswordAuthenticationToken(validUsername, validPassword, authorities);


        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        Authentication authenticationResult = provider.authenticate(testAuthenticationToken);

        assertNotNull(authenticationResult);
        assertNotNull(authenticationResult.getAuthorities());
        assertEquals(1, authenticationResult.getAuthorities().size());
        assertTrue((authenticationResult.isAuthenticated()));

    }

    @Test
    public void shouldReturnNullWhenInvalidCredentialsAreSupplied() {

        String invalidUsername = "invalid";
        String invalidPassword = "invalid";
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        Authentication testAuthenticationToken = new UsernamePasswordAuthenticationToken(invalidUsername, invalidPassword, authorities);

        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        Authentication authenticationResult = provider.authenticate(testAuthenticationToken);

        assertNull(authenticationResult);
    }
}
