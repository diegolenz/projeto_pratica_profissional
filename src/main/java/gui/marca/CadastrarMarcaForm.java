/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.marca;

import gui.swing.DialogPadrao;
import jdk.internal.org.objectweb.asm.Handle;
import lib.dao.imp.marca.MarcaDao;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.model.marca.Marca;
import lib.service.MarcaService;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Diego
 */
public class CadastrarMarcaForm extends DialogPadrao {
    private Marca marca;
    private MarcaService marcaService;
    private Callback callback;

    public interface Callback {
        void handle(Marca marca);
    }
    /**
     * Creates new form CadastrarMarcaForm
     */
    public CadastrarMarcaForm(Window parent, boolean modal, Marca marca, Callback callback) {
        super(parent, modal, ModuloSistema.MARCAS, NivelAcessoModulo.LEITURA_GRAVACAO);
        marcaService = new MarcaService();
        this.callback = callback;
        this.marca = marca;
        initComponents();
        if (this.marca.getId() != null) {
            lblDescricaoForm.setText("Alterar marca");
            if (marca.getAtivo())
                rdAtivado.setSelected(true);
            else rdDesativado.setSelected(true);
        }
        else {
            rdAtivado.setEnabled(false);
            rdAtivado.setSelected(true);
            rdDesativado.setEnabled(false);
        }

    }

    public void carrega(){
        if (marca.getId() == null)
            marca.setAtivo(true);
        else {
            if (rdAtivado.isSelected())
                marca.setAtivo(true);
            else marca.setAtivo(false);
        }
        marca.setNome(edtNome.getText());

    }

    public void carregaEDT(){
        edtCod.setText(marca.getId().toString());
        edtNome.setText(marca.getNome());
    }

    public void bloqueia(){
        edtNome.setEditable(false);
        btnsalvar.setVisible(false);
        btncancelar.setVisible(false);
    }

    public void desbloqueiaedt(){
        edtNome.setEditable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        edtNome = new javax.swing.JTextField();
        edtCod = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnsalvar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        lblDescricaoForm = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rdDesativado = new javax.swing.JRadioButton();
        rdAtivado = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de marca");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        edtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${marca.nome}"), edtNome, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        edtCod.setEditable(false);
        edtCod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${this.marca.id}"), edtCod, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Marca");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(edtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNome))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnsalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnsalvar.setText("salvar");
        btnsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalvarActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btncancelar.setText("cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        lblDescricaoForm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDescricaoForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Heading-Tag_icon-icons.com_53735.png"))); // NOI18N
        lblDescricaoForm.setText("Nova Marca");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Obs : campos obrigatórios são assinalados com '*'");

        rdDesativado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdDesativado.setText("Desativado");
        rdDesativado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDesativadoActionPerformed(evt);
            }
        });

        rdAtivado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdAtivado.setText("Ativado");
        rdAtivado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdAtivadoItemStateChanged(evt);
            }
        });
        rdAtivado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAtivadoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Status :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescricaoForm)
                        .addGap(156, 156, 156)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdAtivado)
                        .addGap(0, 0, 0)
                        .addComponent(rdDesativado)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDescricaoForm)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel3))
                        .addComponent(rdAtivado)
                        .addComponent(rdDesativado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(btnsalvar)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rdAtivadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdAtivadoItemStateChanged

    }//GEN-LAST:event_rdAtivadoItemStateChanged

    private void rdAtivadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAtivadoActionPerformed
        if (!rdAtivado.isSelected())
        rdAtivado.setSelected(true);
        rdDesativado.setSelected(false);
    }//GEN-LAST:event_rdAtivadoActionPerformed

    private void rdDesativadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDesativadoActionPerformed
        if (!rdDesativado.isSelected())
        rdDesativado.setSelected(true);
        rdAtivado.setSelected(false);
    }//GEN-LAST:event_rdDesativadoActionPerformed

    private void btnsalvarActionPerformed(java.awt.event.ActionEvent evt) {
        this.carrega();
        try {
            if (marca.getId()==null)
                marcaService.save(marca);
            else
                marcaService.update(marca);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Falha ao salvar! " + e.getMessage());
            e.printStackTrace();
            return;
        }
        JOptionPane.showMessageDialog(this, "Marca Salva com sucesso!");
        callback.handle(marca);
        dispose();
    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastrarMarcaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarMarcaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarMarcaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarMarcaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastrarMarcaForm dialog = new CadastrarMarcaForm(null, true, null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnsalvar;
    private javax.swing.JTextField edtCod;
    private javax.swing.JTextField edtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescricaoForm;
    private javax.swing.JRadioButton rdAtivado;
    private javax.swing.JRadioButton rdDesativado;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
