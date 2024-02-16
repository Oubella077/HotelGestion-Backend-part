package com.example.reservation.services.servicesImp;

import com.example.reservation.DTOs.HotelDTO;
import com.example.reservation.Mappers.MapperImp;
import com.example.reservation.Models.Hotel;
import com.example.reservation.repositories.HotelRepo;
import com.example.reservation.services.Hotelservice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelserviceImp implements Hotelservice {
    HotelRepo hotelRepo;
    MapperImp mapperImp;
    @Override
       public List<HotelDTO> Getliste() {
        List<Hotel> hotels=hotelRepo.findAll();
            List<HotelDTO> hotelDTOS=hotels.stream().map(h->mapperImp.fromHotel(h)).collect(Collectors.toList());
        return hotelDTOS ;
        }
    @Override
       public Hotel Addhotel(HotelDTO hotelDTO){
       Hotel hotel= mapperImp.fromHotelDTO(hotelDTO);
       Hotel hotel1=new Hotel();
        hotel1.setHotelname(hotelDTO.getHotelname());
        hotel1.setPrice(hotel.getPrice());
        hotel1.setAdress(hotel.getAdress());
            return hotelRepo.save(hotel);
        }
    @Override
       public void Deletehotel(Long id){
            hotelRepo.deleteById(id);
        }
    @Override
     public HotelDTO Editehotel(Long id,HotelDTO hotelDTO) {
     Hotel hotel= mapperImp.fromHotelDTO(hotelDTO);
        Hotel hotel1=hotelRepo.findById(id).get();
      hotel1.setHotelname(hotel.getHotelname());
      hotel1.setPrice(hotel.getPrice());
      hotel1.setAdress(hotel.getAdress());
        hotelRepo.save(hotel1);
     return hotelDTO;
    }
    }



