package Controller1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

public class CityController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

    @FXML
    private PieChart pcCar;
    @FXML
    private Button back;
    // @FXML
    // private Button addHandleAction;
    // @FXML
    // private Button substractHandleAction;

//     @FXML
//     private void addHandleAction(ActionEvent event) {
//         try {
//             data.add(new PieChart.Data("June", 30));
//         System.out.println("Add Button is Clicked!");
    
//         } catch (Exception e) {
// System.out.println("fffff");        }
//     }
        

    // @FXML
    // private void substractHandleAction(ActionEvent event) {
    //     try {
    //            data.remove(3);
    //     System.out.println("Substract Button is Clicked!");
    //     } catch (Exception e) {
    //     System.out.println("fffff");   
    //     }
     
    // }

  @Override
public void initialize(URL url, ResourceBundle rb) {
    try {
        StoreList storeList = StoreList.getInstance();
        ObservableList<Store> storeData = storeList.getData();

        int brickExtraQuantity = storeList.calculateQuantityByName("Brick Extra");
        int brickMasterQuantity = storeList.calculateQuantityByName("Brick Master");
        int brickMartQuantity = storeList.calculateQuantityByName("Brick Mart");

        data.add(new PieChart.Data("Brick Extra", brickExtraQuantity));
        data.add(new PieChart.Data("Brick Master", brickMasterQuantity));
        data.add(new PieChart.Data("Brick Mart", brickMartQuantity));

        pcCar.setData(data);
    } catch (Exception e) {
        System.out.println("fffff");
    }
}


    public void Back(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/afterLogin.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("fffff");   
        }
        
    }

}