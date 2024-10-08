package com.example.botecofx.db.util;

import java.util.List;

public interface IDAL <T>{ //define um objeto 'generics'
    public boolean gravar(T entidade);
    public boolean alterar(T entidade);
    public boolean apagar(T entidade);
    public T get(int id);
    public List<T> get(String filtro);
}