package service;

import model.Banco;
import model.Cuenta;
import repository.CuentaRepository;
import repository.RepositorySingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* This class implements business-level logic of the Bank Account system */

public class CuentaService {
    private boolean debug;
    // add fields for Repository
    public CuentaService() {
        this.debug = false;
    }
    public CuentaService(boolean debug) {
        this.debug = debug;
    }

    public boolean retirar(String id, double monto){
        Cuenta c = RepositorySingleton.getRepository().obtenerCuenta(id);
        if (c ==  null) {
            System.out.println("ERROR: Cuenta inexistente");
            return false;
        }
        if (monto <= c.getMonto()) {
            double nuevoMonto = c.getMonto() - monto;
            RepositorySingleton.getRepository().actualizarMontoDeCuenta(id, nuevoMonto);
            return true;
        } else {
            System.out.println("ERROR: Saldo insuficiente");
            return false;
        }
    }

    public boolean retirar(String id, double monto, double comision) {
        Cuenta c = RepositorySingleton.getRepository().obtenerCuenta(id);
        if (c ==  null) {
            System.out.println("ERROR: Cuenta inexistente");
            return false;
        }
        if ( (monto + comision) <= c.getMonto()) {
            double nuevoMonto = c.getMonto() - monto - comision;
            RepositorySingleton.getRepository().actualizarMontoDeCuenta(id, nuevoMonto);
            String output = String.format("Cuenta: %s \t Retiro con comision por %.2f", id, monto + comision);
            System.out.println(output);
            return true;
        } else {
            System.out.println("ERROR: Saldo insuficiente");
            return false;
        }
    }

    public boolean depositar(String id, double monto){
        Cuenta c = RepositorySingleton.getRepository().obtenerCuenta(id);
        if (c ==  null) {
            System.out.println("ERROR: Cuenta inexistente");
            return false;
        }

        double nuevoMonto = c.getMonto() + monto;
        RepositorySingleton.getRepository().actualizarMontoDeCuenta(id, nuevoMonto);
        return true;
    }

    private void agregarCuenta(Cuenta c){
        // Generar nuevo ID
        RepositorySingleton.getRepository().agregarCuenta(c);
    }

    public void crearCuenta(String nombre) {
        String newId = RandomId.getRandomId(8);
        Cuenta c = new Cuenta(nombre, newId);
        agregarCuenta(c);

        System.out.printf("NUEVA CUENTA: %s, %s\n", newId, nombre);

    }

    public void consultarCuenta(String numCuenta) {
        Cuenta c = RepositorySingleton.getRepository().obtenerCuenta(numCuenta);
        System.out.println(c);
    }

    public List<Banco> getSupportedBanks() {
        return RepositorySingleton.getRepository().getBancos();
    }
}

