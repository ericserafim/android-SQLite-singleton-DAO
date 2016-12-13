package ericserafim.com.sqlitesingleton.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ericserafim.com.sqlitesingleton.R;
import ericserafim.com.sqlitesingleton.model.Tarefa;

public class TarefaAdapter extends android.widget.ArrayAdapter<Tarefa>{

    private LayoutInflater inflater;
    private ArrayList<Tarefa> tarefas;

    public TarefaAdapter(Context c, ArrayList<Tarefa> objects) {
        super(c, 0, objects);
        this.tarefas = objects;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        int count = 0;

        if (tarefas != null)
            count = tarefas.size();

        return count;
    }

    @Nullable
    @Override
    public Tarefa getItem(int position) {
        Tarefa t = null;

        if (tarefas != null)
            t = tarefas.get(position);

        return t;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
           view = inflater.inflate(R.layout.tarefa_item, parent, false);
        }

        TextView textView = (TextView) view.findViewById(R.id.tv_titulo);
        Tarefa tarefa = tarefas.get(position);
        textView.setText(tarefa.getTitulo());

        return view;
    }
}
