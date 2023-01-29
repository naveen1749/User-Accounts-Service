package UserAccounts.userAccounts.Events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.logging.Level;

@Getter
public class UserActivity<T> extends ApplicationEvent {

    public UserActivity(T source) {
        super(source);

    }
}
