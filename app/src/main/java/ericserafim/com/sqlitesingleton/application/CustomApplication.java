package ericserafim.com.sqlitesingleton.application;

import android.app.Application;
import android.content.Context;

import ericserafim.com.sqlitesingleton.banco.BancoDados;

public class CustomApplication extends Application {

    @Override
    public void onTerminate() {
        super.onTerminate();
        BancoDados.close();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //Abre o banco de dados. Este evento é disparado antes do onCreate
        BancoDados.instance(base);
    }
}
