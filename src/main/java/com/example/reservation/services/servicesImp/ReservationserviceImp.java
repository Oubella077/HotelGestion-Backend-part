package com.example.reservation.services.servicesImp;

import com.example.reservation.DTOs.ReservationDTO;
import com.example.reservation.Mappers.MapperImp;
import com.example.reservation.Models.Client;
import com.example.reservation.Models.Hotel;
import com.example.reservation.Models.Reservation;
import com.example.reservation.Security.Entity.AppUser;
import com.example.reservation.repositories.HotelRepo;
import com.example.reservation.repositories.ReservationRepo;
import com.example.reservation.repositories.ClientRepo;
import com.example.reservation.repositories.UserRepo;
import com.example.reservation.services.Reservationservice;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationserviceImp implements Reservationservice {
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    HotelRepo hotelRepo;
    @Override
    public List<ReservationDTO> Getliste() {
        List<Reservation> reservationList=reservationRepo.findAll();
        List<ReservationDTO> reservationDTOS=reservationList.stream()
                .map(r->MapperImp.fromReservation(r))
                .collect(Collectors.toList());
        return reservationDTOS;
    }
    @Override
    public Reservation AddReservation(ReservationDTO Re)  {
        AppUser appUser =userRepo.findById(Re.getIduser()).get();
     //try {
         Hotel hotel = hotelRepo.findById(Re.getId_hotel()).get();
        Reservation reservation =MapperImp.fromReservationDto(Re);
        List<AppUser> list=hotel.getClients();
        list.add(appUser);
        hotel.setClients(list);
        reservation.setAppUser(appUser);
        reservation.setHotel(hotel);
        return reservationRepo.save(reservation);
     //} catch (Exception e) {
       //  throw new BadRequestException("error finding ID  ");}
    }

    @Override
    public void DeleteReservation(Long id) {
      reservationRepo.deleteById(id);
    }

    @Override
    public ReservationDTO EditReservation(Long id, ReservationDTO Re) throws BadRequestException {
       AppUser appUser=userRepo.findById(Re.getIduser()).get();
       Hotel hotel = hotelRepo.findById(Re.getId_hotel()).get();
        //Reservation reservation =MapperImp.fromReservationDto(Re);
      //  reservation.setClient(client);
      //  reservation.setHotel(hotel);
        Reservation reservation1=reservationRepo.findById(id).get();
        BeanUtils.copyProperties(Re,reservation1);
        reservation1.setHotel(hotel);
        reservation1.setAppUser(appUser);
        reservationRepo.save(reservation1);
        return Re;
    }
}
