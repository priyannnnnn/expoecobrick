package Controller1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExtendedStoreList extends StoreList {
    private static ExtendedStoreList instance;

    // Private constructor to prevent direct instantiation
    private ExtendedStoreList() {
        super();
        // Additional initialization if needed
    }

    // Static method to obtain the singleton instance
    public static ExtendedStoreList getInstance() {
        if (instance == null) {
            instance = new ExtendedStoreList();
        }
        return instance;
    }

    // Additional methods specific to the ExtendedStoreList class
}

