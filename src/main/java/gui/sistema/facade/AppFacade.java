package gui.sistema.facade;

import lib.model.interno.Funcionario;


public class AppFacade {
    private static Funcionario operador;
    private static Funcionario supervisor;

    public static Funcionario getOperador() {
        return operador;
    }

    public static void setOperador(Funcionario operador) {
        AppFacade.operador = operador;
    }

    public static Funcionario getSupervisor() {
        return supervisor;
    }

    public static void setSupervisor(Funcionario supervisor) {
        AppFacade.supervisor = supervisor;
    }
}
