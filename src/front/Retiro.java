package front;


import service.CuentaService;

public class Retiro implements BankOperation {

    private CuentaService service;
    private String numeroDeCuenta;
    private double monto;

    public Retiro(CuentaService service, String numeroDeCuenta, double monto) {
        this.service = service;
        this.numeroDeCuenta = numeroDeCuenta;
        this.monto = monto;
    }

    @Override
    public void execute() {
        String output = String.format("Cuenta: %s \t Retiro por %.2f", numeroDeCuenta, monto);
        System.out.println(output);
        service.retirar(numeroDeCuenta, monto);
    }
}