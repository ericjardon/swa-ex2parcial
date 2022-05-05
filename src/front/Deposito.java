package front;

import service.CuentaService;

public class Deposito implements BankOperation{

    private String numeroDeCuenta;
    private double monto;
    private CuentaService service;

    public Deposito(String numeroDeCuenta, double monto, CuentaService service) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.monto = monto;
        this.service = service;
    }

    @Override
    public void execute() {
        String output = String.format("Cuenta: %s \t Depósito de %f", numeroDeCuenta, monto);
        System.out.println(output);

        boolean ok = service.depositar(numeroDeCuenta, monto);

        if (!ok) {
            System.out.println("(!) Operación de Depósito fallida.");
        } else {
            System.out.println("✓ Depósito realizado exitosamente");
        }
    }
}
