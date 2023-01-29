package UserAccounts.userAccounts.Repository;

import UserAccounts.userAccounts.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public interface JpaRepo extends JpaRepository<User,Integer> {
    @Query("select u from User u where upper(u.EMAIL) = upper(?1)")
    User findByEMAILIgnoreCase(String EMAIL);
    @Query("select (count(u) > 0) from User u where upper(u.userName) = upper(?1) or upper(u.EMAIL) = upper(?2)")
    boolean existsByUserNameIgnoreCaseAndEMAILIgnoreCaseAllIgnoreCase(String userName, String EMAIL);
    @Transactional
    @Modifying
    @Query("delete from User u where u.userName = ?1")
    int deleteByUserName(String userName);
    @Transactional
    @Modifying
    @Query("update User u set u.NAME = ?1, u.PASSWORD = ?2, u.EMAIL = ?3, u.PHNO = ?4 " +
            "where u.userName = ?5 or u.EMAIL = ?5")
    int updateNAMEAndPASSWORDAndEMAILAndPHNOByUserName(String NAME,
                                                                    String PASSWORD,
                                                                    String EMAIL,
                                                                    Long PHNO,
                                                                    String userName1);
    User findByUserName(String username);



}
