package com.example.rest.service;

import com.example.rest.controller.dto.ItemDto;
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

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


    public void saveItemToDB(ItemDto itemDto) {

        Item item = new Item(itemDto.getName(), itemDto.getPrice(), itemDto.getCurrency(),
                itemDto.getMark(), itemDto.getImageUrl(), itemDto.getDescription(), itemDto.getCategory());
        itemRepository.save(item);
    }

    public Item findById(int id) {

        return itemRepository.findById(id).orElseThrow();
    }


    public List<Item> getAllByMark(String mark) {
        List<Item> list = itemRepository.findAll();
        list.stream().filter(item -> item.getMark().equals(mark)).collect(Collectors.toList());

        return list;
    }



    public Item getByName(String name) {
        return itemRepository.findByName(name);

    }

    public List<Item> findByCategory(String category) {
        List<Item> list = itemRepository.findByCategory(category);

        return list;

    }

    public Item getByCategory(String category) {
        Item item = itemRepository.findFirstByCategory(category);

        return item;

    }

    public List<Item> findAllByName(String name){
        List<Item> list = itemRepository.findAllByName(name);

        return list;
    }


}
