package front;

import model.Banco;
import service.CuentaService;

public class OperationFactory
{
    private CuentaService service;

    public OperationFactory(CuentaService service) {
        this.service = service;
    }

    public CuentaService getService() {
        return service;
    }

    public void setService(CuentaService service) {
        this.service = service;
    }

    public BankOperation createDeposito(String numeroCuenta, double monto) {
        // TODO
        return new Deposito(this.service, numeroCuenta, monto);
    }

    public BankOperation createRetiro(String numeroCuenta, double monto) {

        return new Retiro(this.service, numeroCuenta, monto);
    }

    public BankOperation createRetiroComision(String numeroCuenta, double monto, Banco banco) {

        return new RetiroComision(this.service, numeroCuenta, monto, banco);
    }

    public BankOperation createConsulta(String numCuenta) {
        return new Consulta(this.service, numCuenta);
    }

    public BankOperation createAlta(String nombre) {
        return new Alta(this.service, nombre);
    }
}
