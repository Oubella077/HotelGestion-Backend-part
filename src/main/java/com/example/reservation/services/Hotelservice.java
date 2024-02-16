package com.example.reservation.services;
import com.example.reservation.DTOs.HotelDTO;
import com.example.reservation.Models.Hotel;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface Hotelservice {
    List<HotelDTO> Getliste();
    Hotel Addhotel(HotelDTO hotel);
    void Deletehotel(Long id);
    HotelDTO Editehotel(Long id,HotelDTO hotel) ;

}
