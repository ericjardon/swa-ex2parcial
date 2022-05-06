package front;

import service.CuentaService;

public class Alta implements BankOperation{
    private String nombre;
    private CuentaService service;

    public Alta(CuentaService service, String nombre) {
        this.nombre = nombre;
        this.service = service;
    }

    @Override
    public void execute() {
        service.crearCuenta(nombre);
    }
}
