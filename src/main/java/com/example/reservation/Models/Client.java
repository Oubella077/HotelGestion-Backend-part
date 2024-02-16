package com.example.reservation.Models;
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
public class Client {
    @jakarta.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    @NonNull
    private String Fullname;
    @NonNull
    @Column( unique = true)
    private String Password;
    @NonNull
    private String Date_Arrive;
    @NonNull
    private String Date_Depart;
    private String Adress;


}
