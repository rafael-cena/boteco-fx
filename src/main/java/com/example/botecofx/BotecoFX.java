package com.example.botecofx;

import com.example.botecofx.db.dals.*;
import com.example.botecofx.db.entidades.*;
import com.example.botecofx.db.util.SingletonDB;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BotecoFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BotecoFX.class.getResource("adm-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("BotecoFX!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        if (!SingletonDB.conectar())
//            System.out.println(SingletonDB.getConexao().getMensagemErro());
////        Categoria categoria = new Categoria("Lanche");
////        new CategoriaDAL().gravar(categoria);
//
////          if (!SingletonDB.conectar())
////            System.out.println(SingletonDB.getConexao().getMensagemErro());
////        List<Unidade> unidades = new UnidadeDAL().get("");
////        System.out.println(unidades);
//
////        if (!SingletonDB.conectar()) {
////            System.out.println(SingletonDB.getConexao().getMensagemErro());
////        }
////        List<Produto> produtos = new ProdutoDAL().get("");
////        System.out.println(produtos);
////
////        Produto produto = new ProdutoDAL().get(1);
////        System.out.println(produto.getPreco());
////        produto.setPreco(produto.getPreco()*1.1);
////        System.out.println(produto.getPreco());
//
//        ComandaDAL dal = new ComandaDAL();
//        Comanda comanda = dal.get(10);
//
//        Garcon garcon = new Garcon();
//        garcon.setId(1);
//        //Comanda comanda = new Comanda(100,"teste", LocalDate.now(), 98, 'A', garcon);
//        ProdutoDAL produtoDAL = new ProdutoDAL();
//        comanda.addItem(new Comanda.Item(produtoDAL.get(3), 1, 18));
////        comanda.addItem(new Comanda.Item(produtoDAL.get(7), 1, 50));
////        comanda.addItem(new Comanda.Item(produtoDAL.get(4), 1, 9));
//
//        if (!new ComandaDAL().alterar(comanda))
//            System.out.println(SingletonDB.getConexao().getMensagemErro());
//
//        Platform.exit();
        launch();
    }
}