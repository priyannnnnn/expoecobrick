package Controller1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StoreTableController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button viewPriducts;
       @FXML
    private Button yrCart;
    @FXML
    private Button changeButton;
    @FXML
    private Button GotoCart;
    @FXML
    private Button edit;
    @FXML
    private TextField editQ;
    @FXML
    private Button removeButton;
    @FXML
    private TableView<Store> tableView;
    @FXML
    private TableColumn<Store, String> nameColumn;
    @FXML
    private TableColumn<Store, Integer> quantityColumn;
    @FXML
    private TableColumn<Store, Integer> priceColumn;

    private Store selectedStore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Store> storeData = loadStoreDataFromXML();
        tableView.setItems(storeData);

        // Set a selection listener for the table view
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedStore = newSelection;
            }
        });
    }

    @FXML
    private void change(ActionEvent event) {
        if (selectedStore != null) {
            String q = editQ.getText();

            try {
                int q2 = Integer.parseInt(q);

                int finalPrice;
                switch (selectedStore.getName()) {
                    case "Brick Master":
                        finalPrice = q2 * 5500;
                        break;
                    case "Brick Mart":
                        finalPrice = q2 * 7000;
                        break;
                    case "Brick Extra":
                        finalPrice = q2 * 10000;
                        break;
                    default:
                        finalPrice = 0;
                        break;
                }

                selectedStore.setQuantity(q2);
                selectedStore.setPrice(finalPrice);
                saveStoreDataToXML(tableView.getItems());
                selectedStore = null;
                tableView.refresh();
                editQ.clear();
            } catch (NumberFormatException e) {
                // Handle invalid input error
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void edit(ActionEvent event) {
        selectedStore = tableView.getSelectionModel().getSelectedItem();
        if (selectedStore != null) {
            editQ.setText(String.valueOf(selectedStore.getQuantity()));
            tableView.refresh();
        }
    }

    @FXML
    private void remove(ActionEvent event) {
        if (selectedStore != null) {
            ObservableList<Store> storeData = tableView.getItems();
            storeData.remove(selectedStore);
            saveStoreDataToXML(storeData);
            selectedStore = null;
            tableView.refresh();
        }
    }

    private ObservableList<Store> loadStoreDataFromXML() {
        ObservableList<Store> storeData = FXCollections.observableArrayList();

        try {
            File xmlFile = new File("src/Model/StoreTable.xml");
            File parentDir = xmlFile.getParentFile();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            if (xmlFile.exists()) {
                doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();
                NodeList storeList = doc.getElementsByTagName("store");

                for (int i = 0; i < storeList.getLength(); i++) {
                    Element storeElement = (Element) storeList.item(i);
                    String name = storeElement.getElementsByTagName("name").item(0).getTextContent();
                    int quantity = Integer.parseInt(storeElement.getElementsByTagName("quantity").item(0).getTextContent());
                    int price = Integer.parseInt(storeElement.getElementsByTagName("price").item(0).getTextContent());

                    Store store = new Store(name, quantity, price);
                    storeData.add(store);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StoreList.getInstance().setData(storeData); // Set the storeData in the StoreList instance

        return storeData;
    }

    private void saveStoreDataToXML(ObservableList<Store> storeData) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create the root element
            Element rootElement = doc.createElement("storeData");
            doc.appendChild(rootElement);

            for (Store store : storeData) {
                Element storeElement = doc.createElement("store");
                rootElement.appendChild(storeElement);

                // Name element
                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(store.getName()));
                storeElement.appendChild(nameElement);

                // Quantity element
                Element quantityElement = doc.createElement("quantity");
                quantityElement.appendChild(doc.createTextNode(String.valueOf(store.getQuantity())));
                storeElement.appendChild(quantityElement);

                // Price element
                Element priceElement = doc.createElement("price");
                priceElement.appendChild(doc.createTextNode(String.valueOf(store.getPrice())));
                storeElement.appendChild(priceElement);
            }

            // Save the updated XML document to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/Model/StoreTable.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GotoCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Cart.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) GotoCart.getScene().getWindow();
        stage.setScene(scene);
    }

       @FXML
   public void viewPriducts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Cart.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) viewPriducts.getScene().getWindow();
        stage.setScene(scene);
    }
}
