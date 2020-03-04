package com.example.rest.service;

import com.example.rest.model.Item;
import com.example.rest.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item findById(int id){
        return itemRepository.findById(id).orElse(null);
    }



    public List<Item> getAllByMark(String mark){
        List<Item> list = itemRepository.findAll();
        list.stream().filter(item -> item.getMark().equals(mark)).collect(Collectors.toList());

        return list;
    }

    public Integer getIDByName(String name){
        List<Integer> collect = itemRepository.findAll().stream().filter(item -> item.getName().equals(name))
                .map(Item::getId)
                .collect(Collectors.toList());

        return collect.get(0);
    }


}
