package kz.smartrecs.authorization.repository;

import kz.smartrecs.authorization.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

    List<UserAccount> findByEmailAndIsActive(String email, Boolean isActive);

    UserAccount getUserAccountByEmail(String email);
}
