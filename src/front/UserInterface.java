package front;

import model.Banco;
import service.CuentaService;

import java.util.ArrayList;

public class UserInterface {
    private OperationFactory ops;
    private ArrayList<Banco> supportedBanks;

    public UserInterface() {
        CuentaService service = new CuentaService();
        ops = new OperationFactory(service);
    }

    private void promptOperation() {
        // TODO
        // receives parameters for user and creates the corresponding Command.
        // then after confirmation calls dispatchOperation.
    }

    private void dispatchOperation(BankOperation command) {
        // 'sends' the command to the service by calling command.execute()
    }

}
