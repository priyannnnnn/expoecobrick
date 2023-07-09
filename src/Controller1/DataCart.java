package Controller1;

import javafx.beans.property.SimpleStringProperty;

public class DataCart {
    private SimpleStringProperty Quantity;

    public String getQuantity() {
        return Quantity.get();
    }

    public DataCart() {
        this("0");
    }

    public DataCart(String quantity) {
        this.Quantity = new SimpleStringProperty(quantity);
    }

    public void setQuantity(String quantity) {
        this.Quantity.set(quantity);
    }
}
