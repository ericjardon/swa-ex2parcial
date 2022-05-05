package front;

import service.CuentaService;

public class Creacion implements BankOperation{
    private String nombre;
    private CuentaService service;
    @Override
    public void execute() {
        service.crearCuenta(nombre);
    }
}
