package com.example.reservation.Security.Entity;

import com.example.reservation.Models.Hotel;
import com.example.reservation.Models.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @jakarta.persistence.Id
    private String Iduser;
    @NonNull
    private String fullname;
    @NonNull
    private String password;
    @NonNull
    private String Date_Arrive;
    @NonNull
    private String Date_Depart;
    private String Adress;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> Roles;
    @OneToMany(mappedBy = "appUser")
    private List<Reservation> reservationList;
    @ManyToOne
    private Hotel hotel;
}
