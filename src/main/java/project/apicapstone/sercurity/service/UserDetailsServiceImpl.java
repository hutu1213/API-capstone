package project.apicapstone.sercurity.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Role;
import project.apicapstone.repository.AccountRepository;
import project.apicapstone.sercurity.dto.UserDetailsDto;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountRepository repository;
    //private String status;

    public UserDetailsServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Optional<Account> account = repository.findByUsernameAndStatus(username,"active");
        //Account acc = repository.findByUsername(username);
        Account account = repository.findByUsernameAndStatus(username, "ACTIVE");


//        if (account == null) {
//            //throw new UsernameNotFoundException("Username is not existed.");
//            throw new UsernameNotFoundException("Unknown username : " + username);
//        }

//        if(acc.getStatus().equals("closed")){
//            //throw new BaseException("");
//            throw new UsernameNotFoundException("not active");
//
//        }

//        if(!account.isPresent())
//            throw new UsernameNotFoundException("Username is not existed.");

        Set<GrantedAuthority> authorities = getAuthorities(account.getRoles());
        return new UserDetailsDto(username, account.getPassword(), authorities);
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }


}
