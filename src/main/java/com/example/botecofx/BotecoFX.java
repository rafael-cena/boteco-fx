package com.example.botecofx;

import com.example.botecofx.db.dals.CategoriaDAL;
import com.example.botecofx.db.dals.ProdutoDAL;
import com.example.botecofx.db.dals.UnidadeDAL;
import com.example.botecofx.db.entidades.Categoria;
import com.example.botecofx.db.entidades.Produto;
import com.example.botecofx.db.entidades.Unidade;
import com.example.botecofx.db.util.SingletonDB;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BotecoFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BotecoFX.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        if (!SingletonDB.conectar())
//            System.out.println(SingletonDB.getConexao().getMensagemErro());
//        Categoria categoria = new Categoria("Lanche");
//        new CategoriaDAL().gravar(categoria);

//          if (!SingletonDB.conectar())
//            System.out.println(SingletonDB.getConexao().getMensagemErro());
//        List<Unidade> unidades = new UnidadeDAL().get("");
//        System.out.println(unidades);

        if (!SingletonDB.conectar()) {
            System.out.println(SingletonDB.getConexao().getMensagemErro());
        }
        List<Produto> produtos = new ProdutoDAL().get("");
        System.out.println(produtos);

        Produto produto = new ProdutoDAL().get(1);
        System.out.println(produto.getPreco());
        produto.setPreco(produto.getPreco()*1.1);
        System.out.println(produto.getPreco());

        Platform.exit();
//        launch();
    }
}