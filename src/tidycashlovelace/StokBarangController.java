/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tidycashlovelace;

import data.DataBarang;
import helper.xstream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amal
 */
public class StokBarangController implements Initializable {

    Alert warning = new Alert(Alert.AlertType.WARNING);

    ObservableList<DataBarang> listBarang;
    private xstream<ArrayList<DataBarang>> dataXml;
    private ArrayList<DataBarang> barangArray;

    private int selectedIndex;
    private DataBarang selectedBarang;

    private Stage stage;

    @FXML
    private Pane tambahPane, editPane, editPane1;

    @FXML
    private TextField tfNamaTambahPane;

    @FXML
    private TextField tfKodeTambahPane;

    @FXML
    private TextField tfPenyuplaiTambahPane;

    @FXML
    private TextField tfStokTambahPane;

    @FXML
    private TextField tfHargaTambahPane;

    @FXML
    private TextField tfNama_edit;

    @FXML
    private TextField tfKode_edit;

    @FXML
    private TextField tfPenyuplai_edit;

    @FXML
    private TextField tfStok_edit;

    @FXML
    private TextField tfHarga_edit;

    @FXML
    private TextField tfLineUpdate, tfUbah;

    @FXML
    private TextField tfLineDelete;

    @FXML
    private ChoiceBox cbUbah;

    @FXML
    private TableView<DataBarang> tvStok;

    @FXML
    private TableColumn<DataBarang, String> tcNama;

    @FXML
    private TableColumn<DataBarang, String> tcKode;
    @FXML
    private TableColumn<DataBarang, String> tcPenyuplai;
    @FXML
    private TableColumn<DataBarang, String> tcHarga;

    @FXML
    private TableColumn<DataBarang, String> tcStatus;

    @FXML
    private TableColumn<DataBarang, String> tcStok;

    @FXML
    private void handleBtnTambah(ActionEvent event) throws IOException {
        tambahPane.setVisible(true);
        editPane1.setVisible(false);
    }

    @FXML
    private void handleBtnEdit(ActionEvent event) throws IOException {
        selectedBarang = (DataBarang) tvStok.getSelectionModel().getSelectedItem();
        selectedIndex = tvStok.getSelectionModel().getSelectedIndex();
        if (selectedBarang != null) {
            tvStok.getSelectionModel().select(null);

//          Memunculkan pane Ubah
            editPane1.setVisible(true);

//          Mengambil data dari transaksi yang dipilih
            tfNama_edit.setText(selectedBarang.getNama());
            tfKode_edit.setText(selectedBarang.getKode());
            tfPenyuplai_edit.setText(selectedBarang.getPenyuplai());
            tfHarga_edit.setText(selectedBarang.getHarga().toString());
            tfStok_edit.setText(selectedBarang.getStok().toString());

        } else {
            warning.setContentText("Tidak ada data yang dipilih.");
            warning.showAndWait();
        }
        tambahPane.setVisible(false);
    }

    @FXML
    private void handleBtnTambahTambahPane(ActionEvent event) throws IOException {
        if ((tfNamaTambahPane.getText().equals("") || tfKodeTambahPane.getText().equals("")) || tfStokTambahPane.getText().equals("") || tfPenyuplaiTambahPane.getText().equals("") || tfHargaTambahPane.getText().equals("")) {
            warning.setContentText("Masih ada kolom kosong, nih!");
            warning.showAndWait();
        } else {
            String nama = tfNamaTambahPane.getText();
            String kode = tfKodeTambahPane.getText();
            String penyuplai = tfPenyuplaiTambahPane.getText();
            String stok = tfStokTambahPane.getText();
            String harga = tfHargaTambahPane.getText();
            String status = "";
            int stok2 = Integer.parseInt(stok);
            int harga2 = Integer.parseInt(harga);

            if (stok2 > 10) {
                status = "Tersedia";
            } else if (stok2 > 0) {
                status = "Stok menipis";
            } else {
                status = "Kosong";
            }

            barangArray.add(new DataBarang(nama, kode, penyuplai, stok2, harga2, status));
            dataXml.saveToXML(barangArray);

            listBarang = observableArrayList(barangArray);
            tvStok.setItems(listBarang);

            clearForm();

            tambahPane.setVisible(false);
        }

    }

