/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.sistema;

import lib.model.interno.ModuloSistema;

import java.awt.event.ItemEvent;

/**
 * @author diego.lenz
 */
public class PermissaoPanel extends javax.swing.JPanel {

    private final ModuloSistema modulo;


    public PermissaoPanel(ModuloSistema modulo) {
        this.modulo = modulo;

        initComponents();
        edtModulo.setText(modulo.getDescricaoSimplificada());

        // chkLeituraGravacao.setEnabled(true);//modulo.isModoLeituraGravacaoPermitido() && modulo.isModoSomenteLeituraPermitido());
    }

    public void setModuloSelecionado(boolean selected) {
        chkLeituraGravacao.setVisible(selected);
        chkLeituraGravacao.setSelected(true);
    }

    public boolean isLeituraSelecionado() {
        return this.chkSomenteLeitura.isSelected();
    }

    public boolean isLeituraGravacaoSelecionado() {
        return chkLeituraGravacao.isSelected();
    }

    public void setGravacao() {
        chkLeituraGravacao.setSelected(true);
    }

    public void setLeitura() {
        chkSomenteLeitura.setSelected(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        chkLeituraGravacao = new javax.swing.JCheckBox();
        chkSomenteLeitura = new javax.swing.JCheckBox();
        edtModulo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        chkLeituraGravacao.setBackground(new java.awt.Color(255, 255, 255));
        chkLeituraGravacao.setText("Leitura e gravação");
        chkLeituraGravacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkLeituraGravacaoItemStateChanged(evt);
            }
        });

        chkSomenteLeitura.setBackground(new java.awt.Color(255, 255, 255));
        chkSomenteLeitura.setText("Somente leitura");
        chkSomenteLeitura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkSomenteLeituraItemStateChanged(evt);
            }
        });

        edtModulo.setBackground(new java.awt.Color(255, 255, 255));
        edtModulo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        edtModulo.setText("{modulo.permissoes}");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(edtModulo, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSomenteLeitura, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkLeituraGravacao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(edtModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(chkSomenteLeitura, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(chkLeituraGravacao)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkLeituraGravacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkLeituraGravacaoItemStateChanged
        if (chkLeituraGravacao.isSelected())
            chkSomenteLeitura.setSelected(false);
    }//GEN-LAST:event_chkLeituraGravacaoItemStateChanged

    private void chkSomenteLeituraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkSomenteLeituraItemStateChanged
        if (chkSomenteLeitura.isSelected()){
            chkLeituraGravacao.setSelected(false);
        }
    }//GEN-LAST:event_chkSomenteLeituraItemStateChanged


    public ModuloSistema getModulo() {
        return modulo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkLeituraGravacao;
    private javax.swing.JCheckBox chkSomenteLeitura;
    private javax.swing.JLabel edtModulo;
    // End of variables declaration//GEN-END:variables
}
