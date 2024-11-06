package Programa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RutaLoader {

    public static List<Ruta> cargarRutas(String filePath) throws IOException {
        List<Ruta> rutas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        reader.readLine(); // Ignorar la primera línea con el número total de rutas
        String line;

        // Leer cada línea de ruta
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int origen = Integer.parseInt(parts[0]);
            int destino = Integer.parseInt(parts[1]);
            int costo = Integer.parseInt(parts[2]);
            rutas.add(new Ruta(origen, destino, costo));
        }
        reader.close();
        return rutas;
    }
}
