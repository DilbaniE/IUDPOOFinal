package org.example;
import java.util.ArrayList;

public class Prestamo {
    private int id;
    private Cliente cliente;
    private double monto;
    private double interes;
    private int plazo;
    private String fechaInicio;
    private ArrayList<Pago> pagos;

    public Prestamo(int id, Cliente cliente, double monto, double interes, int plazo, String fechaInicio) {
        this.id = id;
        this.cliente = cliente;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.fechaInicio = fechaInicio;
        this.pagos = new ArrayList<>();
    }

    // Calcula la cuota periódica (diaria o semanal)
    public double calcularCuota() {
        double montoTotal = monto + (monto * interes);
        return montoTotal / plazo;
    }

    // Registra un pago
    public void registrarPago(Pago pago) {
        pagos.add(pago);
    }

    // Calcula el total pagado hasta ahora
    public double montoPagado() {
        double total = 0;
        for (int i = 0; i < pagos.size(); i++) {
            total += pagos.get(i).getMonto();
        }
        return total;
    }

    // Calcula el monto pendiente
    public double montoPendiente() {
        double montoTotal = monto + (monto * interes);
        return montoTotal - montoPagado();
    }

    // Verifica si el cliente está moroso (ejemplo simple)
    public boolean estaMoroso() {
        int pagosDebe = plazo;
        int pagosHechos = pagos.size();
        // Si debe más de 2 pagos, se considera moroso
        return (pagosDebe - pagosHechos) > 2;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getMonto() {
        return monto;
    }

    public double getInteres() {
        return interes;
    }

    public int getPlazo() {
        return plazo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }
}