/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tidycashlovelace;

import data.Transaksi;
import helper.xstream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amal
 */
public class TransaksiController implements Initializable {

    Alert warning = new Alert(Alert.AlertType.WARNING);
    ObservableList<Transaksi> listdata;
    private xstream<ArrayList<Transaksi>> dataXml;
    private ArrayList<Transaksi> transaksiArray;

    private int selectedIndex;
    private Transaksi selectedTransaksi;

    private Stage stage;

    @FXML
    private Pane paneUbah;

    @FXML
    private Pane paneUbah1;

    @FXML
    private Pane paneTambah;

    @FXML
    private TableView<Transaksi> tvTrns;

    @FXML
    private TableColumn<?, ?> tcNama;

    @FXML
    private TableColumn<?, ?> tcTanggal;

    @FXML
    private TableColumn<?, ?> tcJumlahSatuan;

    @FXML
    private TableColumn<?, ?> tcHarga;

    @FXML
    private TableColumn<?, ?> tcTotalRp;

    @FXML
    private TextField tfNamaTransaksi, tfJumlah, tfHarga;

    @FXML
    private DatePicker dateTanggal;

    @FXML
    private TextField tfNamaTransaksi_ubah;

    @FXML
    private TextField tfJumlah_ubah;

    @FXML
    private TextField tfHarga_ubah;

    @FXML
    private DatePicker dateTanggal_ubah;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnUbah;

    @FXML
    private void handleTableClick(MouseEvent event) {
        btnHapus.setDisable(false);
        btnUbah.setDisable(false);
    }

