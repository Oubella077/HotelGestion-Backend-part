package com.example.reservation.services.servicesImp;

import com.example.reservation.Security.Entity.AppRole;
import com.example.reservation.Security.Entity.AppUser;
import com.example.reservation.repositories.RoleRepo;
import com.example.reservation.repositories.UserRepo;
import com.example.reservation.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public AppUser Adduser(AppUser appUser) {
     AppUser appUser1=userRepo.findByFullname(appUser.getFullname());
     if(appUser1!=null)
         throw new RuntimeException("User Already Exist ! ");
     //if(!appUser1.getPassword().equals(appUser.getPassword()))
       //  throw new RuntimeException("Password Not Correct");
     AppRole appRole=roleRepo.findById("USER").get();
     List<AppRole> roles=new ArrayList<>();
     roles.add(appRole);
     appUser1=appUser1.builder()
             .Iduser(UUID.randomUUID().toString())
             .Adress(appUser.getAdress())
             .Date_Depart(appUser.getDate_Depart())
             .Date_Arrive(appUser.getDate_Arrive())
             .Adress(appUser.getAdress())
             .fullname(appUser.getFullname())
             .password(passwordEncoder.encode(appUser.getPassword()))
             .build();
        return userRepo.save(appUser);
    }

    @Override
    public AppRole AddRole(String Role) {
        AppRole appRole=roleRepo.findById(Role).orElse(null);
        if (appRole!=null)
            throw new RuntimeException("Role Already Exist !");
       appRole=appRole.builder()
               .Role(Role).build();
        return roleRepo.save(appRole);
    }
    @Override
    public AppUser AddRoleToUser(String name, String Role) {
        AppUser appUser=userRepo.findByFullname(name);
        AppRole appRole=roleRepo.findById(Role).get();
        if(appRole == null)
            throw new RuntimeException("Role Does not Exist");
        appUser.getRoles().add(appRole);
        return userRepo.save(appUser);

    }

    @Override
    public AppUser loadUserByUsername(String name) {
        AppUser appUser=userRepo.findByFullname(name);
        return appUser;
    }
}
