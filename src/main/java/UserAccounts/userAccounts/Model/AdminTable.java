package UserAccounts.userAccounts.Model;

import UserAccounts.userAccounts.Role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="admin")
@Setter
public class AdminTable {

    String NAME;
    @Id
    String userName;
    String PASSWORD;
    String EMAIL;
    Long PHNO;
    Role ROLE;

    public AdminTable() {
    }

    public AdminTable(String NAME,
                String USERNAME,
                String PASSWORD,
                String EMAIL,
                Long PHNO,
                Role ROLE) {
        this.NAME = NAME;
        this.userName = USERNAME;
        this.PASSWORD = PASSWORD;
        this.EMAIL = EMAIL;
        this.PHNO = PHNO;
        this.ROLE = ROLE;
    }

    @Override
    public String toString() {
        return "User{" +
                ", NAME='" + NAME + '\'' +
                ", userName='" + userName + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", PHNO=" + PHNO +
                ", ROLE=" + ROLE +
                '}';
    }
}
