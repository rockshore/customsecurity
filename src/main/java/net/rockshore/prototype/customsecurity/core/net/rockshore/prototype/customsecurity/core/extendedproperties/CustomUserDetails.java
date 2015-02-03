package net.rockshore.prototype.customsecurity.core.net.rockshore.prototype.customsecurity.core.extendedproperties;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by neildunlop on 02/02/15.
 */
public class CustomUserDetails implements UserDetails {


    ExtendedProperties extendedProperties;

    public ExtendedProperties getExtendedProperties() {
        return extendedProperties;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public CustomUserDetails(ExtendedProperties extendedProperties) {
        this.extendedProperties = extendedProperties;
    }
}
