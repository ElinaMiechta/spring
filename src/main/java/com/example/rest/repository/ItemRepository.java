package com.example.rest.repository;

import com.example.rest.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

   // Optional<Item> getByID(int id);
    List<Item> getAllByMark(String mark);
    List<Item> findAll();



}
