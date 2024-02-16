package com.example.reservation.repositories;

import com.example.reservation.Models.Client;
import com.example.reservation.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {
}
