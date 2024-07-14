package kz.smartrecs.config;

import kz.smartrecs.authorization.model.Authority;
import kz.smartrecs.authorization.model.Customer;
import kz.smartrecs.authorization.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationProviderConfiguration implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Customer activeCustomer = customerRepository.findByEmailAndIsActive(userName, true);
        if (Objects.isNull(activeCustomer)) {
            log.warn("Authenticated user {} not found!", userName);
            throw new UsernameNotFoundException("User details not found for the user: " + userName);
        } else {
            if (passwordEncoder.matches(pwd, activeCustomer.getPwd())) {
                return new UsernamePasswordAuthenticationToken(userName, pwd, getGrantedAuthorities(activeCustomer.getAuthorities()));
            } else {
                log.warn("Authenticated user {} input invalid password {}", userName, pwd);
                throw new BadCredentialsException("Invalid password!");
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
