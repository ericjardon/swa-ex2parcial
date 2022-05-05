package front;

import model.Banco;
import service.CuentaService;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private OperationFactory ops;
    private ArrayList<Banco> supportedBanks;
    private boolean finished;
    private Scanner in;
    private String[] options = {
            "Hacer un depósito",
            "Hacer un retiro",
            "Crear una cuenta"
    };

    public UserInterface() {
        CuentaService service = new CuentaService();
        ops = new OperationFactory(service);
        finished = false;
        in = new Scanner(System.in);
        System.out.println("BIENVENIDX AL SISTEMA BANCARIO");
    }

    private void displayOptions() {
        for (int i=0; i<options.length; i++) {
            System.out.println("> %d -- %s".formatted(i+1, options[i]));
        }
        System.out.println("> Presiona -1 para salir");
    }

    public void promptOperation() {
        // TODO
        int selection = -1;
        // receives parameters for user and creates the corresponding Command.
        // then after confirmation calls dispatchOperation.
        displayOptions();
        System.out.print("Tu selección ");
        selection = in.nextInt();
        System.out.println("\nSeleccionaste " + selection);

        switch (selection) {
            case 1:
                System.out.println("Depósito:");
                break;
            case 2:
                System.out.println("Retiro:");
                break;
            case 3:
                System.out.println("Crear Cuenta:");
                break;
            case -1:
                System.out.println("Gracias por usar el sistema.");
                finished = true;
                break;
            default:
                // code block
                // prompt again
                System.out.println("Opción inválida. Intenta de nuevo");
        }
    }

    private void dispatchOperation(BankOperation command) {
        // 'sends' the command to the service by calling command.execute()
    }

    public boolean isDone() {
        return finished;
    }

}
