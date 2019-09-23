/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.sistema;

import lib.model.interno.ModuloSistema;

import java.awt.event.ItemEvent;

/**
 *
 * @author diego.lenz
 */
public class PermissaoPanel extends javax.swing.JPanel {

    private final ModuloSistema modulo;


    public PermissaoPanel(ModuloSistema modulo, boolean somenteLeitura) {
        this.modulo = modulo;

        initComponents();
        edtModulo.setText(modulo.getDescricaoSimplificada());

        chkLeituraGravacao.setEnabled(modulo.isModoLeituraGravacaoPermitido() && modulo.isModoSomenteLeituraPermitido());
        chkLeituraGravacao.setVisible(false);

        if(somenteLeitura) {
            edtModulo.setEnabled(false);
            chkLeituraGravacao.setEnabled(false);
        }
    }

    public void setModuloSelecionado(boolean selected) {
        chkLeituraGravacao.setVisible(selected);
        chkLeituraGravacao.setSelected(modulo.isModoLeituraGravacaoPermitido());
    }

    public void setLeituraGravacaoSelecionado(boolean selected) {
        if (chkLeituraGravacao.isEnabled()) {
            chkLeituraGravacao.setSelected(selected);
        }
    }

    public boolean isModuloSelecionado() {
        return false;//chkModulo.isSelected();
    }

    public boolean isLeituraGravacaoSelecionado() {
        return chkLeituraGravacao.isSelected();
    }







    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chkLeituraGravacao = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        edtModulo = new javax.swing.JLabel();

        chkLeituraGravacao.setBackground(new java.awt.Color(255, 255, 255));
        chkLeituraGravacao.setText("Leitura e gravação");
        chkLeituraGravacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLeituraGravacaoActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Somente leitura");

        edtModulo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        edtModulo.setText("{modulo.permissoes}");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(edtModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkLeituraGravacao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(chkLeituraGravacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1))
            .addComponent(edtModulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkLeituraGravacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLeituraGravacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLeituraGravacaoActionPerformed

    private void chkModuloItemStateChanged(java.awt.event.ItemEvent evt) {
        chkLeituraGravacao.setVisible(evt.getStateChange() == ItemEvent.SELECTED);
    }

    private void chkModuloActionPerformed(java.awt.event.ActionEvent evt) {

    }


    public ModuloSistema getModulo() {
        return modulo;
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkLeituraGravacao;
    private javax.swing.JLabel edtModulo;
    private javax.swing.JCheckBox jCheckBox1;
    // End of variables declaration//GEN-END:variables
}
