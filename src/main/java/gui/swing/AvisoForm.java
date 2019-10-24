package gui.swing;

import javafx.scene.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvisoForm {

    public void aviso(String msg, String titulo, Window parent, Integer min ) {


        JOptionPane meuJOPane = new JOptionPane(msg);//instanciando o JOptionPane
        final JDialog dialog = meuJOPane.createDialog(parent, titulo);//aqui uso um JDialog para manipular
        //meu JOptionPane
        dialog.setModal(true);
        //Usando o javax.swing.Timer para poder gerar um evento em um tempo determinado
        //Veja o construtor da classe Timer para mais explicações
        Timer timer = new Timer(min * 1000, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                dialog.dispose();  //o evento(no caso fechar o meu JDialog)
            }
        });
        timer.start();
        dialog.setVisible(true);
        timer.stop();
    }
}
