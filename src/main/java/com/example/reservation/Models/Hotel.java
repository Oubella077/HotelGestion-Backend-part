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
public class Hotel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    @NonNull
    private String Hotelname;
    @NonNull
    private String Price;
    @NonNull
    private String Adress;
    private String Type;
    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private List<AppUser> Clients;
    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
  private List<Reservation> reservationList;
}
