package UserAccounts.userAccounts.SecurityConfig;

import UserAccounts.userAccounts.Model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final User user;

    CustomUserDetails(User user){
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getROLE().getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPASSWORD();
    }

    @Override
    public String getUsername() {
        return user.getNAME();
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
