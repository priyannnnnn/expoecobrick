package Controller1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ExchanegControlar implements Initializable {
    @FXML
    private Button back;

    @FXML
    private Label label;

    private float total;

  @FXML
public void exchainge1(ActionEvent event) {
    if (total >= 70) {
        total -= 70;
        label.setText("Your Points: " + total);
        System.out.println(total);
        saveTotalToXML(total); // Save the updated total to XML
    } else {
        System.out.println("xxxx");
    }
}


    @FXML
    public void initialize() {
        double totalPoints = StoreList.getInstance().points();
        label.setText("Your Points: " + totalPoints);
        total = (float) totalPoints;
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/afterLogin.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
    }

    private Collection<Integer> loadStoreDataFromXML() {
        Collection<Integer> prices = new ArrayList<>();

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
                    int price = Integer.parseInt(storeElement.getElementsByTagName("price").item(0).getTextContent());
                    prices.add(price);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prices;
    }

  @Override
public void initialize(URL location, ResourceBundle resources) {
    Collection<Integer> prices = loadStoreDataFromXML();

    List<Integer> priceList = new ArrayList<>(prices);

    for (int price : priceList) {
        total += price;
    }

    total = total / 5000;
    System.out.println(total);

    // Load the total from XML if it exists
    File xmlFile = new File("src/Model/Total.xml");
    if (xmlFile.exists()) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList totalList = doc.getElementsByTagName("total");
            if (totalList.getLength() > 0) {
                Element totalElement = (Element) totalList.item(0);
                String totalText = totalElement.getTextContent().trim();
                if (!totalText.isEmpty()) {
                    total = (float) Double.parseDouble(totalText);
                    label.setText("Your Points: " + total);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Convert the total to a string
    String totalString = Double.toString(total);

    // Set the total in the label
    label.setText("Your Points: " + totalString);
}


private void saveTotalToXML(double total) {
    try {
        File xmlFile = new File("src/Model/Total.xml");
        File parentDir = xmlFile.getParentFile();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc;

        if (xmlFile.exists()) {
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
        } else {
            doc = dBuilder.newDocument();
            doc.appendChild(doc.createElement("root"));
        }

        Element totalElement = doc.createElement("total");
        totalElement.setTextContent(String.valueOf(total));
        doc.getDocumentElement().appendChild(totalElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
    } catch (Exception e) {
        e.printStackTrace();
    }
}




}
