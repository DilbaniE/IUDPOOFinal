package org.example;

import java.time.LocalDate;

public class Pago {
    private int id;
    private double monto;
    private String fechaPago;

  public Pago(int id, double monto, String fechaPago) {
      this.id = id;
      this.monto = monto;
      this.fechaPago = fechaPago;
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
}
