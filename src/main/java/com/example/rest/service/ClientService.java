package com.example.rest.service;

import com.example.rest.controller.dto.ClientDto;
import com.example.rest.model.Client;
import com.example.rest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private MailService mailService;


    @Autowired
    public ClientService(ClientRepository clientRepository, MailService mailService) {
        this.clientRepository = clientRepository;
        this.mailService = mailService;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void saveClientToDB(ClientDto clientDto) {
        String encodedPassword = new BCryptPasswordEncoder().encode(clientDto.getPassword());
        String token = UUID.randomUUID().toString();

        Client client = new Client(clientDto.getOrder(), clientDto.getName(),
                clientDto.getSurname(),
                clientDto.getAdress(),
                clientDto.getCity(),
                encodedPassword, clientDto.getEmail(), false, "USER", token);
        clientRepository.save(client);
        sendToken(client);

    }


    public void logInClient(String email) {
        clientRepository.findClientByEmail(email);

    }

    private void sendToken(@Valid Client client) {
        String token = client.getToken();
        String url = "http://localhost:8080" + "/" + token;
        mailService.sentMail(client.getEmail(), "Account activation", url, true);

    }

    @Transactional
    public void activateAccount(String token) {
        clientRepository.activateAccount(token);
    }


    public Client findClientbyToken(String token) {
        return clientRepository.findClientByToken(token);
    }

    public Client findClientbyEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    @Transactional
    public void deactivateClient(LocalDate loginDate) {
        clientRepository.deactivateAccount(loginDate);
    }

    @Transactional
    public void resendTokenToActivateAccount(String token, String email) {
        clientRepository.saveNewToken(token, email);
        Client client = findClientbyToken(token);
        sendToken(client);
    }

    @Transactional
    public void updateLoginDate(String email){
        Client client = findClientbyEmail(email);
        clientRepository.updateLoginDate(email);
    }
}
