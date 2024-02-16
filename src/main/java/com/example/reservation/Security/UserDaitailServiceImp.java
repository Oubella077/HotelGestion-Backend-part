package com.example.reservation.Security;

import com.example.reservation.Security.Entity.AppUser;
import com.example.reservation.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDaitailServiceImp implements UserDetailsService {
@Autowired
 AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=accountService.loadUserByUsername(username);
        if(appUser == null)
            throw new UsernameNotFoundException(String.format("Username not Exist %s",username));
        String [] roles=appUser.getRoles().stream().map(p->p.getRole()).toArray(String[]::new);
       UserDetails userDetails= User.withUsername(appUser.getFullname())
               .password(appUser.getPassword())
               .authorities(roles)
               .build();
        return userDetails;
    }
}
