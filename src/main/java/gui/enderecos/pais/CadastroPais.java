/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.enderecos.pais;

import gui.swing.DialogPadrao;
import lib.dao.imp.endereco.pais.PaisDao;
import lib.model.endereco.pais.Pais;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.service.PaisService;
import org.castor.core.util.Assert;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Diego
 */
public class CadastroPais extends DialogPadrao {
    private Pais pais;
    private PaisService paisService;
    private Callback callback;

    interface Callback {
        void handle(Pais pais);
    }

    /**
     * Creates new form CadastroPais
     */
    public CadastroPais(Window parent, boolean modal, Pais pais, Callback callback) {
        super(parent, modal, ModuloSistema.ENDERECO, NivelAcessoModulo.LEITURA_GRAVACAO);
        initComponents();
        this.callback = callback;
        this.pais = pais;
        paisService = new PaisService();
        if (pais.getId() != null)
            this.lblDescricaoForm.setText("Alterar país");
        else {
            rdAtivado.setSelected(true);
            rdAtivado.setEnabled(false);
            rdDesativado.setEnabled(false);
        }
    }

    public void carregaEdt(){
        edtDDI.setText(pais.getDdi());
        edtNome.setText(pais.getNome());
        edtCod.setText(pais.getId().toString());
        rdAtivado.setSelected(pais.getAtivo());
        rdDesativado.setSelected(!pais.getAtivo());
        if (pais.getId()== null) {
            rdAtivado.setEnabled(false);
            rdDesativado.setEnabled(false);
        }
    }

    public void bloqueiaEdt(){
        edtDDI.setEditable(false);
        edtNome.setEditable(false);
        edtCod.setEditable(false);
        rdAtivado.setEnabled(false);
        rdDesativado.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalvar.setEnabled(false);
    }

    public void carrega() {
        pais.setDdi(this.edtDDI.getText());
        pais.setNome(this.edtNome.getText());
        if (pais.getId() == null)
            pais.setAtivo(true);
        else {
            if (rdDesativado.isSelected())
                pais.setAtivo(false);
            else pais.setAtivo(true);
        }
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt)  {
      carrega();
      if(pais.getNome().trim().isEmpty()){
          JOptionPane.showMessageDialog(this, "Campo país não informado");
          edtNome.requestFocus();
          return;
      }
      try {
          if (pais.getId() == null) {
              paisService.save(pais);
          } else {
              paisService.update(pais);
          }
      } catch (Exception e) {
          JOptionPane.showMessageDialog(this, "Falha ao salvar: "+ e.getMessage());
          return;
      }


      JOptionPane.showMessageDialog(this, "Salvo com sucesso");
      dispose();
      callback.handle(pais);
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        if(JOptionPane.showConfirmDialog(this, "nenhum dado alterado será salvo, deseja mesmo cancelar?","CANCELAR", JOptionPane.YES_NO_OPTION)==0){
            dispose();
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        edtNome = new javax.swing.JTextField();
        edtDDI = new javax.swing.JTextField();
        edtCod = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblDescricaoForm = new javax.swing.JLabel();
        rdDesativado = new javax.swing.JRadioButton();
        rdAtivado = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        edtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDDI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtCod.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("País *");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("DDI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(edtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(edtDDI, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtDDI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setText("salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Obs : campos obrigatórios são assinalados com '*'");

        lblDescricaoForm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDescricaoForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mapa.png"))); // NOI18N
        lblDescricaoForm.setText("Novo País");

        rdDesativado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdDesativado.setText("Desativado");
        rdDesativado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdDesativadoItemStateChanged(evt);
            }
        });

        rdAtivado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdAtivado.setSelected(true);
        rdAtivado.setText("Ativado");
        rdAtivado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdAtivadoItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblDescricaoForm, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(rdAtivado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdDesativado)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdAtivado)
                        .addComponent(jLabel3)
                        .addComponent(rdDesativado))
                    .addComponent(lblDescricaoForm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rdDesativadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdDesativadoItemStateChanged
        if (rdDesativado.isSelected()){
            rdAtivado.setSelected(false);
        }
    }//GEN-LAST:event_rdDesativadoItemStateChanged

    private void rdAtivadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdAtivadoItemStateChanged
        if (rdAtivado.isSelected()){
            rdDesativado.setSelected(false);
        }
    }//GEN-LAST:event_rdAtivadoItemStateChanged


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
            java.util.logging.Logger.getLogger(CadastroPais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroPais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroPais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroPais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroPais dialog = new CadastroPais(new javax.swing.JFrame(), true, new Pais(),null);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField edtCod;
    private javax.swing.JTextField edtDDI;
    private javax.swing.JTextField edtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescricaoForm;
    private javax.swing.JRadioButton rdAtivado;
    private javax.swing.JRadioButton rdDesativado;
    // End of variables declaration//GEN-END:variables
}
