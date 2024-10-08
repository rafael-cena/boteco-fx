package com.example.botecofx.db.dals;

import com.example.botecofx.db.entidades.Categoria;
import com.example.botecofx.db.util.IDAL;
import com.example.botecofx.db.util.SingletonDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAL implements IDAL <Categoria> {

    @Override
    public boolean gravar(Categoria entidade) {
        String sql = """
                INSERT INTO categoria(cat_nome) VALUES ('#1');
                """;
        sql = sql.replace("#1", entidade.getNome());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(Categoria entidade) {
        String sql = """
                UPDATE categoria SET cat_nome='#1' WHERE cat_id=#2;
                """;
        sql = sql.replace("#1", entidade.getNome());
        sql = sql.replace("#2", ""+entidade.getId());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Categoria entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM categoria WHERE cat_id="+entidade.getId());
    }

    @Override
    public Categoria get(int id) {
        Categoria categoria = null;
        String sql = "SELECT * FROM categoria WHERE cat_id="+id;
        ResultSet resultSet = SingletonDB.getConexao().consultar(sql);
        try {
            if (resultSet.next()) {
                categoria = new Categoria(id, resultSet.getString("cat_nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public List<Categoria> get(String filtro) {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        if (!filtro.isEmpty()) {
            sql += " WHERE "+filtro;
        }
        sql+=" ORDER BY cat_nome";
        ResultSet resultSet = SingletonDB.getConexao().consultar(sql);
        try {
            while (resultSet.next()) {
                categorias.add(new Categoria(resultSet.getInt("cat_id"), resultSet.getString("cat_nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}