package com.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_price")
    private int price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "mark")
    private String mark;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private String category;

    public String getCategory() {
        return category;
    }


    public Item() {
    }

    @Column(name = "image_url")
    private String imageUrl;


    public Item(String name, int price, String currency, String mark, String imageUrl, String description, String category) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.mark = mark;
        this.imageUrl = imageUrl;
        this.category=category;
        this.description=description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMark() {
        return mark;
    }

    public String getDescription() {
        return description;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
