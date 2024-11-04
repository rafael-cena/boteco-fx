package com.example.botecofx;

import com.example.botecofx.db.dals.GarconDAL;
import com.example.botecofx.db.entidades.Garcon;
import com.example.botecofx.db.util.SingletonDB;
import com.example.botecofx.util.ApisService;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

public class GarconFormController implements Initializable {

    public ImageView imageView;
    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

    @FXML
    private TextField tfCep;

    @FXML
    private TextField tfCidade;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfEndereco;

    @FXML
    private TextField tfFone;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfUf;
    private File file = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tfNome.requestFocus();
            }
        });
        unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil.cpfField(tfCpf);
        unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil.cepField(tfCep);
        unoeste.fipp.andrebrunogabriel.abgestacionamentosfipp.abgestacionamento.Formatacoes.MaskFieldUtil.foneField(tfFone);
        // se for alteracao
        if (GarconConsultaController.garcon != null) {
            Garcon gAux = GarconConsultaController.garcon;
            // preencher os dados do garcon
            tfNome.setText(gAux.getNome());
            tfCpf.setText(gAux.getCpf());
            tfCep.setText(gAux.getCep());
            tfEndereco.setText(gAux.getEndereco());
            tfNumero.setText(gAux.getNumero());
            tfCidade.setText(gAux.getCidade());
            tfUf.setText(gAux.getUf());
            tfFone.setText(gAux.getFone());
            BufferedImage bufferedImage;
            bufferedImage=SingletonDB.getConexao().carregarImagem("garcon", "gar_foto", "gar_id", gAux.getId());
            imageView.setImage(SwingFXUtils.toFXImage(bufferedImage,null));
        }

    }

    @FXML
    void onCancelar(ActionEvent event) {
        btCancelar.getScene().getWindow().hide();
    }

    @FXML
    void onConfirmar(ActionEvent event) {
        Garcon garcon = new Garcon(tfNome.getText(), tfCpf.getText(), tfCep.getText(),
            tfNumero.getText(), tfEndereco.getText(), tfCidade.getText(), tfUf.getText(), tfFone.getText());
        if (!new GarconDAL().gravar(garcon, file)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao gravar o Garçon");
            alert.setContentText("Erro: "+ SingletonDB.getConexao().getMensagemErro());
            alert.showAndWait();
        }
        btConfirmar.getScene().getWindow().hide();
    }

    public void onBuscarCep(KeyEvent keyEvent) {
        if (tfCep.getText().length() == 9) {
            String dados = ApisService.consultaCep(tfCep.getText(), "json");
            JSONObject json = new JSONObject(dados);
            tfCidade.setText(json.getString("localidade"));
            tfEndereco.setText(json.getString("logradouro"));
            tfUf.setText(json.getString("uf"));
            tfNumero.requestFocus();
        }
    }

    public void onBuscarImagem (MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }
}