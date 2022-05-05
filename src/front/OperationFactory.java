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
        return new Deposito(numeroCuenta, monto, this.service);
    }

    public BankOperation createRetiro(String numeroCuenta, double monto) {
        return null;
    }

    public BankOperation createRetiroComision(String numeroCuenta, double monto, Banco bank) {
        return null;
    }
}
