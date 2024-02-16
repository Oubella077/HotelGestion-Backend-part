package com.example.reservation.Web;

import com.example.reservation.DTOs.ReservationDTO;
import com.example.reservation.Models.Hotel;
import com.example.reservation.Models.Reservation;
import com.example.reservation.repositories.ReservationRepo;
import com.example.reservation.services.Hotelservice;
import com.example.reservation.services.Reservationservice;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/reservation")
public class ReservationApi {
    @Autowired
    Reservationservice reservationservice;
    @GetMapping("/liste")
    public ResponseEntity<List<ReservationDTO>> listReservations() {
        return new ResponseEntity<>(reservationservice.Getliste(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Reservation> AddHotel(@RequestBody ReservationDTO Re){
        return new ResponseEntity<>(reservationservice.AddReservation(Re), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{idhotel}")
    public void deleteHotel(@PathVariable Long idhotel) {
        this.reservationservice.DeleteReservation(idhotel);}
    @PutMapping("/edite/{idhotel}")
    public ResponseEntity<ReservationDTO> Edite(@PathVariable Long idhotel,@RequestBody ReservationDTO Re) throws BadRequestException {
        return new ResponseEntity<>(reservationservice.EditReservation(idhotel , Re), HttpStatus.OK);
    }
}
