package com.example.reservation.services;

import com.example.reservation.DTOs.ReservationDTO;
import com.example.reservation.Models.Hotel;
import com.example.reservation.Models.Reservation;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface Reservationservice {
    List<ReservationDTO> Getliste();
    Reservation AddReservation(ReservationDTO Re) ;
    void DeleteReservation(Long id);
    ReservationDTO EditReservation(Long id,ReservationDTO Re) throws BadRequestException;

}
