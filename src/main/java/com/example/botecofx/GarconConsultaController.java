package com.example.botecofx;

import com.example.botecofx.db.dals.GarconDAL;
import com.example.botecofx.db.entidades.Garcon;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GarconConsultaController implements Initializable {

    @FXML
    private TableColumn<Garcon, String> colCidade;

    @FXML
    private TableColumn<Garcon, String> colFone;

    @FXML
    private TableColumn<Garcon, String> colId;

    @FXML
    private TableColumn<Garcon, String> colNome;

    @FXML
    private TableView<Garcon> tabela;

    @FXML
    private TextField tfFiltro;

    private GarconDAL garconDAL;

    public static Garcon garcon = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        garconDAL = new GarconDAL();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colFone.setCellValueFactory(new PropertyValueFactory<>("fone"));
        preencherTabela("");
    }

    private void preencherTabela(String filtro) {
        List<Garcon> garconList = new ArrayList<>();
        garconList.add(new Garcon(1, "Zé", "212122", "19053300", "123", "Rua da casa dele", "Alfredo Marcondes", "SP", "1888445687"));
        garconList.add(new Garcon(2, "Maria", "3200047898", "19053300", "05", "Rua da casa dela", "Presidente Prudente", "SP", "1888445687"));
                                    //garconDAL.get(filtro);
        tabela.setItems(FXCollections.observableArrayList(garconList));
    }

    @FXML
    void onAlterar(ActionEvent event) throws Exception {
        if (tabela.getSelectionModel().getSelectedIndex() >= 0) {
            garcon = tabela.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(BotecoFX.class.getResource("garcon-form-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            tfFiltro.setText("");
            preencherTabela("");
            garcon = null;
        }
    }

    @FXML
    void onExcluir(ActionEvent event) {
        if (tabela.getSelectionModel().getSelectedIndex() >= 0) {
            Garcon garcon = tabela.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Deseja excluir o garcon "+garcon.getNome()+"?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                garconDAL.apagar(garcon);
                onFiltro(null);
            }
        }
    }

    @FXML
    void onFechar(ActionEvent event) {
        tfFiltro.getScene().getWindow().hide();
    }

    @FXML
    void onFiltro(KeyEvent event) {
        String filtro = tfFiltro.getText().toUpperCase();
        preencherTabela("upper(gar_nome) LIKE '"+filtro+"'");
    }

    @FXML
    void onNovo(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(BotecoFX.class.getResource("garcon-form-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        tfFiltro.setText("");
        preencherTabela("");
    }
}
