package ericserafim.com.sqlitesingleton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import ericserafim.com.sqlitesingleton.adapter.TarefaAdapter;
import ericserafim.com.sqlitesingleton.dao.TarefaDao;
import ericserafim.com.sqlitesingleton.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TarefaAdapter adapter;
    private ArrayList<Tarefa> tarefas;
    private TarefaDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Tarefa t = new Tarefa();
                final EditText tvTitulo = new EditText(MainActivity.this);
                tvTitulo.setSingleLine(true);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Inclusão");
                dialog.setMessage("Digite o título da tarefa");
                dialog.setView(tvTitulo);


                dialog.setPositiveButton("Incluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        t.setTitulo(tvTitulo.getText().toString());
                        dao.incluir(t);
                        tarefas.add(t);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("Cancelar", null);

                dialog.create();
                dialog.show();
            }
        });

        dao = new TarefaDao();
        tarefas = dao.listar();
        adapter = new TarefaAdapter(MainActivity.this, tarefas);
        listView = (ListView) findViewById(R.id.lv_tarefas);
        listView.setAdapter(adapter);

        //Touch simples é para alterar
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Tarefa tarefa = tarefas.get(position);
                final EditText tvTitulo = new EditText(MainActivity.this);
                tvTitulo.setSingleLine(true);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                tvTitulo.setText(tarefa.getTitulo());
                dialog.setTitle("Alteração");
                dialog.setMessage("Digite o novo título");
                dialog.setView(tvTitulo);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        tarefa.setTitulo(tvTitulo.getText().toString());
                        dao.alterar(tarefa);
                        tarefas.set(position, tarefa);
                        adapter.notifyDataSetChanged();
                    }
                });

                dialog.create();
                dialog.show();
            }
        });

        //Touch longo é para excluir
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Tarefa tarefa = tarefas.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Exclusão");
                dialog.setMessage("Você realmente deseja excluir a tarefa?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.excluir(tarefa);
                        tarefas.remove(tarefa);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("Não", null);

                dialog.create();
                dialog.show();

                return true;
            }
        });
    }

}
