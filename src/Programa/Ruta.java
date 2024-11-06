package Programa;

public class Ruta {
    private int origen;
    private int destino;
    private int costo;

    public Ruta(int origen, int destino, int costo) {
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public int getCosto() {
        return costo;
    }
}