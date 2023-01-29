package UserAccounts.userAccounts.Role;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static UserAccounts.userAccounts.Role.Rights.*;

@AllArgsConstructor
@Getter
public enum Role {
    USER(Sets.newHashSet(USER_READ)),
    ADMIN(Sets.newHashSet(USER_WRITE,
            USER_READ,
            ADMIN_READ,
            ADMIN_WRITE));

    private final Set<Rights> rights;

    public java.util.List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auts = new ArrayList<>();
        rights.forEach(e -> {
            auts.add(new SimpleGrantedAuthority(e.getRight()));
        });
        auts.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return auts;
    }
}
