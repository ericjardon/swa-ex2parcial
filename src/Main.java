import front.UserInterface;
/*
 * BANKING SYSTEM
 * Eric Andrés Jardón Chao | A01376748 | 6 de mayo de 2022
 * */

public class Main {
    /*
    FACADE PATTERN: structural pattern that provides a simplified interface to a library or set of classes.
    * The UserInterface class acts as a menu for the user to interact with the accounts in the repository.
    * It provides a way to collect bank operation parameters
    * and of sending them as commands to the CuentaService receiver, which in turn communicates with the repository.
    * From the client code, there is no other object needed to instantiate to run the system.

    COMMAND PATTERN: behavioral design pattern that turns a request into a stand-alone object that contains
    all information about the request, allowing to pass request objects as arguments to methods.
    * Our menu contains an OperationFactory that is used to generate the Concrete Commands that correspond
    * to the supported bank operations. These implement a BankOperation interface with a single method, "execute()".
    * The commands should contain a reference to their "receiver", i.e. the class that performs the work to serve
    * the request.
    * In this command scenario, the "receiver" class is the CuentaService class.
    * The UserInterface is considered the "sender" class.

    FACTORY PATTERN: is a creational design pattern that provides an interface for creating objects in a superclass,
    but allows subclasses or methods to alter the type of objects that will be created.
    * In a layered approach it becomes natural to have a sending layer and a receiving layer. We want to be
    * able to support different types of operations, as they use different parameters and perform different things.
    * We implement an OperationFactory class that contains methods for creating Command objects as needed.

    SINGLETON: The data source (aka repository) of our app should be a single source of truth for all
    operations and clients, especially if it represents a multi-client system.
    Thus, using a singleton for the class that defines the methods to add and
    update Cuenta objects should be a singleton.

    Eric Jardon Chao
    * */

    public static void main(String[] args) {

        UserInterface menu = new UserInterface(false);


        while (!menu.isDone()) {
            menu.promptOperation();
        }
        System.out.println("Gracias por utilizar el sistema.");
    }
}
