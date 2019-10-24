package gui.swing;

import com.sun.glass.events.KeyEvent;
import gui.sistema.facade.AppFacade;
import lib.model.interno.Funcionario;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogPadrao extends JDialog implements WindowPadrao {

    private  ModuloSistema moduloSistema;
    private  Window parent;
    private  NivelAcessoModulo nivelAcesso;

    public DialogPadrao(Window parent, Boolean modal, ModuloSistema moduloSistema, NivelAcessoModulo nivelAcesso) {
        super(parent);

        if (parent == null) {
            throw new IllegalArgumentException("Parent inválido: null");
        }

        if (moduloSistema == null) {
            throw new IllegalArgumentException("Módulo inválido: null");
        }

        this.parent = parent;
        this.moduloSistema = moduloSistema;
        this.nivelAcesso = nivelAcesso;
        this.initEscapeListener();
        this.initComponentListeners();

    }


    public void setModulo_Nivel( ModuloSistema moduloSistema, NivelAcessoModulo nivelAcesso){
        this.moduloSistema = moduloSistema;
        this.nivelAcesso = nivelAcesso;

    }

    /**
     * Método de inicialização dos ouvitens de eventos para modificação dos componentes dos formulários
     *
     * @author Lucas Dillmann
     * @since 1.5.0
     */
    private void initComponentListeners() {
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {

                java.util.List<JScrollPane> components = new ArrayList<>();
                Arrays.stream(getComponents()).forEach(item -> findChildrenScrollComponents(components, item));

                components.forEach(item -> item.getVerticalScrollBar().setUnitIncrement(20));

            }
        });
    }


    /**
     * Método de busca recursiva dos componentes de scroll
     *
     * @param components Lista de componentes de scroll encontrados
     * @param component  Componente para a busca
     * @author Lucas Dillmann
     * @since 1.5.0
     */
    private void findChildrenScrollComponents(List<JScrollPane> components, Component component) {
        if (component instanceof JScrollPane) components.add((JScrollPane) component);
        if (component instanceof Container) {
            Container container = (Container) component;
            Arrays.stream(container.getComponents()).forEach(item -> findChildrenScrollComponents(components, item));
        }
    }

    private void initEscapeListener() {
        this.getRootPane().registerKeyboardAction(
                event -> handleEscape(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }

    public void handleEscape() {
        dispose();
    }

    @Override
    public ModuloSistema getModuloSistema() {
        return moduloSistema;
    }

    @Override
    public Window getWindowParent() {
        return parent;
    }

    @Override
    public void setVisible(boolean exibir) {
        this.setVisible(exibir, true);
    }

    public void setVisible(boolean exibir, boolean modal) {
        try {
            if (exibir) {

                Funcionario operador = AppFacade.getOperador();

                if ( !operador.isPermissaoConcedida(moduloSistema, nivelAcesso)) {
                    JOptionPane.showMessageDialog(this,
                            "Acesso negado \n" +
                            "Você não tem permissão de acesso ao recurso solicitado \n" +
                            "Permissão necessária"+ moduloSistema.getDescricao() + " (" + nivelAcesso.getDescricao() + ")"
                    );
                    return;
                }

            }

            super.setModal(modal);
            super.setVisible(exibir);
        } catch (Throwable ex) {
            //LoggerFactory.getLogger(DialogPadrao.class).error("Falha ao exibir janela", ex);
        }
    }

    public void show(boolean exibir, boolean modal) {
        try {
            if (exibir) {

                Funcionario operador = AppFacade.getOperador();

                if ( !operador.isPermissaoConcedida(moduloSistema, nivelAcesso)) {
                    JOptionPane.showMessageDialog(this,
                            "Acesso negado \n" +
                                    "Você não tem permissão de acesso ao recurso solicitado \n" +
                                    "Permissão necessária"+ moduloSistema.getDescricao() + " (" + nivelAcesso.getDescricao() + ")"
                    );
                    return;
                }

            }

            super.setModal(modal);
            super.setVisible(exibir);
        } catch (Throwable ex) {
       //     LoggerFactory.getLogger(DialogPadrao.class).error("Falha ao exibir janela", ex);

        }
    }



    @Override
    public NivelAcessoModulo getNivelAcessoNecessario() {
        return nivelAcesso;
    }

    protected void execucaoPrivilegiada(Runnable task) {

        Funcionario operador = AppFacade.getOperador();

        if (!operador.isPermissaoConcedida(moduloSistema, NivelAcessoModulo.LEITURA_GRAVACAO)) {
            JOptionPane.showMessageDialog(this,
                    "Acesso negado \n"+
                    "Você não tem permissão de acesso ao recurso solicitado! \n"
            + "Permissão necessária" + moduloSistema.getDescricao() + " (" + nivelAcesso.getDescricao() + ")"
            );
            return;
        }

        task.run();
    }
}
