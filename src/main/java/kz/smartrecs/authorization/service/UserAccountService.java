package kz.smartrecs.authorization.service;

import kz.smartrecs.authorization.entity.UserAccount;
import kz.smartrecs.authorization.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public boolean registerUserAccount(UserAccount userAccount) {
        if (isUserAccountPresent(userAccount)) {
            return userAccountRepository.save(userAccount).getId() > 0;
        } else {
            log.error("Error registering user account. UserAccount with email {} is present in database", userAccount.getEmail());
            throw new IllegalArgumentException("UserAccount with email " + userAccount.getEmail() + " is present in database");
        }
    }

    private boolean isUserAccountPresent(UserAccount userAccount) {
        UserAccount account = userAccountRepository.getUserAccountByEmail(userAccount.getEmail());
        return Objects.isNull(account);
    }
}
