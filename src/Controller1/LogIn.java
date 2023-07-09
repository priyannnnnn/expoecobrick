package Controller1;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LogIn implements Initializable{

     private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Button loginButton;
        @FXML
    private Button SignUp;

    @FXML
private void login() throws IOException {
    String email = tfEmail.getText();
    String password = pfPassword.getText();
    if (isValidLogin(email, password)) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/afterLogin.fxml"));
        Parent root = loader.load();
        AfterLogin afterLoginController = loader.getController();
        afterLoginController.setUserName(getUserNameFromXML(email)); // Pass the user's name
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(scene);
    } else {
        System.out.println("User not found");
    }
}

    private boolean isValidLogin(String email, String password) {
    try {
        File xmlFile = new File("src/Model/Users.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbFactory.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("user"); // Retrieve user elements, not users
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String storedEmail = element.getElementsByTagName("email").item(0).getTextContent();
                String storedPassword = element.getElementsByTagName("password").item(0).getTextContent();
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    return true;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

private String getUserNameFromXML(String email) {
    try {
        File xmlFile = new File("src/Model/Users.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbFactory.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("user");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String storedEmail = element.getElementsByTagName("email").item(0).getTextContent();
                String storedName = element.getElementsByTagName("name").item(0).getTextContent();
                if (storedEmail.equals(email)) {
                    return storedName;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


     @FXML
     public void SignUp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/View/SignUp.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) SignUp.getScene().getWindow();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }

}




   
   

    
   




     
 