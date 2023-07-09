package Controller1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Store {
    private String name;
    private int quantity;
    private int price;
     private int previousPrice;
    private int priceChange;

     // Rest of the code

    public int getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(int previousPrice) {
        this.previousPrice = previousPrice;
    }

    public int getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(int priceChange) {
        this.priceChange = priceChange;
    }

    
    

    public Store(int price) {
        this.price = price;
    }

    public Store(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price =  price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }}

  

  