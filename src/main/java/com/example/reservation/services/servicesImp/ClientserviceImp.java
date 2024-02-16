package com.example.reservation.services.servicesImp;

import com.example.reservation.Models.Client;
import com.example.reservation.repositories.ClientRepo;
import com.example.reservation.services.Clientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientserviceImp implements Clientservice {
    @Autowired
    ClientRepo clientRepo;
    @Override
    public Client addclient(Client client) {
        return clientRepo.save(client) ;
    }
}
