package Controller1;




import Model.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;

public class SignUp {

@FXML
private TableView<User> userTable;
    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPhoneNumber;

    @FXML
    private TextField tfmail;
      @FXML
    private TextField tfAddres;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Button regButton;


    public void register(ActionEvent Event) throws IOException{
        String name = tfName.getText();
        String addres = tfAddres.getText();

       
        String phoneNumber = tfPhoneNumber.getText();
         String mail = tfmail.getText();
         String password = pfPassword.getText();
       

        User user = new  User( name,  addres,  phoneNumber,  mail,  password);

        storeUserInXML(user);

        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) regButton.getScene().getWindow();
        stage.setScene(scene);

    }

    private void storeUserInXML(User user){
        try{
            File xmlFile = new File("src/Model/Users.xml");
            File parentDir = xmlFile.getParentFile();


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            if(xmlFile.exists()){
                doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();
            }
         else {
             doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("users");
                doc.appendChild(rootElement);
        }

        
        Element userElement = doc.createElement("user");

        Element nameElement = doc.createElement("name");
        nameElement.setTextContent(user.getName());
        userElement.appendChild(nameElement);

        
        Element emailElement = doc.createElement("email");
        emailElement.setTextContent(user.getMail());
        userElement.appendChild(emailElement);

        Element phoneNumberElement = doc.createElement("phoneNumber");
        phoneNumberElement.setTextContent(user.getPhoneNumber());
        userElement.appendChild(phoneNumberElement);

       
        Element passwordElement = doc.createElement("password");
        passwordElement.setTextContent(user.getPassword());
        userElement.appendChild(passwordElement);

        Element addresElement = doc.createElement("addres");
        addresElement.setTextContent(user.getAddres());
        userElement.appendChild(addresElement);

        
        doc.getDocumentElement().appendChild(userElement);

        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);

        

        System.out.println("User registered and stored in XML successfully.");
    }catch(Exception e){
        e.printStackTrace();
    }
    } 


    private List<User> retrieveUsersFromXML() {
    List<User> users = new ArrayList<>();

    try {
        File xmlFile = new File("src/Model/Users.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("user");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String email = element.getElementsByTagName("email").item(0).getTextContent();
                String phoneNumber = element.getElementsByTagName("phoneNumber").item(0).getTextContent();
                String address = element.getElementsByTagName("addres").item(0).getTextContent();

                User user = new User(name, address, phoneNumber, email, "");
                users.add(user);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return users;
}

    
        
    


    
}
