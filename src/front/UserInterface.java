package front;

import model.Banco;
import service.CuentaService;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    /* This class provides an interface for the user.
    * It provides a way to collect bank operation parameters
    * and of sending them as commands to the CuentaService receiver
    * */

    CuentaService service;
    private OperationFactory ops;
    private ArrayList<Banco> bancos;
    private boolean finished;
    private Scanner in;
    private boolean debug;

    private final String[] options = {
            "Hacer un deposito",
            "Hacer un retiro",
            "Hacer un retiro de ATM",
            "Crear una cuenta",
            "Consultar cuenta",
    };

    public UserInterface() {
        // On startup, we initialize our connection to the service,
        // we fetch the list of banks, and we do basic input setup
        this.debug = false;
        service = new CuentaService();
        ops = new OperationFactory(service);
        bancos = (ArrayList<Banco>) service.getSupportedBanks();

        finished = false;
        in = new Scanner(System.in);
        System.out.println("~~ BIENVENIDX AL SISTEMA BANCARIO ~~");
    }

    public UserInterface(boolean debug) {
        this.debug = debug;
        service = new CuentaService();
        ops = new OperationFactory(service);
        bancos = (ArrayList<Banco>) service.getSupportedBanks();

        finished = false;
        in = new Scanner(System.in);
        System.out.println("~~ BIENVENIDX AL SISTEMA BANCARIO ~~");
    }

    private void displayOptions() {
        for (int i=0; i<options.length; i++) {
            System.out.printf("> %d -- %s%n", i+1, options[i]);
        }
        System.out.println("> Presiona -1 para salir");
    }

    private Banco selectBanco() {
        int i = 0;
        for (Banco b : bancos) {
            System.out.println("> %d -- %s".formatted(i + 1, b.getNombre()));
            i++;
        }
        System.out.print("Selecciona tu banco -> ");
        Integer selection = in.nextInt();
        in.nextLine();
        return bancos.get(selection - 1);

    }


    public void promptOperation() {
        // TODO
        int selection = -1;
        // receives parameters for user and creates the corresponding Command.
        // then after confirmation calls dispatchOperation.
        displayOptions();
        System.out.print("Tu seleccion ->");
        selection = in.nextInt();
        in.nextLine();

        if (debug) System.out.println("-- DEBUG Seleccionaste " + selection);

        switch (selection) {
            case 1:
                System.out.println("~ Deposito:");
                promptDeposito();
                break;
            case 2:
                System.out.println("~ Retiro:");
                promptRetiro();
                break;
            case 3:
                System.out.println("~ Retiro ATM");
                System.out.println("(!) Estos retiros tienen comision.");
                promptRetiroComision();
                break;
            case 4:
                System.out.println("~ Crear Cuenta:");
                promptCrearCuenta();
                break;
            case 5:
                System.out.println("~ Consultar Cuenta");
                promptConsulta();
                break;
            case -1:
                System.out.println("\nGracias por usar el sistema.");
                finished = true;
                break;
            default:
                // code block
                // prompt again
                System.out.println("(!) Opcion invalida.");
        }

        System.out.println("1 -- Otra operacion");
        System.out.println("2 -- Salir");
        System.out.print("> ");
        int again = in.nextInt();
        in.nextLine();
        if (again!= 1) {
            System.out.println("\nGracias por usar el sistema.");
            finished = true;
        }
    }

    private void promptCrearCuenta() {
        System.out.print("Nombre Cuentahabiente ->");
        String nombre = in.nextLine();
        BankOperation alta = ops.createAlta(nombre);
        sendOperation(alta);
    }

    private void promptConsulta() {
        System.out.print("Ingresa no. de Cuenta (8 digitos) ->");
        String numCuenta = in.nextLine();

        if (debug) System.out.println("\n-- DEBUG Inputs <" + numCuenta +"> ");

        BankOperation consulta = ops.createConsulta(numCuenta);
        sendOperation(consulta);
    }

    private void promptDeposito() {
        // Creates a Deposito Command and calls dispatch on it.

        System.out.print("Ingresa no. de Cuenta (8 digitos) ->");
        String numCuenta = in.nextLine();
        System.out.print("Monto de deposito -> $");
        Double monto = in.nextDouble();

        if (debug) System.out.println("\n-- DEBUG Inputs <" + numCuenta +"> " + monto);
        
        BankOperation deposito = ops.createDeposito(numCuenta, monto);
        sendOperation(deposito);
    }

    private void promptRetiroComision() {
        // Creates a Retiro Command and calls dispatch on it.
        Banco banco = selectBanco();
        System.out.print("Ingresa no. de Cuenta (8 dígitos) ->");
        String numCuenta = in.nextLine();
        System.out.print("Monto de retiro -> $");
        Double monto = in.nextDouble();

        if (debug) System.out.println("\n-- DEBUG Inputs " + numCuenta +" " + monto);

        BankOperation retiroComision = ops.createRetiroComision(numCuenta, monto, banco);
        sendOperation(retiroComision);
    }

    private void promptRetiro() {
        // Creates a Deposito Command and calls dispatch on it.

        System.out.print("Ingresa no. de Cuenta (8 digitos) ->");
        String numCuenta = in.nextLine();
        System.out.print("Monto de retiro -> $");
        Double monto = in.nextDouble();

        if (debug) System.out.println("\n-- DEBUG Inputs " + numCuenta +" " + monto);

        BankOperation retiro = ops.createRetiro(numCuenta, monto);
        sendOperation(retiro);
    }

    private void sendOperation(BankOperation command) {
        // 'sends' the command to the service by calling command.execute()
        command.execute();
    }

    public boolean isDone() {
        return finished;
    }

}
