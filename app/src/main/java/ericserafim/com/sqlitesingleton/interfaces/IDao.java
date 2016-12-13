package ericserafim.com.sqlitesingleton.interfaces;

import java.util.ArrayList;

public interface IDao<E> {
    void incluir(E obj);
    void alterar(E obj);
    void excluir(E obj);
    void excluir(long id);
    ArrayList<E> listar();
}
