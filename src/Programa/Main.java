package Programa;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class Main {

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<CentroDistribucion> centros = new ArrayList<>();
    private static List<Ruta> rutas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            cargarDatos();
            ejecutarBacktracking();
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    private static void cargarDatos() throws IOException {
        // Llama a los métodos para cargar datos desde archivos.
    	clientes = DataLoader.cargarClientes("clientesYCentros.txt");
    	centros = DataLoader.cargarCentros("clientesYCentros.txt");
    	rutas = RutaLoader.cargarRutas("rutas.txt");
    }

    private static void ejecutarBacktracking() {
        // Crear una instancia de la clase de optimización
        OptimizacionCentros optimizador = new OptimizacionCentros(clientes, centros, rutas);
        optimizador.encontrarMejorConfiguracion();
    }
}