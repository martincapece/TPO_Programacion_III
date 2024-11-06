package Programa;

public class CentroDistribucion {
    private int id;
    private int costoTransportePuerto;
    private int costoFijo;

    public CentroDistribucion(int id, int costoTransportePuerto, int costoFijo) {
        this.id = id;
        this.costoTransportePuerto = costoTransportePuerto;
        this.costoFijo = costoFijo;
    }

    public int getId() {
        return id;
    }

    public int getCostoTransportePuerto() {
        return costoTransportePuerto;
    }

    public int getCostoFijo() {
        return costoFijo;
    }
}
