package UserAccounts.userAccounts.Exceptions;

import UserAccounts.userAccounts.Model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
public class UserException extends Exception{

    @Getter
    String msg;
    Status status;
    public UserException(Throwable obj, Status status){

        super( obj);
        this.msg= obj.getMessage();
        this.status=status;
    }


    public String getStatus() {
        return status.getMesssage();
    }
}
