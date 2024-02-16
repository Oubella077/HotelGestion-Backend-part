package com.example.reservation.repositories;

import com.example.reservation.Security.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser,String> {
    AppUser findByFullname(String name);
}
