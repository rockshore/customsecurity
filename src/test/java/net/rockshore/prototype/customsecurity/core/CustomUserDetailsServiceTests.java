package net.rockshore.prototype.customsecurity.core;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by neildunlop on 29/01/15.
 * Tests to confirm that the CustomUserDetailsService allows us to retrieve user information as expected.
 */
public class CustomUserDetailsServiceTests {

    @Test
    public void canRetrieveDetailsForAKnownUser() {

        String validUsername = CustomUserDetailsService.ADMIN_USERNAME;
        String userPassword = CustomUserDetailsService.ADMIN_PASSWORD;
        List<GrantedAuthority> grantedAuthorities = CustomUserDetailsService.ADMIN_GRANTEDAUTHORITIES;

        UserDetailsService userDetailsService = new CustomUserDetailsService();
        UserDetails user = userDetailsService.loadUserByUsername(validUsername);

        assertNotNull(user);
        assertEquals(validUsername, user.getUsername());
        assertEquals(userPassword, user.getPassword());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
        assertNotNull(user.getAuthorities());
        assertEquals(grantedAuthorities.size(), user.getAuthorities().size());
        assertEquals(grantedAuthorities.get(0), user.getAuthorities().toArray()[0]);
    }

    @Test
    public void shouldReturnNullWhenAttempingToRetrieveUnknownUser() {

        String invalidUsername = "invalid";

        UserDetailsService userDetailsService = new CustomUserDetailsService();
        UserDetails user = userDetailsService.loadUserByUsername(invalidUsername);

        assertNull(user);
    }

}
