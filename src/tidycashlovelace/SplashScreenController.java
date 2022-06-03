/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tidycashlovelace;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amal
 */
public class SplashScreenController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private void handleBtnStartNow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent sceneDashboard = loader.load();

        DashboardController sceneDashboardController = loader.getController();

//        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneDashboard));
        stage.setResizable(false);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
