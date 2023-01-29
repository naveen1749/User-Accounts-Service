package UserAccounts.userAccounts.Model;

import lombok.Data;

@Data
public class EventBinding<T,V> {
    T msg;
    V type;
}
