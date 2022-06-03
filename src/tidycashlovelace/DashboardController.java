/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tidycashlovelace;

import data.DataBarang;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amal
 */
public class DashboardController implements Initializable {

    private Stage stage;

    @FXML
    private TableView<DataBarang> tvStok;

    @FXML
    private TableColumn<DataBarang, String> tcStock;

    @FXML
    private TableColumn<DataBarang, String> tcNama;

    @FXML
    private void handleBtnKeuangan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Keuangan.fxml"));
        Parent sceneKeuangan = loader.load();

        KeuanganController sceneKeuanganController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneKeuangan));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleRefreshAction(ActionEvent event) throws IOException {
    }

    @FXML
    private void handleBtnTransaksi(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Transaksi.fxml"));
        Parent sceneTransaksi = loader.load();

        TransaksiController sceneTransaksiController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneTransaksi));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleBtnStokBarang(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StokBarang.fxml"));
        Parent sceneStokBarang = loader.load();

        StokBarangController sceneStokBaranController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneStokBarang));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
}
