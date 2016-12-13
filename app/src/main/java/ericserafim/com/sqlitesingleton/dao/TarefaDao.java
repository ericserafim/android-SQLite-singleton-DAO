package ericserafim.com.sqlitesingleton.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import ericserafim.com.sqlitesingleton.banco.BancoDados;
import ericserafim.com.sqlitesingleton.interfaces.IDao;
import ericserafim.com.sqlitesingleton.model.Tarefa;

public class TarefaDao implements IDao<Tarefa>{

    @Override
    public void incluir(Tarefa obj) {
        ContentValues values = new ContentValues();
        values.put("titulo", obj.getTitulo());

        long id = BancoDados.instance().insert("tarefas", "", values);
        obj.setId(id);
    }

    @Override
    public void alterar(Tarefa obj) {
        BancoDados.instance().execSQL(
                        "UPDATE tarefas SET titulo = '" + obj.getTitulo() +
                        "' WHERE id=" + String.valueOf(obj.getId()));
    }

    @Override
    public void excluir(Tarefa obj) {
        excluir(obj.getId());
    }

    @Override
    public void excluir(long id) {
        BancoDados.instance().execSQL("DELETE FROM tarefas WHERE id=" + String.valueOf(id));
    }

    @Override
    public ArrayList<Tarefa> listar() {
        ArrayList<Tarefa> lista = new ArrayList<>();

        Cursor cursor = BancoDados.instance().rawQuery("SELECT * FROM tarefas", null);

        int idIndex = cursor.getColumnIndex("id");
        int tituloIndex = cursor.getColumnIndex("titulo");

        while (cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId(cursor.getLong(idIndex));
            tarefa.setTitulo(cursor.getString(tituloIndex));

            lista.add(tarefa);
        }

        return lista;
    }
}
