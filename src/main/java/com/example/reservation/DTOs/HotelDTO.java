package com.example.reservation.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO  {
    private Long Id;
    @NonNull
    private String Hotelname;
    @NonNull
    private String Price;
    @NonNull
    private String Adress;
    private String Type;
    private  Boolean  isFavorite;

}
