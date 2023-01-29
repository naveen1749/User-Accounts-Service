package UserAccounts.userAccounts.Model;

import UserAccounts.userAccounts.Role.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserReq {

    @NotNull(message = "name shouldn't be empty")
    String NAME;
    @NotNull(message = "enter a valid User Name")
    String userName;

    @Pattern(regexp = "^[A-Za-z\\d]{8,}$",
            message = "password must contain 8 char's & a number")
    String PASSWORD;
    @Email(message = "enter a valid email")
    String EMAIL;

    Long PHNO;

    public User toUser() {
        return new User(this.getNAME(), this.getUserName(),
                this.getPASSWORD(), this.getEMAIL(),
                this.getPHNO(), Role.USER);
    }
}
