package UserAccounts.userAccounts.UserController;

import UserAccounts.userAccounts.Exceptions.UserException;
import UserAccounts.userAccounts.Responses.Response;
import UserAccounts.userAccounts.Model.User;
import UserAccounts.userAccounts.Model.UserReq;
import UserAccounts.userAccounts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class Controller {
    @Autowired
    UserService SERVICE;

        @GetMapping("/{userName}")
        public User get(@PathVariable("userName") String userName) throws Exception {
            return  SERVICE.getUser(userName);
        }


        @PostMapping("/")
        public Response post(@Valid @RequestBody UserReq user) throws UserException {
            return SERVICE.add(user);
        }

        @DeleteMapping("/{userName}")
        public Response dlt(@PathVariable("userName") String name) throws UserException {
            return SERVICE.delete(name);
        }

        @PutMapping("/{userName}")
        public Response update(
                @PathVariable("userName") String name,
                @RequestBody UserReq user) throws UserException {
            return SERVICE.update(name,user);
        }

    }


