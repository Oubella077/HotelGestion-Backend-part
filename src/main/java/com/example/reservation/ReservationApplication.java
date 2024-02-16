package com.example.reservation;

import com.example.reservation.DTOs.HotelDTO;
import com.example.reservation.Models.Client;
import com.example.reservation.Security.Entity.AppUser;
import com.example.reservation.services.AccountService;
import com.example.reservation.services.Clientservice;
import com.example.reservation.services.Hotelservice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(Hotelservice hotelservice, Clientservice clientservice) {
		return args -> {
			Stream.of("Ibiss", "Lmourabitine", "Massira").forEach(
					name -> {
						HotelDTO hotelDTO = new HotelDTO();
						hotelDTO.setAdress("el jadida avenue M6");
						hotelDTO.setPrice("88");
						hotelDTO.setHotelname(name);
						hotelservice.Addhotel(hotelDTO);
					});
			Client client=new Client();
			client.setFullname("amin");
			client.setAdress("uuuuu");
			client.setDate_Depart("7777");
			client.setDate_Arrive("9/8/2000");
			client.setPassword("0000");
     clientservice.addclient(client);

};
	}

	@Bean
	CommandLineRunner commandLineRunnerAccountService(AccountService accountService, PasswordEncoder passwordEncoder){
		return args -> {
			accountService.AddRole("ADMIN");
			accountService.AddRole("USER");
			//AppUser appUser=new AppUser(null,"12345","12345","Amine","Amine123@gmail","El jadida 077","098763535",null);
		AppUser appUser= AppUser.builder()
				.Date_Depart("555555")
				.Iduser(UUID.randomUUID().toString())
				.password(passwordEncoder.encode("12345"))
				.fullname("Amine")
				.Adress("El jadida 362 rue 07")
				.Date_Arrive("66666")
				.build();
			AppUser appUser1= AppUser.builder()
					.Date_Depart("555555")
					.Iduser(UUID.randomUUID().toString())
					.password(passwordEncoder.encode("12345"))
					.fullname("Oubella")
					.Adress("El jadida 362 rue 07")
					.Date_Arrive("66666")
					.build();
			accountService.Adduser(appUser);
			accountService.Adduser(appUser1);
			// Add roles t users
			accountService.AddRoleToUser("Amine","USER");
			accountService.AddRoleToUser("Oubella","ADMIN");
		};
	}

}

