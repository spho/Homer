package com.example.christoph.homer;

/**
 * Created by marcel on 10/3/15.
 */
public class Apartment {
    private int id;
    private int price;
    private int traveltime;
    private String title;
    private String comment;
    private String image;
    private String address;


    public Apartment(int id, int price, int traveltime, String title, String comment, String image, String address) {
        this.id = id;
        this.price = price;
        this.traveltime = traveltime;
        this.title = title;
        this.comment = comment;
        this.image = image;
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(int traveltime) {
        this.traveltime = traveltime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
