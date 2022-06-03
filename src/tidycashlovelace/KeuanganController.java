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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amal
 */
public class KeuanganController implements Initializable {

    XYChart.Series dataKas = new XYChart.Series<>();

    private xstream<ArrayList<Transaksi>> dataIncomeXml;
    private ArrayList<Transaksi> incomeArray;
    private final XYChart.Series<String, Integer> dataChartIncome = new XYChart.Series<>();

    private xstream<ArrayList<Transaksi>> dataExpenseXml;
    private ArrayList<Transaksi> expenseArray;
    private final XYChart.Series<String, Integer> dataChartExpense = new XYChart.Series<>();

    private Stage stage;

    @FXML
    private BarChart chartIncome;

    @FXML
    private BarChart chartExpense;

    @FXML
    private LineChart chartKas;

    @FXML
    private void handleRefreshAction(ActionEvent event) throws IOException {
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
    private void handleBtnDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent sceneDashboard = loader.load();

        DashboardController sceneDashboardController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(sceneDashboard));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataKas.getData().add(new XYChart.Data("Februari", 200));
        dataKas.getData().add(new XYChart.Data("Maret", 300));
        dataKas.getData().add(new XYChart.Data("April", 300));
        dataKas.getData().add(new XYChart.Data("Mei", 650));
        dataKas.getData().add(new XYChart.Data("Juni", 400));
        dataKas.getData().add(new XYChart.Data("Juli", 425));

//      Mengisi chart Income
        incomeArray = new ArrayList<>();
        dataIncomeXml = new xstream("data_income", incomeArray);
        incomeArray = dataIncomeXml.loadXml();

        for (int i = 0; i < incomeArray.size(); i++) {
            dataChartIncome.getData().add(new XYChart.Data(incomeArray.get(i).getTanggal(), Integer.valueOf(incomeArray.get(i).getTotal())));
        }

//      Mengisi chart Expense
        expenseArray = new ArrayList<>();
        dataExpenseXml = new xstream("data_expense", expenseArray);
        expenseArray = dataExpenseXml.loadXml();

        for (int i = 0; i < expenseArray.size(); i++) {
            dataChartExpense.getData().add(new XYChart.Data(expenseArray.get(i).getTanggal(), Integer.valueOf(expenseArray.get(i).getTotal())));
        }

        chartKas.getData()
                .addAll(dataKas);
        chartIncome.getData()
                .addAll(dataChartIncome);
        chartExpense.getData()
                .addAll(dataChartExpense);

    }

}
