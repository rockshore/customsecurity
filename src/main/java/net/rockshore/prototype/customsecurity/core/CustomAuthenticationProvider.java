package net.rockshore.prototype.customsecurity.core;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neildunlop on 29/01/15.
 * Custom Spring Security Authentication provider that would delegate the authentication of users to the REPP User Manager component.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public static String ADMIN_USERNAME = "admin";
    public static String ADMIN_PASSWORD = "password";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        //this fakey code would make a call to user manager to do the authentication and fill up the granted
        //authorities of the supplied user.
        if (name.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
