package Controller1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StoreList {
    private static StoreList instance;
    protected ObservableList<Store> data;
    private double totalPoints;

    public StoreList() {
        data = FXCollections.observableArrayList();
    }

    public static StoreList getInstance() {
        if (instance == null) {
            instance = new StoreList();
        }
        return instance;
    }

    public void setData(ObservableList<Store> data) {
        this.data = data;
    }

    public ObservableList<Store> getData() {
        return data;
    }

    public void setData(String name, int quantity, int price) {
        Store newStore = new Store(name, quantity, price);
        data.add(newStore);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Store store : data) {
            totalPrice += store.getPrice() * store.getQuantity();
        }
        return totalPrice;
    }

    public double points() {
        double totalPoints = calculateTotalPrice() / 5000;
        return totalPoints;
    }

    public int calculateTotalQuantity() {
        int totalQuantity = 0;
        for (Store store : data) {
            totalQuantity += store.getQuantity();
        }
        return totalQuantity;
    }

    public int calculateQuantityByName(String name) {
        int totalQuantity = 0;
        try {
            File file = new File("src/Model/StoreTable.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("store");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String storeName = element.getElementsByTagName("name").item(0).getTextContent();
                    int quantity = Integer.parseInt(element.getElementsByTagName("quantity").item(0).getTextContent());

                    if (storeName.equals(name)) {
                        totalQuantity += quantity;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalQuantity;
    }

    public void compareAndCalculateTotals() {
        int brickExtraQuantity = calculateQuantityByName("Brick Extra");
        int brickMasterQuantity = calculateQuantityByName("Brick Master");
        int brickMartQuantity = calculateQuantityByName("Brick Mart");

        System.out.println("Brick Extra Quantity: " + brickExtraQuantity);
        System.out.println("Brick Master Quantity: " + brickMasterQuantity);
        System.out.println("Brick Mart Quantity: " + brickMartQuantity);
    }
}
