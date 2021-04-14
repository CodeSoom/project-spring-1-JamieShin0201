package com.solebysole.authentication;

import com.solebysole.user.domain.Role;
import com.solebysole.user.domain.User;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 회원 인증.
 */
@Getter
public class UserAuthentication extends AbstractAuthenticationToken {

    private final User user;

    public UserAuthentication(User user, Role... roles) {
        super(authorities(roles));
        this.user = user;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    private static List<GrantedAuthority> authorities(Role... roles) {
        return Arrays.stream(roles)
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

}
