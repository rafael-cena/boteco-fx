package com.example.botecofx.db.dals;

import com.example.botecofx.db.entidades.Garcon;
import com.example.botecofx.db.util.IDAL;
import com.example.botecofx.db.util.SingletonDB;

import java.io.File;
import java.util.List;

public class GarconDAL implements IDAL<Garcon> {
    public boolean gravar(Garcon entidade, File file) {
        String sql = """
                INSERT INTO garcon(
                	gar_nome, gar_cpf, gar_cep, gar_endereco, gar_cidade, gar_uf, gar_fone, gar_numero)
                	VALUES ('#1', '#2', '#3', '#4', '#5', '#6', '#7', '#8');
                """;
        sql = sql.replace("#1", entidade.getNome());
        sql = sql.replace("#2", entidade.getCpf());
        sql = sql.replace("#3", entidade.getCep());
        sql = sql.replace("#4", entidade.getEndereco());
        sql = sql.replace("#5", entidade.getCidade());
        sql = sql.replace("#6", entidade.getUf());
        sql = sql.replace("#7", entidade.getFone());
        sql = sql.replace("#8", entidade.getNumero());
        if (SingletonDB.getConexao().manipular(sql)) {
            if (file != null) {
                int id = SingletonDB.getConexao().getMaxPK("garcon", "gar_id");
                if (SingletonDB.getConexao().gravarImagem(file, "gracon", "gar_foto", "gar_id", id)) {
                    return true;
                }
            }
            else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean gravar(Garcon entidade) {
        return false;
    }

    @Override
    public boolean alterar(Garcon entidade) {
        return false;
    }

    @Override
    public boolean apagar(Garcon entidade) {
        return false;
    }

    public Garcon get (int garId) {
        return new Garcon();
    }

    @Override
    public List<Garcon> get(String filtro) {
        return null;
    }
}
