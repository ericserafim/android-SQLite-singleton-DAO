package ericserafim.com.sqlitesingleton.banco;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public final class Versao {

    public static void criarTabelas(SQLiteDatabase database) {
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, titulo TEXT)");
        } catch (Exception e) {
            Log.i("ERRO", e.getMessage());
        }
    }

}
