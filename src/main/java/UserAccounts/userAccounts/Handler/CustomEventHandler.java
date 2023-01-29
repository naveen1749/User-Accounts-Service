package UserAccounts.userAccounts.Handler;

import UserAccounts.userAccounts.Events.UserActivity;
import UserAccounts.userAccounts.Model.EventBinding;
import UserAccounts.userAccounts.Model.EventType;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Component
public class CustomEventHandler {
    Logger log = Logger.getLogger(this.getClass().getName());

    @EventListener
    @Async
    public void handle(UserActivity event){
        EventBinding data=(EventBinding)event.getSource();
            log.log(new LogRecord((Level)data.getType(),
                    (String)data.getMsg()));

    }

}
