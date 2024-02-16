package com.example.reservation.Mappers;

import com.example.reservation.DTOs.HotelDTO;
import com.example.reservation.DTOs.ReservationDTO;
import com.example.reservation.Models.Hotel;
import com.example.reservation.Models.Reservation;
import com.example.reservation.repositories.ReservationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperImp {
    @Autowired
    ReservationRepo reservationRepo;
    public static HotelDTO fromHotel(Hotel hotel) {

        HotelDTO hotelDTO = new HotelDTO();
        BeanUtils.copyProperties(hotel, hotelDTO);
     //   productDTO.setCategoryId(product.getCategorie().getId());
     //   productDTO.setId(product.getId());
        return hotelDTO;
    }
    public static Hotel fromHotelDTO(HotelDTO hotelDTO) {

        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelDTO, hotel);
        hotel.setHotelname(hotelDTO.getHotelname());
        //   productDTO.setCategoryId(product.getCategorie().getId());
        //   productDTO.setId(product.getId());
        return hotel;
    }

    public static ReservationDTO fromReservation(Reservation reservation) {

        ReservationDTO reservationDTO = new ReservationDTO();
        BeanUtils.copyProperties(reservation, reservationDTO);
        reservationDTO.setId_hotel(reservation.getHotel().getId());
        //   productDTO.setCategoryId(product.getCategorie().getId());
        //   productDTO.setId(product.getId());
        return reservationDTO;
    }
    public static Reservation fromReservationDto(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationDTO, reservation);
        //   productDTO.setCategoryId(product.getCategorie().getId());
        //   productDTO.setId(product.getId());
        return reservation;
    }
}
