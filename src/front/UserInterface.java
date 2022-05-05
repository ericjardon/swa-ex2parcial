package front;

import model.Banco;
import service.CuentaService;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    CuentaService service;
    private OperationFactory ops;
    private ArrayList<Banco> bancos;
    private boolean finished;
    private Scanner in;

    private final String[] options = {
            "Hacer un deposito",
            "Hacer un retiro",
            "Crear una cuenta"
    };

    public UserInterface() {
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
        // fetch banks and display TODO
        int i = 0;
        for (Banco b : bancos) {
            System.out.println("> %d -- %s".formatted(i + 1, b.getNombre()));
            i++;
        }
        System.out.print("Selecciona tu banco -> ");
        Integer selection = in.nextInt();
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
        System.out.println("\nDEBUG -- Seleccionaste " + selection);

        switch (selection) {
            case 1:
                System.out.println("~ Deposito:");
                promptDeposito();
                break;
            case 2:
                System.out.println("~ Retiro:");
                break;
            case 3:
                System.out.println("~ Crear Cuenta:");
                break;
            case -1:
                System.out.println("\nGracias por usar el sistema.");
                finished = true;
                break;
            default:
                // code block
                // prompt again
                System.out.println("(!) Opcion invalida. Intenta de nuevo");
        }
    }

    private void promptDeposito() {
        // Creates a Deposito Command and calls dispatch on it.
        System.out.print("Ingresa no. de Cuenta (8 digitos) ->");
        String numCuenta = in.nextLine();
        System.out.println("\nDEBUG -- Seleccionaste " + numCuenta);
        System.out.print("Monto de deposito -> $");
        Double monto = in.nextDouble();
        System.out.println("\nDEBUG -- Seleccionaste " + monto);
        
        BankOperation deposito = ops.createDeposito(numCuenta, monto);
        sendOperation(deposito);
    }

    private void promptRetiro() {
        // Creates a Retiro Command and calls dispatch on it.
        Banco banco = selectBanco();
        System.out.println("DEBUG -- Seleccionaste " + banco.getNombre());
        System.out.print("Ingresa no. de Cuenta (8 dígitos) ->");
        String numCuenta = in.nextLine();
        System.out.println("\nDEBUG -- Seleccionaste " + numCuenta);
        System.out.print("Monto de retiro -> $");
        Double monto = in.nextDouble();
        System.out.println("\nDEBUG -- Seleccionaste " + monto);

        BankOperation retiroComision = ops.createRetiroComision(numCuenta, monto, banco);
        sendOperation(retiroComision);
    }

    private void promptRetiroComision() {
        // Creates a Deposito Command and calls dispatch on it.
        /*displayBancos();
        String nombreBanco = inputBanco();
        System.out.print("Ingresa no. de Cuenta (8 dígitos) ->");
        String numCuenta = in.nextLine();
        System.out.println("\nDEBUG -- Seleccionaste " + numCuenta);
        System.out.print("Monto de depósito -> $");
        Double monto = in.nextDouble();
        System.out.println("\nDEBUG -- Seleccionaste " + monto);

        BankOperation deposito = ops.createDeposito(numCuenta, monto);
        sendOperation(deposito);*/
    }

    private void sendOperation(BankOperation command) {
        // 'sends' the command to the service by calling command.execute()
        command.execute();
    }

    public boolean isDone() {
        return finished;
    }

}
