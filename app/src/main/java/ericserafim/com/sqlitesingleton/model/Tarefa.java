package ericserafim.com.sqlitesingleton.model;

public class Tarefa {

    private long id;
    private String titulo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String toString() {
        return String.valueOf(id) + " - " + titulo;
    }
}