    @FXML
    private void editBtnAction(ActionEvent event) throws IOException {
        ObservableList<DataBarang> allBarangUbah;
        allBarangUbah = tvStok.getItems();

        if ((tfNama_edit.getText().equals("") || tfKode_edit.getText().equals("")) || tfStok_edit.getText().equals("") || tfPenyuplai_edit.getText().equals("") || tfHarga_edit.getText().equals("")) {
            warning.setContentText("Masih ada kolom kosong, nih!");
            warning.showAndWait();
        } else {
            try {
//              set data di transaksi yang dipilih
                selectedBarang.setNama(tfNama_edit.getText());
                selectedBarang.setKode(tfKode_edit.getText());
                selectedBarang.setPenyuplai(tfPenyuplai_edit.getText());
                selectedBarang.setHarga(Integer.valueOf(tfHarga_edit.getText()));
                selectedBarang.setStok(Integer.valueOf(tfStok_edit.getText()));

                allBarangUbah.set(selectedIndex, selectedBarang);

//              buat data baru untuk bagian ubah
                ArrayList<DataBarang> newBarangUbah = new ArrayList<>(allBarangUbah);

//              save data ubah ke xml
                dataXml.saveToXML(newBarangUbah);
                listBarang = observableArrayList(newBarangUbah);
                tvStok.setItems(listBarang);

                clearForm();

                editPane1.setVisible(false);

            } catch (NumberFormatException e) {
                warning.setContentText("Jumlah modal harus angka");
                warning.showAndWait();
            }
        }

    }

    @FXML
    private void handleBtnHapusAction(ActionEvent event) throws IOException {
        ObservableList<DataBarang> selectedBarang, allBarang;
        allBarang = tvStok.getItems();
        selectedBarang = tvStok.getSelectionModel().getSelectedItems();

        if (selectedBarang.size() > 0) {
            selectedBarang.forEach(allBarang::remove);
            tvStok.getSelectionModel().select(null);
        } else {
            warning.setContentText("Tidak ada data yang dipilih.");
            warning.showAndWait();
        }

        ArrayList<DataBarang> newBarang = new ArrayList<>(allBarang);
        dataXml.saveToXML(newBarang);

        System.out.println("Button Hapus IS Clicked");
    }

    @FXML
    private void handleBtnBatalTambahPane(ActionEvent event) throws IOException {
        tambahPane.setVisible(false);
    }

    @FXML
    private void handleBtnBatalEditPane(ActionEvent event) throws IOException {
        editPane1.setVisible(false);
    }

    @FXML
    private void handleBtnDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent sceneDashboard = loader.load();

        DashboardController sceneDashboardController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneDashboard));
        stage.setResizable(false);
        stage.show();
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
    private void handleBtnKeuangan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Keuangan.fxml"));
        Parent sceneKeuangan = loader.load();

        KeuanganController sceneKeuanganController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneKeuangan));
        stage.setResizable(false);
        stage.show();
    }

    private void clearForm() {
        tfNamaTambahPane.setText("");
        tfKodeTambahPane.setText("");
        tfPenyuplaiTambahPane.setText("");
        tfHargaTambahPane.setText("");
        tfStokTambahPane.setText("");
        tfNama_edit.setText("");
        tfKode_edit.setText("");
        tfPenyuplai_edit.setText("");
        tfHarga_edit.setText("");
        tfStok_edit.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tcNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        tcKode.setCellValueFactory(new PropertyValueFactory<>("kode"));
        tcPenyuplai.setCellValueFactory(new PropertyValueFactory<>("penyuplai"));
        tcStok.setCellValueFactory(new PropertyValueFactory<>("stok"));
        tcHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        cbUbah.getItems().addAll("nama", "kode", "penyuplai", "stok", "harga");

        barangArray = new ArrayList<>();
        dataXml = new xstream("data_barang", barangArray);
        barangArray = dataXml.loadXml();
        listBarang = observableArrayList(barangArray);

        tvStok.setItems(listBarang);

        tambahPane.setVisible(false);
        editPane1.setVisible(false);
        editPane.setVisible(false);
    }

}
