package Controller1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AfterLogin {
  @FXML
  private Button go;

  @FXML
  private Button profile;
  @FXML
  private Button buy;
  @FXML
  private Button sell;
  @FXML
  private Button exchange;
  // @FXML
  // private TextField user;

  @FXML
  private Label user;

  private Stage stage;
  private Scene scene;
  private Parent root;

  public void setUserName(String userName) {
    user.setText("Welcome, " + userName);
  }

  @FXML
  void profile(ActionEvent event) {

  }

  public void CityCart(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/View/citysCart.fxml"));
    Scene scene = new Scene(root);

    Stage stage = (Stage) go.getScene().getWindow();
    stage.setScene(scene);
  }

  public void buy(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/View/BuyEcobric.fxml"));
    Scene scene = new Scene(root);

    Stage stage = (Stage) buy.getScene().getWindow();
    stage.setScene(scene);
  }

  public void sell(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/View/SellPage.fxml"));
    Scene scene = new Scene(root);

    Stage stage = (Stage) sell.getScene().getWindow();
    stage.setScene(scene);
  }

  public void exchange(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/View/Exchanege.fxml"));
    Scene scene = new Scene(root);

    Stage stage = (Stage) exchange.getScene().getWindow();
    stage.setScene(scene);
  }

}