    @FXML
    private void handleBtnExpenses(ActionEvent event) throws IOException {
//      Load Halaman Expenses
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TransaksiExp.fxml"));
        Parent sceneTransaksiExp = loader.load();

        TransaksiExpController sceneTransaksiExpController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneTransaksiExp));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleBtnStokBarang(ActionEvent event) throws IOException {
//      Load Halaman Stok Barang
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StokBarang.fxml"));
        Parent sceneStokBarang = loader.load();

        StokBarangController sceneStokBaranController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneStokBarang));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleBtnDashboard(ActionEvent event) throws IOException {
//        Load Halaman Dasbor
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent sceneDashboard = loader.load();

        DashboardController sceneDashboardController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneDashboard));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleBtnKeuangan(ActionEvent event) throws IOException {
//        Load Halaman Keuangan
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
    private void paneTambahBtnSimpanAction(ActionEvent event) {
        if ((tfNamaTransaksi.getText().equals("") || dateTanggal.getValue().toString().equals("")) || tfHarga.getText().equals("") || tfJumlah.getText().equals("")) {
            warning.setContentText("Masih ada kolom kosong, nih!");
            warning.showAndWait();
        } else {
            try {
//              Assign value dari TextField ke variabel
                String nama = tfNamaTransaksi.getText();
                String tanggal = dateTanggal.getValue().toString();
                String harga = tfHarga.getText();
                String jumlah = tfJumlah.getText();

//              Ubah variabel menjadi tipe int
                int jumlah2, harga2;
                harga2 = Integer.parseInt(harga);
                jumlah2 = Integer.parseInt(jumlah);
                int total = harga2 * jumlah2;

//              Ubah kembali ke String
                String total2 = String.valueOf(total);

//              setData
                transaksiArray.add(new Transaksi(nama, tanggal, jumlah, harga, total2));
                dataXml.saveToXML(transaksiArray);

                listdata = observableArrayList(transaksiArray);
                tvTrns.setItems(listdata);

                clearForm();
//              Tutup pane Tambah
                paneTambah.setVisible(false);
                paneUbah1.setVisible(false);

                System.out.println("SimpanButton is clicked");
            } catch (NumberFormatException e) {
                warning.setContentText("Jumlah modal harus angka");
                warning.showAndWait();
            }
        }

    }

    @FXML
    private void btnMunculTambahPane(ActionEvent event) throws IOException {
//      Munculkan pane Tambah
        paneTambah.setVisible(true);
        paneUbah1.setVisible(false);
    }

    @FXML
    private void paneUbahBtnSimpanAction(ActionEvent event) {
        ObservableList<Transaksi> allTransaksiUbah;
        allTransaksiUbah = tvTrns.getItems();

        if ((tfNamaTransaksi_ubah.getText().equals("") || dateTanggal_ubah.getValue().toString().equals("")) || tfHarga_ubah.getText().equals("") || tfJumlah_ubah.getText().equals("")) {
            warning.setContentText("Masih ada kolom kosong, nih!");
            warning.showAndWait();
        } else {
            try {
//              set data di transaksi yang dipilih
                selectedTransaksi.setNama(tfNamaTransaksi_ubah.getText());
                selectedTransaksi.setTanggal(dateTanggal_ubah.getValue().toString());
                selectedTransaksi.setHarga(tfHarga_ubah.getText());
                selectedTransaksi.setJumlah(tfJumlah_ubah.getText());

//              Ubah variabel menjadi tipe int
                int jumlah2, harga2;
                harga2 = Integer.parseInt(tfHarga_ubah.getText());
                jumlah2 = Integer.parseInt(tfJumlah_ubah.getText());
                int total = harga2 * jumlah2;

//              Ubah kembali ke String
                String total2 = String.valueOf(total);

                selectedTransaksi.setTotal(total2);

                allTransaksiUbah.set(selectedIndex, selectedTransaksi);

//      buat data baru untuk bagian ubah
                ArrayList<Transaksi> newTransaksiUbah = new ArrayList<>(allTransaksiUbah);

//      save data ubah ke xml
                dataXml.saveToXML(newTransaksiUbah);
                listdata = observableArrayList(newTransaksiUbah);
                tvTrns.setItems(listdata);

                clearForm();

                paneUbah1.setVisible(false);

            } catch (NumberFormatException e) {
                warning.setContentText("Jumlah modal harus angka");
                warning.showAndWait();
            }
        }

    }

    @FXML
    private void btnMunculUbahPane(ActionEvent event) throws IOException {
        selectedTransaksi = (Transaksi) tvTrns.getSelectionModel().getSelectedItem();
        selectedIndex = tvTrns.getSelectionModel().getSelectedIndex();
        if (selectedTransaksi != null) {
            tvTrns.getSelectionModel().select(null);

//          Memunculkan pane Ubah
            paneUbah1.setVisible(true);

//          Mengambil data dari transaksi yang dipilih
            tfNamaTransaksi_ubah.setText(selectedTransaksi.getNama());
            dateTanggal_ubah.setValue(LocalDate.now());
            tfJumlah_ubah.setText(selectedTransaksi.getJumlah());
            tfHarga_ubah.setText(selectedTransaksi.getHarga());

        } else {
            warning.setContentText("Tidak ada data yang dipilih.");
            warning.showAndWait();
        }

    }

    @FXML
    private void btnHapusAction(ActionEvent event) throws IOException {
        ObservableList<Transaksi> selectedTransaksi, allTransaksi;
        allTransaksi = tvTrns.getItems();
        selectedTransaksi = tvTrns.getSelectionModel().getSelectedItems();

        if (selectedTransaksi.size() > 0) {
            selectedTransaksi.forEach(allTransaksi::remove);
            tvTrns.getSelectionModel().select(null);
        } else {
            warning.setContentText("Tidak ada data yang dipilih.");
            warning.showAndWait();
        }

        ArrayList<Transaksi> newTransaksi = new ArrayList<>(allTransaksi);
        dataXml.saveToXML(newTransaksi);
    }

    @FXML
    private void paneTambahBtnBatal(ActionEvent event) throws IOException {
//        Tutup pane Tambah
        paneTambah.setVisible(false);
        paneUbah1.setVisible(false);
    }

    @FXML
    private void paneUbahBtnBatal(ActionEvent event) throws IOException {
//        Tutup pane Ubah
        paneUbah1.setVisible(false);
        paneTambah.setVisible(false);
    }

    private void clearForm() {
        tfNamaTransaksi.setText("");
        dateTanggal.setValue(LocalDate.now());
        tfHarga.setText("");
        tfJumlah.setText("");
        tfNamaTransaksi_ubah.setText("");
        dateTanggal_ubah.setValue(LocalDate.now());
        tfHarga_ubah.setText("");
        tfJumlah_ubah.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcNama.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        tcTanggal.setCellValueFactory(new PropertyValueFactory<>("Tanggal"));
        tcJumlahSatuan.setCellValueFactory(new PropertyValueFactory<>("Jumlah"));
        tcHarga.setCellValueFactory(new PropertyValueFactory<>("Harga"));
        tcTotalRp.setCellValueFactory(new PropertyValueFactory<>("Total"));

//        Menyembunyikan pane Tambah, Ubah, dan Hapus pada tampilan awal
        paneTambah.setVisible(false);
        paneUbah.setVisible(false);
        paneUbah1.setVisible(false);

        transaksiArray = new ArrayList<>();
        dataXml = new xstream("data_income", transaksiArray);
        transaksiArray = dataXml.loadXml();
        listdata = observableArrayList(transaksiArray);

        tvTrns.setItems(listdata);

    }

}
