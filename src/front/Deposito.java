package front;

import service.CuentaService;

public class Deposito {

    private String numeroDeCuenta;
    private double monto;
    private CuentaService service;

    public Deposito(String numeroDeCuenta, double monto, CuentaService service) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.monto = monto;
        this.service = service;
    }
}
