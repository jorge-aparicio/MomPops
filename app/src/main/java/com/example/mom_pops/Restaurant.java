package com.example.mom_pops;

public class Restaurant {
    private String name, food_type, price_range, phone_number, address, miles_away;
    private int image;

    public Restaurant(String name, String food_type, String price_range, String phone_number, String address, String miles_away, int image) {
        this.name = name;
        this.food_type = food_type;
        this.price_range = price_range;
        this.phone_number = phone_number;
        this.address = address;
        this.miles_away = miles_away;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodType() {
        return food_type;
    }

    public void setFoodType(String food_type) {
        this.food_type = food_type;
    }

    public String getPriceRange() {
        return price_range;
    }

    public void setPriceRange(String price_range) {
        this.price_range = price_range;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMilesAway() {
        return miles_away;
    }

    public void setMilesAway(String miles_away) {
        this.miles_away = miles_away;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
