package com.example.reservation.Models;

import com.example.reservation.Security.Entity.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
        @jakarta.persistence.Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long Id;
        @NonNull
        private String date_reservation;
        @NonNull
        private String date_arrive;
        @NonNull
        private String date_depart;
        @ManyToOne
        @JsonIgnore
        private AppUser appUser;
        @ManyToOne
        private Hotel hotel;
    }
