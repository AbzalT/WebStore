package models;

import java.util.UUID;

/**
 * Created by user01 on 17.05.2017.
 */
public class ProductBean {
    private UUID id;
    private String name; //Название товара
    private String description; //Описание товара
    private String imageAddress; // путь к картинке
    private int price;

    public ProductBean(UUID id, String name, String description, String imageAddress, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageAddress = imageAddress;
        this.price = price;
    }
    public ProductBean(String name, String description, String imageAddress, int price) {
        id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.imageAddress = imageAddress;
        this.price = price;
    }
    public ProductBean() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
