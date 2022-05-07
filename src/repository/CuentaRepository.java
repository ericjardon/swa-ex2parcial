package repository;

import model.Banco;
import model.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class CuentaRepository {
    /* This class implements an in-memory store for accounts.
    * It emulates a back end database for persisting account balance data.
    * */


    private List<Cuenta> cuentas;
    private List<Banco> bancos;

    CuentaRepository(){
        Cuenta c1 = new Cuenta("Eric Chao", "12345678");
        Cuenta c2 = new Cuenta("Hena Mendoza", "98765432");
        Cuenta c3 = new Cuenta("Fer Parra", "24681012");
        cuentas = new ArrayList<Cuenta>(List.of(new Cuenta[]{c1, c2, c3}));

        Banco A = new Banco("A", 30);
        Banco B = new Banco("B", 15);
        Banco C = new Banco("C", 10);

        bancos = new ArrayList<>();
        bancos.add(A);
        bancos.add(B);
        bancos.add(C);
    }

    public Cuenta obtenerCuentaPorIndice(int index){
        return cuentas.get(index);
    }

    public void agregarCuenta(Cuenta c){
        cuentas.add(c);
    }

    public Cuenta obtenerCuenta(String numeroDeCuenta){
        for (Cuenta c:
             cuentas) {
            if(c.getNumeroDeCuenta().equalsIgnoreCase(numeroDeCuenta)){
                return c;
            }
        }
        return null;
    }

    public void actualizarMontoDeCuenta(String numeroDeCuenta, double monto){
        Cuenta c = obtenerCuenta(numeroDeCuenta);
        if (c != null) {
            c.setMonto(monto);
        }
    }

    public List<Banco> getBancos() {
        return bancos;
    }

}
