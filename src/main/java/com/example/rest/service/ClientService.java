package com.example.rest.service;

import com.example.rest.controller.dto.ClientDto;
import com.example.rest.model.Client;
import com.example.rest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                encodedPassword,clientDto.getUserName());

        System.out.println(client);
        clientRepository.save(client);

    }




    Client findFirstBySurnameAndPassword(String login, String password) {
        int id = 0;


            List<Client> list = clientRepository.findAll();
            list.stream().filter(c -> c.getSurname().equals(login)
                    && c.getPassword().equals(password)).findFirst();


        return list.get(0);
    }



    public void logInClient(String login, String password) {
        clientRepository.findFirstBySurnameAndPassword(login
                , password);

    }



    public Integer findIdByPassword(String password){

        List<Client> list = clientRepository.findAll();
        list.stream().filter(client -> client.getPassword().equals(password))
                .map(Client::getId).collect(Collectors.toList());

        return list.get(0).getId();

    }

    public Client getByName(String name){
        return clientRepository.getByName(name);
    }





}
