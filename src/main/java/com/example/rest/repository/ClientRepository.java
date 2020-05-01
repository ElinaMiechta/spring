package com.example.rest.repository;

import com.example.rest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    List<Client> findAll();
    Client findClientByEmail(String email);
    Client getByName(String name);
    Client findClientByToken(String token);

    @Modifying
    @Query("update Client  c set c.active = true where c.token=:token")
    void activateAccount(@Param("token") String token);

    @Modifying
    @Query("update Client  c set c.active = false where c.loginDate<:loginDate")
    void deactivateAccount(@Param("loginDate")LocalDate loginDate);

    @Modifying
    @Query("update Client c set c.token =:token where c.email=:email")
    void saveNewToken(@Param("token") String token, @Param("email") String email);

    @Modifying
    @Query("update Client  c set c.loginDate=current_date where c.email=:email")
    void updateLoginDate(@Param("email") String email);


}
