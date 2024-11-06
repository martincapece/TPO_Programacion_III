package Programa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    // Método para cargar los clientes desde el archivo
    public static List<Cliente> cargarClientes(String filePath) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        // Ignorar las primeras líneas de encabezado
        reader.readLine(); // Total de clientes
        reader.readLine(); // Total de centros de distribución

        // Ignorar los datos de los centros de distribución para llegar a la lista de clientes
        String line;
        while ((line = reader.readLine()) != null && !line.startsWith("0,")) { 
            // Estas líneas se ignoran, ya que representan centros de distribución
        }

        // Lee los datos de los clientes
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            int produccionAnual = Integer.parseInt(parts[1]);
            clientes.add(new Cliente(id, produccionAnual));
        }
        reader.close();
        return clientes;
    }

    // Método para cargar los centros de distribución desde el archivo
    public static List<CentroDistribucion> cargarCentros(String filePath) throws IOException {
        List<CentroDistribucion> centros = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        reader.readLine(); // Ignora la línea de total de clientes
        reader.readLine(); // Ignora la línea de total de centros

        // Leer los datos de los centros de distribución
        for (int i = 0; i < 8; i++) { // Asumiendo siempre 8 centros de distribución
            String line = reader.readLine();
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            int costoTransportePuerto = Integer.parseInt(parts[1]);
            int costoFijo = Integer.parseInt(parts[2]);
            centros.add(new CentroDistribucion(id, costoTransportePuerto, costoFijo));
        }
        reader.close();
        return centros;
    }
}