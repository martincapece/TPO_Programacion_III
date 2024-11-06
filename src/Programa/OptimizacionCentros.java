package Programa;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OptimizacionCentros {
    private List<Cliente> clientes;
    private List<CentroDistribucion> centros;
    private List<Ruta> rutas;
    private int[][] distancias;
    private int mejorCostoTotal = Integer.MAX_VALUE;
    private List<CentroDistribucion> mejoresCentrosSeleccionados = new ArrayList<>();
    private Map<Cliente, CentroDistribucion> asignacionClientes = new HashMap<>();

    public OptimizacionCentros(List<Cliente> clientes, List<CentroDistribucion> centros, List<Ruta> rutas) {
        this.clientes = clientes;
        this.centros = centros;
        this.rutas = rutas;
        inicializarDistancias();
        floydWarshall();
    }

    private void inicializarDistancias() {
        int numNodos = 58; // 50 clientes + 8 centros de distribución
        distancias = new int[numNodos][numNodos];

        for (int i = 0; i < numNodos; i++) {
            Arrays.fill(distancias[i], Integer.MAX_VALUE / 2);
            distancias[i][i] = 0;
        }

        for (Ruta ruta : rutas) {
            distancias[ruta.getOrigen()][ruta.getDestino()] = ruta.getCosto();
            distancias[ruta.getDestino()][ruta.getOrigen()] = ruta.getCosto();
        }
    }

    private void floydWarshall() {
        int numNodos = distancias.length;
        for (int k = 0; k < numNodos; k++) {
            for (int i = 0; i < numNodos; i++) {
                for (int j = 0; j < numNodos; j++) {
                    if (distancias[i][j] > distancias[i][k] + distancias[k][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }
    }

    public void encontrarMejorConfiguracion() {
        backtrack(new ArrayList<>(), 0, 0);
        mostrarResultados();
    }

    private void backtrack(List<CentroDistribucion> centrosSeleccionados, int indiceCentro, int costoAcumulado) {
        if (indiceCentro == centros.size()) {
            Map<Cliente, CentroDistribucion> asignacionTemporal = new HashMap<>();
            int costoTransporteTotal = calcularCostoTransporte(centrosSeleccionados, asignacionTemporal);
            int costoTotal = costoAcumulado + costoTransporteTotal;

            if (costoTotal < mejorCostoTotal) {
                mejorCostoTotal = costoTotal;
                mejoresCentrosSeleccionados = new ArrayList<>(centrosSeleccionados);
                asignacionClientes = new HashMap<>(asignacionTemporal);
            }
            return;
        }

        CentroDistribucion centroActual = centros.get(indiceCentro);
        int nuevoCosto = costoAcumulado + centroActual.getCostoFijo();

        if (nuevoCosto >= mejorCostoTotal) return;

        centrosSeleccionados.add(centroActual);
        backtrack(centrosSeleccionados, indiceCentro + 1, nuevoCosto);

        centrosSeleccionados.remove(centrosSeleccionados.size() - 1);
        backtrack(centrosSeleccionados, indiceCentro + 1, costoAcumulado);
    }

    private int calcularCostoTransporte(List<CentroDistribucion> centrosSeleccionados, Map<Cliente, CentroDistribucion> asignacionTemporal) {
        int costoTotal = 0;

        for (Cliente cliente : clientes) {
            int costoMinimoCliente = Integer.MAX_VALUE;
            CentroDistribucion mejorCentro = null;

            for (CentroDistribucion centro : centrosSeleccionados) {
                int costoTransporte = distancias[cliente.getId()][centro.getId()] + centro.getCostoTransportePuerto();
                costoTransporte *= cliente.getProduccionAnual();
                if (costoTransporte < costoMinimoCliente) {
                    costoMinimoCliente = costoTransporte;
                    mejorCentro = centro;
                }
            }
            costoTotal += costoMinimoCliente;
            asignacionTemporal.put(cliente, mejorCentro); // Guarda la mejor asignación para este cliente
        }
        return costoTotal;
    }

    public void mostrarResultados() {
        System.out.println("Asignación de clientes a centros de distribución:");
        for (Map.Entry<Cliente, CentroDistribucion> entry : asignacionClientes.entrySet()) {
            System.out.println("Cliente " + entry.getKey().getId() + " asignado al centro " + entry.getValue().getId());
        }
        System.out.print("Centros de distribución construidos: ");
        for (CentroDistribucion centro : mejoresCentrosSeleccionados) {
            System.out.print(centro.getId() + " ");
        }
        System.out.println("\nCosto total optimizado: " + mejorCostoTotal);
    }
}

