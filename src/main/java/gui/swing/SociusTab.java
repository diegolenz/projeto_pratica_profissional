package gui.swing;

import gui.sistema.facade.AppFacade;
import lib.model.interno.Funcionario;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lucas Dillmann on 04/03/2016.
 */
public abstract class SociusTab extends JPanel implements WindowPadrao {

    public interface Proxy {
        void fechar();
        void exibir(String descricao, WindowPadrao window, boolean modoExclusivo, boolean bloquearMenu);
    }

    private final Window parent;
    private final ModuloSistema moduloSistema;
    private Proxy proxy;

    public SociusTab(Window parent, ModuloSistema moduloSistema) {
        this.parent = parent;
        this.moduloSistema = moduloSistema;
    }

    @Override public ModuloSistema getModuloSistema() {return moduloSistema;}
    @Override public Window getWindowParent() {return parent;}
    public Proxy getProxy() {
        return proxy;
    }
    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
    public void tabOpening() {}
    public void tabOpened() {}
    public void tabClosed() {}
    public boolean tabClosing() { return true; }

    protected void execucaoPrivilegiada(Runnable task) {

        Funcionario operador = AppFacade.getOperador();

        if (!operador.isPermissaoConcedida(moduloSistema, NivelAcessoModulo.LEITURA_GRAVACAO)) {
            JOptionPane.showMessageDialog( this,
                    "Acesso negado \n"+
                    "Você não tem permissão de acesso ao recurso solicitado! \n"
            +
                    "Permissão necessária "+ moduloSistema.getDescricao() + " (" + NivelAcessoModulo.LEITURA_GRAVACAO.getDescricao() + ")"
            );
            return;
        }

        task.run();
    }

    @Override
    public NivelAcessoModulo getNivelAcessoNecessario() {
        return NivelAcessoModulo.SOMENTE_LEITURA;
    }
}