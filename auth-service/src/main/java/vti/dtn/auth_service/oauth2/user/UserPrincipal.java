package vti.dtn.auth_service.oauth2.user;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import vti.dtn.auth_service.entity.User;
import vti.dtn.auth_service.oauth2.common.OAuth2Constant;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static vti.dtn.auth_service.oauth2.common.OAuth2Constant.*;

@Setter
public class UserPrincipal implements OAuth2User, UserDetails {
    private int id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(int id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER));
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return this.username;
    }
}
