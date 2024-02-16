package com.example.reservation.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String date_reservation;
    @NonNull
    private String date_arrive;
    @NonNull
    private String date_depart;
    private String Iduser;
    private Long Id_hotel;
}
