package front;

import model.Banco;
import service.CuentaService;

public class RetiroComision implements BankOperation {

    private CuentaService service;
    private String numeroDeCuenta;
    private double monto;
    private Banco banco;

    public RetiroComision(CuentaService service, String numeroDeCuenta, double monto, Banco banco) {
        this.service = service;
        this.numeroDeCuenta = numeroDeCuenta;
        this.monto = monto;
        this.banco = banco;
    }

    @Override
    public void execute() {
        String output = String.format("Cuenta: %s \t Retiro con comisión por %f", numeroDeCuenta, monto + banco.getComision());
        System.out.println(output);
        service.retirar(numeroDeCuenta, monto, banco.getComision());
    }
}
