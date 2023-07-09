package Controller1;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SellControllar {
     private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button back;

    @FXML
     public void back(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/View/afterLogin.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
    }

}