package org.example;

import org.example.Prestamo;

import java.util.ArrayList;

public class PrestamoManager {
    private ArrayList<Prestamo> prestamos;

    public PrestamoManager() {
        prestamos = new ArrayList<>();
    }

    // Registra un nuevo préstamo
    public void registrarPrestamo(Prestamo p) {
        prestamos.add(p);
        System.out.println("\n✓ Préstamo registrado exitosamente!");
    }

    // Busca un préstamo por ID
    public Prestamo buscarPorId(int id) {
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getId() == id) {
                return prestamos.get(i);
            }
        }
        return null;
    }

    // Busca préstamos por nombre de cliente
    public ArrayList<Prestamo> buscarPorNombre(String nombreCliente) {
        ArrayList<Prestamo> encontrados = new ArrayList<>();
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCliente().getNombre().equalsIgnoreCase(nombreCliente)) {
                encontrados.add(prestamos.get(i));
            }
        }
        return encontrados;
    }

    // Calcula el total otorgado
    public double calcularTotalOtorgado() {
        double total = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            total += prestamos.get(i).getMonto();
        }
        return total;
    }

    // Lista préstamos con saldo pendiente
    public ArrayList<Prestamo> listarPendientes() {
        ArrayList<Prestamo> pendientes = new ArrayList<>();
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).montoPendiente() > 0) {
                pendientes.add(prestamos.get(i));
            }
        }
        return pendientes;
    }

    // Muestra un resumen general
    public void mostrarResumen() {
        System.out.println("\n===== RESUMEN GENERAL =====");
        System.out.println("Total de préstamos: " + prestamos.size());
        System.out.println("Total otorgado: $" + String.format("%.2f", calcularTotalOtorgado()));
        System.out.println("Clientes con saldo pendiente: " + listarPendientes().size());
        System.out.println("===========================\n");
    }
}