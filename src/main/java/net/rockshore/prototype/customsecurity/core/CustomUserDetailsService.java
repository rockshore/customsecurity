package net.rockshore.prototype.customsecurity.core;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neildunlop on 29/01/15.
 * Provides Spring with a way of getting to user information stored in User Manager.  Spring does not explicitly need
 * this class but it is a convenient way of encapsulating a lot of common user information.
 * Spring calls this this User Details Service.. but should we call it the UserDetailsProvider just for consistentcy
 * with our custom Authentication Provider?  (Spring has the inconsistency in naming).
 */
public class CustomUserDetailsService implements UserDetailsService {


    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "password";
    public static List<GrantedAuthority> ADMIN_GRANTEDAUTHORITIES = new ArrayList<GrantedAuthority>() {{
        add(new SimpleGrantedAuthority("ROLE_USER"));
    }};

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //clearly this would be replaced by a call to UM....
        if(username.equals(ADMIN_USERNAME)) {
            UserDetails user = new User(ADMIN_USERNAME, ADMIN_PASSWORD, true, true, true, true, ADMIN_GRANTEDAUTHORITIES);
            return user;
        }
        return null;
    }
}
