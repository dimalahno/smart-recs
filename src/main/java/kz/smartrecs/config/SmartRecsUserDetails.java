package kz.smartrecs.config;

import kz.smartrecs.authorization.entity.UserAccount;
import kz.smartrecs.authorization.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SmartRecsUserDetails implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    /**
     *
     * @param username email
     * @return User details info
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName;
        String password;
        List<GrantedAuthority> authorities;
        List<UserAccount> userAccounts = userAccountRepository.findByEmailAndIsActive(username, true);
        if (userAccounts.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user" + username);
        } else {
            userName = userAccounts.get(0).getEmail();
            password = userAccounts.get(0).getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(userAccounts.get(0).getRole()));
        }
        return new User(userName, password, authorities);
    }
}
