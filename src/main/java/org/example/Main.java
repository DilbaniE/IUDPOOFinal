package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        PrestamoManager manager = new PrestamoManager();
        int opcion = 0;

        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE PRESTAMOS =====");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Buscar préstamo por ID");
            System.out.println("3. Buscar préstamos por nombre de cliente");
            System.out.println("4. Calcular total otorgado");
            System.out.println("5. Listar préstamos pendientes");
            System.out.println("6. Mostrar resumen general");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(lea.nextLine());
            } catch (Exception e) {
                System.out.println("ERROR: Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    try {
                        System.out.println("\n--- REGISTRAR NUEVO PRÉSTAMO ---");

                        // Datos del cliente
                        System.out.print("ID del cliente: ");
                        int idCliente = Integer.parseInt(lea.nextLine());
                        System.out.print("Nombre del cliente: ");
                        String nombre = lea.nextLine();
                        System.out.print("Dirección: ");
                        String direccion = lea.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = lea.nextLine();

                        Cliente cliente = new Cliente(idCliente, nombre, direccion, telefono);

                        // Datos del préstamo
                        System.out.print("ID del préstamo: ");
                        int idPrestamo = Integer.parseInt(lea.nextLine());
                        System.out.print("Monto del préstamo: $");
                        double monto = Double.parseDouble(lea.nextLine());
                        System.out.print("Tasa de interés (%): ");
                        double interes = Double.parseDouble(lea.nextLine());
                        System.out.print("Plazo en meses: ");
                        int plazo = Integer.parseInt(lea.nextLine());
                        System.out.print("Fecha de inicio (dd/mm/aaaa): ");
                        String fechaInicio = lea.nextLine();

                        Prestamo prestamo = new Prestamo(idPrestamo, cliente, monto, interes, plazo, fechaInicio);
                        manager.registrarPrestamo(prestamo);

                    } catch (Exception e) {
                        System.out.println("ERROR: Datos inválidos. " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("\nIngrese el ID del préstamo: ");
                        int id = Integer.parseInt(lea.nextLine());
                        Prestamo p = manager.buscarPorId(id);

                        if (p != null) {
                            System.out.println("\n--- PRÉSTAMO ENCONTRADO ---");
                            mostrarPrestamo(p);
                        } else {
                            System.out.println("✗ No se encontró un préstamo con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR: Ingrese un ID válido.");
                    }
                    break;

                case 3:
                    System.out.print("\nIngrese el nombre del cliente: ");
                    String nombreBuscar = lea.nextLine();
                    ArrayList<Prestamo> encontrados = manager.buscarPorNombre(nombreBuscar);

                    if (encontrados.isEmpty()) {
                        System.out.println("✗ No se encontraron préstamos para ese cliente.");
                    } else {
                        System.out.println("\n--- PRÉSTAMOS ENCONTRADOS (" + encontrados.size() + ") ---");
                        for (Prestamo p : encontrados) {
                            mostrarPrestamo(p);
                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case 4:
                    double total = manager.calcularTotalOtorgado();
                    System.out.printf("\n💰 TOTAL OTORGADO EN PRÉSTAMOS: $%,.2f\n", total);
                    break;

                case 5:
                    ArrayList<Prestamo> pendientes = manager.listarPendientes();

                    if (pendientes.isEmpty()) {
                        System.out.println("\n✓ No hay préstamos con saldo pendiente.");
                    } else {
                        System.out.println("\n--- PRÉSTAMOS CON SALDO PENDIENTE (" + pendientes.size() + ") ---");
                        for (Prestamo p : pendientes) {
                            mostrarPrestamo(p);
                            System.out.println("-----------------------------");
                        }
                    }
                    break;
                case 6:
                    manager.mostrarResumen();
                    break;

                case 7:
                    System.out.println("\n✓ Saliendo del sistema... ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("✗ Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 7);

        lea.close();
    }

    private static void mostrarPrestamo(Prestamo p) {
        System.out.println("ID: " + p.getId());
        System.out.println("Cliente: " + p.getCliente().getNombre());
        System.out.println("Teléfono: " + p.getCliente().getTelefono());
        System.out.println("Monto: $" + String.format("%.2f", p.getMonto()));
        System.out.println("Interés: " + p.getInteres() + "%");
        System.out.println("Plazo: " + p.getPlazo() + " meses");
        System.out.println("Fecha inicio: " + p.getFechaInicio());
        System.out.println("Monto pendiente: $" + String.format("%.2f", p.montoPendiente()));
    }
}