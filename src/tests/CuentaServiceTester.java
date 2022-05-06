package tests;

import model.Banco;
import model.Cuenta;
import org.junit.Test;
import repository.RepositorySingleton;
import service.CuentaService;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

public class CuentaServiceTester {

    private final CuentaService cs = new CuentaService();

    // Crea cuenta
    @Test
    public void testAltaCuenta() {
        cs.crearCuenta("Test Account");
        // Get newest element of cuentas array
        Cuenta c = RepositorySingleton.getRepository().obtenerCuentaPorIndice(3);
        assertEquals("Test Account", c.getNombre());
        assertNotNull(c.getNumeroDeCuenta());
        assertEquals(8, c.getNumeroDeCuenta().length());
        assertEquals(0d, c.getMonto());
    }

    // Deposito ok
    @Test
    public void testDeposito() {
        String cuentaId = "12345678";
        Cuenta x = RepositorySingleton.getRepository().obtenerCuenta(cuentaId);
        assertEquals(0d, x.getMonto());
        cs.depositar(cuentaId, 100);
        assertEquals(100d, x.getMonto());
    }

    // Retiro not ok
    @Test
    public void testRetiroNotOk() {
        String cuentaId = "24681012";
        Cuenta x = RepositorySingleton.getRepository().obtenerCuenta(cuentaId);
        assertEquals(0d, x.getMonto());
        cs.retirar(cuentaId, 100);
        assertEquals(0d, x.getMonto());
    }

    // Retiro ok
    @Test
    public void testRetiroOk() {
        String cuentaId = "24681012";
        Cuenta x = RepositorySingleton.getRepository().obtenerCuenta(cuentaId);
        assertEquals(0d, x.getMonto());
        cs.depositar(cuentaId, 300);
        assertEquals(300d, x.getMonto());
        cs.retirar(cuentaId, 299);
        assertEquals(1d, x.getMonto());
    }

    // Retiro comision not ok
    @Test
    public void testRetiroComisionNotOk() {
        String cuentaId = "98765432";
        Cuenta x = RepositorySingleton.getRepository().obtenerCuenta(cuentaId);
        assertEquals(0d, x.getMonto());

        // Comisión 30
        Banco A = RepositorySingleton.getRepository().getBancos().get(0);
        // Comisión 15
        Banco B = RepositorySingleton.getRepository().getBancos().get(1);
        // Comisión 10
        Banco C = RepositorySingleton.getRepository().getBancos().get(2);


        cs.depositar(cuentaId, 35);
        cs.retirar(cuentaId, 10d, A.getComision());
        assertEquals(35d, x.getMonto());

    }

    // Retiro comision ok
    @Test
    public void testRetiroComisionOk() {
        String cuentaId = "98765432";
        Cuenta x = RepositorySingleton.getRepository().obtenerCuenta(cuentaId);
        x.setMonto(0);

        // Comisión 30
        Banco A = RepositorySingleton.getRepository().getBancos().get(0);
        assertEquals(30d, A.getComision());
        cs.depositar(cuentaId, 35);

        // should fail
        assertEquals(35d, x.getMonto());
        cs.retirar(cuentaId, 5, A.getComision());
        assertEquals(0d, x.getMonto());

    }
}
