package repository;

import model.Banco;

import java.util.ArrayList;

public class RepositorySingleton extends CuentaRepository
{
    static private RepositorySingleton repository;

    private RepositorySingleton(){};  // private constructor

    public static RepositorySingleton getRepository() {
        if (repository == null) {
            repository = new RepositorySingleton();
        }
        return repository;
    }

}
