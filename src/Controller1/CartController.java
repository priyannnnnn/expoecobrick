// package Controller1;

// import java.io.IOException;
// import java.net.URL;
// import java.util.ResourceBundle;

// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.Initializable;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.TextField;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.text.Text;
// import javafx.stage.Stage;

// public class CartController implements Initializable {
//      private Stage stage;
//     private Scene scene;
//     private Parent root;

//     @FXML
//     private Text Pric2;

//     @FXML
//     private TableView<DataCart> last;

//     @FXML
//     private TextField Quantity1;

//     @FXML
//     private TextField Quantity2;

//     @FXML
//     private TextField Quantity3;

//     @FXML
//     private TextField Quantity4;

//     @FXML
//     private Button button1;

//     @FXML
//     private Button button2;

//     @FXML
//     private Button button3;

//     @FXML
//     private Button button4;

//     @FXML
//     private Button seting;

//     @FXML
//     private Button viPr;

//     @FXML
//     private Button yrCart;

//     @FXML
//     private TableView<DataCart> QuantityTable;

//     @FXML
//     private TableColumn<DataCart, String> quantityColumn;

//     TableList data;



//     @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
//         data = new TableList();
//         QuantityTable.setItems(data.getData());
//     }

//     @FXML
//     void Addf(ActionEvent event) {

//     }

//     @FXML
//     void Addo(ActionEvent event) {
//         String quantity = Quantity1.getText();
//         TableController tableController = (TableController) QuantityTable.getScene().getUserData();
//         tableController.setQuantity(quantity);
//         System.out.println("Quantity updated: " + quantity);
//     }

//     @FXML
//     void Addt(ActionEvent event) {

//     }

//     @FXML
//     void Addth(ActionEvent event) {

//     }
//       @FXML
//    public void moveToTablel(ActionEvent event) throws IOException {
//         root = FXMLLoader.load(getClass().getResource("Table.fxml"));
//         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }
// }
