package Programa;

public class Cliente {
    private int id;
    private int produccionAnual;

    public Cliente(int id, int produccionAnual) {
        this.id = id;
        this.produccionAnual = produccionAnual;
    }

    public int getId() {
        return id;
    }

    public int getProduccionAnual() {
        return produccionAnual;
    }
}
