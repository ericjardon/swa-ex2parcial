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

    // add fields for Repository

    public void retirar(String id, double monto){
        Cuenta c = RepositorySingleton.getRepository().obtenerCuenta(id);
        if (c ==  null) {
            System.out.println("ERROR: Cuenta inexistente");
            return;
        }
        if (monto <= c.getMonto()) {
            double nuevoMonto = c.getMonto() - monto;
            RepositorySingleton.getRepository().actualizarMontoDeCuenta(id, nuevoMonto);
        } else {
            System.out.println("ERROR: Saldo insuficiente");
        }
    }

    public void retirar(String id, double monto, double comision) {
        Cuenta c = RepositorySingleton.getRepository().obtenerCuenta(id);
        if (c ==  null) {
            System.out.println("ERROR: Cuenta inexistente");
            return;
        }
        if ( (monto + comision) <= c.getMonto()) {
            double nuevoMonto = c.getMonto() - monto - comision;
            RepositorySingleton.getRepository().actualizarMontoDeCuenta(id, nuevoMonto);
        } else {
            System.out.println("ERROR: Saldo insuficiente");
        }
    }

    public void depositar(String id, double monto){
        Cuenta c = RepositorySingleton.getRepository().obtenerCuenta(id);
        if (c ==  null) {
            System.out.println("ERROR: Cuenta inexistente");
            return;
        }

        double nuevoMonto = c.getMonto() + monto;
        RepositorySingleton.getRepository().actualizarMontoDeCuenta(id, nuevoMonto);
    }

    public void agregarCuenta(Cuenta c){
        // Generar nuevo ID
        RepositorySingleton.getRepository().agregarCuenta(c);
    }

    public void crearCuenta(String nombre) {
        String newId = RandomId.getRandomId(8);
        Cuenta c = new Cuenta(nombre, newId);
        agregarCuenta(c);
        String msg = String.format("CREA NUEVA CUENTA: %s, %s", newId, nombre);
        System.out.println(msg);
    }

    public List<Banco> getSupportedBanks() {
        return RepositorySingleton.getRepository().getBancos();
    }
}

