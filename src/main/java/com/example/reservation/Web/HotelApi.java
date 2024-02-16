package com.example.reservation.Web;

import com.example.reservation.DTOs.HotelDTO;
import com.example.reservation.Models.Hotel;
import com.example.reservation.services.Hotelservice;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/hotels")
public class HotelApi {
    @Autowired
    Hotelservice hotelservice;
    @GetMapping("/liste")
    public ResponseEntity<List<HotelDTO>> GetHotelliste() {
        return new ResponseEntity<>(hotelservice.Getliste(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Hotel> AddHotel(@RequestBody HotelDTO hotel) {
      return new ResponseEntity<>(hotelservice.Addhotel(hotel), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{idhotel}")
    public void AddHotel(@PathVariable Long idhotel) {
         this.hotelservice.Deletehotel(idhotel);
    }
    @PutMapping("/edite/{id}")
    public ResponseEntity<HotelDTO> AddHotel(@RequestBody HotelDTO hotel,@PathVariable Long id){
        return new ResponseEntity<>(hotelservice.Editehotel(id , hotel), HttpStatus.OK);
    }


}
