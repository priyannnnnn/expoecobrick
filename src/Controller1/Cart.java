package Controller1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Cart implements Initializable {
    private TableViewSelectionModel<Store> selectionModel;
    private Store selectedStore;
    StoreList data1;
    private File xmlFile;

    @FXML
    private Label myLabel;
    @FXML
    private ComboBox<?> address1;
   
    @FXML
    private TextField myQantity1;
    @FXML
    private TextField myQantity2;
    @FXML
    private TextField myQantity3;
    @FXML
    private Label storeNameLabel1;
    @FXML
    private Label storeNameLabel2;
    @FXML
    private Label myPrice1;
    @FXML
    private Button saveButton;
    @FXML
    private Button yrCart;
    @FXML
    private Button save2;
    @FXML
    private Button save3;
    @FXML
    private Button back;
    @FXML
    private Button deleButton;
    @FXML
    private TextField editQ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      

        // Load data from XML
        ObservableList<Store> storeData = loadStoreDataFromXML();
        data1 = StoreList.getInstance();
        data1.setData(storeData);

        
    }
     @FXML
    void address1(ActionEvent event) {

    }

    private ObservableList<Store> loadStoreDataFromXML() {
        ObservableList<Store> storeData = FXCollections.observableArrayList();

        try {
            xmlFile = new File("src/Model/StoreTable.xml");
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

        return storeData;
    }

    private void saveDataToXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Create root element
            Element rootElement = doc.createElement("storeData");
            doc.appendChild(rootElement);

            // Add store elements to root
            for (Store store : data1.getData()) {
                Element storeElement = doc.createElement("store");
                rootElement.appendChild(storeElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(store.getName()));
                storeElement.appendChild(nameElement);

                Element quantityElement = doc.createElement("quantity");
                quantityElement.appendChild(doc.createTextNode(Integer.toString(store.getQuantity())));
                storeElement.appendChild(quantityElement);

                Element priceElement = doc.createElement("price");
                priceElement.appendChild(doc.createTextNode(Integer.toString(store.getPrice())));
                storeElement.appendChild(priceElement);
            }

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void save1(ActionEvent event) {
        String name = "Brick Master";
        String q = myQantity1.getText();
        int q2 = Integer.parseInt(q);
        int finalPrice = q2 * 5500;

        if (selectedStore != null) {
            selectedStore.setName(name);
            selectedStore.setQuantity(q2);
            selectedStore.setPrice(finalPrice);
            selectedStore = null;
        } else {
            Store store = new Store(name, q2, finalPrice);
            data1.getData().add(store);
            myQantity1.clear();
        }

        saveDataToXML();
    }

    @FXML
    private void save2(ActionEvent event) {
        String name = "Brick Mart";
        String q = myQantity2.getText();
        int q2 = Integer.parseInt(q);
        int finalPrice = q2 * 7000;

        if (selectedStore != null) {
            selectedStore.setName(name);
            selectedStore.setQuantity(q2);
            selectedStore.setPrice(finalPrice);
            selectedStore = null;
        } else {
            Store store = new Store(name, q2, finalPrice);
            data1.getData().add(store);
            myQantity2.clear();
        }

        saveDataToXML();
    }

    @FXML
    private void save3(ActionEvent event) {
        String name = "Brick Extra";
        String q = myQantity3.getText();
        int q2 = Integer.parseInt(q);
        int finalPrice = q2 * 10000;

        if (selectedStore != null) {
            selectedStore.setName(name);
            selectedStore.setQuantity(q2);
            selectedStore.setPrice(finalPrice);
            selectedStore = null;
        } else {
            Store store = new Store(name, q2, finalPrice);
            data1.getData().add(store);
            myQantity3.clear();
        }

        saveDataToXML();
    }

    @FXML
    void GotoCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Table.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) yrCart.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void remove(ActionEvent event) {
        Store selectedStore = selectionModel.getSelectedItem();
        if (selectedStore != null) {
            data1.getData().remove(selectedStore);
            saveDataToXML();
        }
    }

   


    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/BuyEcobric.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
    }
}
