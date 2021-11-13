package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Employee;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
//    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.roles WHERE a.username = ?1")
//    Optional<Account> findByUsernameWithRoles(String username);

    Account findByUsername(String username);

    int countByUsername(String username);

    Account findByUsernameAndStatus(String username, String status);
}
