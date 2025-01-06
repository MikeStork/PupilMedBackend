package com.pupilmed.pupilmedapi.model;

import com.pupilmed.pupilmedapi.service.OwnerService;
import com.pupilmed.pupilmedapi.service.VetService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private User user;
    private OwnerService ownerService;
    private VetService vetService;

    public UserPrincipal(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRola())); // np. "ROLE_ADMIN"
    }

    @Override
    public String getPassword() {
        return user.getHaslo();
    }

    @Override
    public String getUsername() {
        return user.getNumerTelefonu();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
