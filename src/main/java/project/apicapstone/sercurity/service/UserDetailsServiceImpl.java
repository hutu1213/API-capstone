package project.apicapstone.sercurity.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.apicapstone.common.util.ResourceBadRequestException;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Role;
import project.apicapstone.repository.AccountRepository;
import project.apicapstone.sercurity.dto.UserDetailsDto;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountRepository repository;
    private final String STATUS = "ACTIVE";

    public UserDetailsServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsernameAndStatus(username, STATUS);
//      Account account = repository.findByUsername(username);
        if (account == null) {
            //throw new ResourceBadRequestException("Username is not found");
            throw new UsernameNotFoundException("username is not fount");
        }
        if (!account.getStatus().equals("ACTIVE")) {
            throw new ResourceBadRequestException("Account not active");
        }
        /* N-N
        Optional<Account> account = repository.findByUsernameWithRoles(username);
        Set<GrantedAuthority> authorities = getAuthorities(account.get().getRole());
        if (!account.isPresent()) {
            throw new UsernameNotFoundException("Username is not existed");
        }
        return new UserDetailsDto(username, account.get().getPassword(), authorities);
         N-N */

        Set<GrantedAuthority> authorities = getAuthorities(account.getRole());

        return new UserDetailsDto(username, account.getPassword(), authorities);
    }

    // account - role: N-N
    //    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
//        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        return authorities;
//    }

    private Set<GrantedAuthority> getAuthorities(Role role) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        return authorities;
    }

}
