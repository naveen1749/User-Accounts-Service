package UserAccounts.userAccounts;

import UserAccounts.userAccounts.Model.User;
import UserAccounts.userAccounts.Repository.JpaRepo;
import UserAccounts.userAccounts.Role.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserAccountsApplication implements ApplicationRunner {
	@Autowired
	JpaRepo REPO;
	public static void main(String[] args) {
		SpringApplication.run(UserAccountsApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		REPO.save(new User("root","root","password123","root@gmail.com",1231231231L, Role.USER));
		REPO.save(new User("admin","admin","adminpass123","admin@gmail.com",1231231231L, Role.ADMIN));
		System.out.println("""
				+-----------------------------------------------+");
				|  Server started at http://localhost:8080/	 	|");
				+-----------------------------------------------+");
					+-------------------------------+
					|  		Default Credentials 	|
					|-------------------------------|
					| UserName  | Password  | Role 	|
					|-----------+-----------+-------|
					|   root  	|   root  	| USER 	|
					|-----------+-----------+-------|
					|   admin  	|   admin  	| ADMIN |
					+-------------------------------+
					
				ACTUATOR : http://localhost:8080/actuator	
					
				""");


	}


}
