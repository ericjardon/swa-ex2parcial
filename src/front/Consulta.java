package front;

import service.CuentaService;

public class Consulta implements BankOperation {

    private String numeroDeCuenta;
    CuentaService service;

    Consulta(CuentaService service, String numeroDeCuenta) {
        this.service = service;
        this.numeroDeCuenta = numeroDeCuenta;
    }

    @Override
    public void execute() {
        service.consultarCuenta(numeroDeCuenta);
    }

}
