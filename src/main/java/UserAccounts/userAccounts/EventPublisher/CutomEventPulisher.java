package UserAccounts.userAccounts.EventPublisher;

import UserAccounts.userAccounts.Events.UserActivity;
import UserAccounts.userAccounts.Model.EventBinding;
import UserAccounts.userAccounts.Model.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CutomEventPulisher  {


    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(String  req, Level type) {

        EventBinding<String,Level> msg=new EventBinding();
        msg.setMsg(req);
        msg.setType(type);

        applicationEventPublisher.publishEvent(new UserActivity<>(msg));
    }
}
