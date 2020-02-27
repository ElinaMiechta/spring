package com.example.rest.service;

import com.example.rest.controller.clientDto.ClientDto;
import com.example.rest.model.Client;
import com.example.rest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void saveClientToDB(ClientDto clientDto) {
        String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getPassword());

        Client client = new Client(clientDto.getOrder(), clientDto.getName(),
                clientDto.getSurname(),
                clientDto.getAdress(),
                clientDto.getCity(),
                encodedPassword);

        System.out.println(client);
        clientRepository.save(client);

    }
}
