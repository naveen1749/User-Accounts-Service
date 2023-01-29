package UserAccounts.userAccounts.Service;


import UserAccounts.userAccounts.EventPublisher.CutomEventPulisher;
import UserAccounts.userAccounts.Exceptions.UserException;
import UserAccounts.userAccounts.Model.AdminReq;
import UserAccounts.userAccounts.Responses.Response;
import UserAccounts.userAccounts.Model.Status;
import UserAccounts.userAccounts.Model.User;
import UserAccounts.userAccounts.Model.UserReq;
import UserAccounts.userAccounts.Repository.JpaRepo;
import UserAccounts.userAccounts.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

@Service
public class AdminService {

    @Autowired
    JpaRepo REPO;
    @Autowired
    CutomEventPulisher customEventPublisher;

    public Response getAll(){
        return new Response<>(Status.OK,REPO.findAll());
    }


    public boolean checkUser(String username, String email) {
        boolean exists = REPO.existsByUserNameIgnoreCaseAndEMAILIgnoreCaseAllIgnoreCase(
                username
                , email);
        return exists;
    }

    private Response addUser(AdminReq user) throws UserException {
        if (user == null) {
            throwException(getThrowable("please provide a valid Details",
                    "add User", 36),Status.CREATE_FAILURE);
        }
        boolean check = checkUser(user.getUserName(), user.getEMAIL());
        if (check) {
            throwException(getThrowable("user already Exists", "add", 36),Status.CREATE_FAILURE);
        }
        User newUser = new User(user.getNAME(),
                user.getUserName(),
                user.getPASSWORD(),
                user.getEMAIL(),
                user.getPHNO(),
                Role.ADMIN);

        User u = REPO.save(newUser);
        if (u == null) {
            return new Response(Status.CREATE_FAILURE,u);
        }
        publishEvent("USER SAVED : "+u.getUserName(),Level.INFO);
        return new Response(Status.CREATED,u);
    }

    private  void publishEvent(String userSaves, Level info) {
        customEventPublisher.publishEvent(userSaves,info);
    }

    public User getUser(String userName) throws Exception {
        boolean oldUser = false;
        if (userName.contains("@")) {
            oldUser = checkUser(null, userName);

        } else {
            oldUser = checkUser(userName, null);
        }
        if (!oldUser) {
            throwException(getThrowable("user not found", "getUser", 65),Status.NOTFOUND);
            return null;
        }
        publishEvent("USER ACCESSED "+userName,Level.INFO);
        return userName.contains("@") ?
                REPO.findByEMAILIgnoreCase(userName) :
                REPO.findByUserName(userName);
    }

    private Throwable getThrowable(String msg,
                                   String methodname,
                                   int linenum) {
        Throwable cause = new Throwable(msg);
        StackTraceElement stackTraceElement =
                new StackTraceElement(
                        this.getClass().getName(),
                        methodname,
                        this.getClass().getModule().getName(),
                        linenum);
        cause.setStackTrace(new StackTraceElement[]{stackTraceElement});
        return cause;
    }

    private void throwException(Throwable throwable,Status status) throws UserException {
        publishEvent(throwable.getMessage(),Level.WARNING);
        throw new UserException(throwable,status);

    }

    public Response add(AdminReq user) throws UserException {
        return addUser(user);
    }

    public Response delete(String name) throws UserException {
        boolean check = false;
        if (name.contains("@")) {
            check = checkUser(null, name);
        } else {
            check = checkUser(name, null);
        }
        if (!check) throwException(getThrowable("user not Availble",
                "delete", 107),Status.NOTFOUND);
        int result = REPO.deleteByUserName(name);
        if (!(result > 0)) {
            throwException(getThrowable("something went wrong ", "delete", 103),Status.DELETE_FAILURE);
        }
        publishEvent("USER DELETED",Level.INFO);
        return new Response(Status.DELETED,name);

    }

    public Response update(String name, UserReq user) throws UserException {
        if (name.isEmpty() || user == null ||
                checkUser(user.getUserName(),
                        user.getEMAIL())) {
            throwException(getThrowable("please provide a valid details",
                    "update", 119),Status.INVALID_DETAILS);
        }
        boolean check = false;
        if (name.contains("@")) {
            check = checkUser(null, name);
        } else {
            check = checkUser(name, null);
        }
        if (!check) {
            throwException(getThrowable("user not found to update",
                    "update", 126),Status.NOTFOUND);
        }

        int result = REPO.updateNAMEAndPASSWORDAndEMAILAndPHNOByUserName(
                user.getNAME(),
                user.getPASSWORD(), user.getEMAIL(),
                user.getPHNO(), name
        );
        publishEvent("USER UPDATED : "+name,Level.INFO);
        return new Response(Status.UPDATED,user);

    }

}
