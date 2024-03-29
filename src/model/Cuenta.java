package model;

public class Cuenta {
    private double monto;
    private String nombre;
    private String numeroDeCuenta;

    public Cuenta(String nombre, String numeroDeCuenta) {
        monto = 0;
        this.nombre = nombre;
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    @Override
    public String toString() {
        return String.format("------------\n| Nombre: %s\n| Cuenta: %s\n| Saldo: $%.2f\n------------", nombre, numeroDeCuenta, monto);
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }
}
