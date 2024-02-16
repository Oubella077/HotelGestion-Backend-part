package com.example.reservation.repositories;

import com.example.reservation.Security.Entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
@Repository
public interface RoleRepo extends JpaRepository<AppRole,String> {
}
