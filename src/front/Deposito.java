package front;

import service.CuentaService;

public class Deposito implements BankOperation{

    private String numeroDeCuenta;
    private double monto;
    private CuentaService service;

    public Deposito(CuentaService service, String numeroDeCuenta, double monto) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.monto = monto;
        this.service = service;
    }

    @Override
    public void execute() {
        String output = String.format("Cuenta: %s \t Deposito de %.2f", numeroDeCuenta, monto);
        System.out.println(output);

        boolean ok = service.depositar(numeroDeCuenta, monto);

        if (!ok) {
            System.out.println("(!) Operacion de Deposito fallida.");
        } else {
            System.out.println("✓ Deposito realizado exitosamente");
        }
    }
}
