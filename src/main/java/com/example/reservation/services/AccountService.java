package com.example.reservation.services;

import com.example.reservation.Security.Entity.AppRole;
import com.example.reservation.Security.Entity.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    AppUser Adduser(AppUser appUser);
    AppRole AddRole(String appRole);
    AppUser AddRoleToUser(String name,String Role);
    AppUser loadUserByUsername(String name);
}
