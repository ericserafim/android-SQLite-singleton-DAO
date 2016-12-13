package ericserafim.com.sqlitesingleton.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public final class BancoDados {

    public static final String BANCOAPP = "bancoapp";
    private static SQLiteDatabase database;

    public static SQLiteDatabase instance(Context context) {
        if (database == null) {
            database = context.openOrCreateDatabase(BANCOAPP, Context.MODE_PRIVATE, null);
            Versao.criarTabelas(database);
        }

        return database;
    }

    public static SQLiteDatabase instance() {
        return database;
    }

    public static void close() {
        if (database != null)
            database.close();
    }

}
