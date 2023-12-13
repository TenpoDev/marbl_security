package com.marbl.security.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.marbl.security.entity.user.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_CREATE,
            ADMIN_DELETE,
            PARTNER_READ,
            PARTNER_UPDATE,
            PARTNER_CREATE,
            PARTNER_DELETE,
            USER_READ,
            USER_UPDATE,
            USER_CREATE,
            USER_DELETE
    )),
    PARTNER(Set.of(
            PARTNER_READ,
            PARTNER_UPDATE,
            PARTNER_CREATE,
            PARTNER_DELETE,
            USER_READ,
            USER_UPDATE,
            USER_CREATE,
            USER_DELETE
    )),
    USER(Set.of(
            USER_READ,
            USER_UPDATE,
            USER_CREATE,
            USER_DELETE
    ));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toCollection(ArrayList::new));

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}
