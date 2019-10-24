/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.financeiro.CondicaoPagamento;

import gui.modeltable.TableModelCondicaoPagamento;
import gui.swing.SociusTab;
import gui.swing.WindowPadrao;
import lib.model.financeiro.CondicaoPagamento.CondicaoPagamento;
import lib.model.interno.ModuloSistema;
import lib.service.CondicaoPagamentoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 *
 * @author Diego
 */
public class ConsultaCondicoesPagamentoForm extends SociusTab implements WindowPadrao {

    private CondicaoPagamento condicaoPagamento;
    private List<CondicaoPagamento> condicaoPagamentoList;
    private TableModelCondicaoPagamento tableModelCondicaoPagamento;

    /**
     * Creates new form ConsultaPanel
     */
    public ConsultaCondicoesPagamentoForm(Window parent) {
        super(parent, ModuloSistema.CONDICOES_PAGAMENTO);
        initComponents();
        this.tableModelCondicaoPagamento = new TableModelCondicaoPagamento();
        try {
            condicaoPagamentoList = new  CondicaoPagamentoService().getAllAtivos(edtPesquisa.getText());
            tableModelCondicaoPagamento.setList(condicaoPagamentoList.toArray());
            this.jTable1.setModel(tableModelCondicaoPagamento);
            if (!condicaoPagamentoList.isEmpty())
                this.jTable1.setRowSelectionInterval(0,0);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,  e.getMessage());
        }
        initTable();
    }

    public void initTable(){
        this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        this.jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        edtPesquisa = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("Consulta"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        edtPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAlterar.setText("alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir.setText("desativar");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnExcluir1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir1.setText("Excluir");
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(edtPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir1))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        CadastroCondicaoPagamentoForm.Callback callback = (c -> {
            try {
                condicaoPagamentoList = new CondicaoPagamentoService().getAll(edtPesquisa.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            Integer pos = condicaoPagamentoList.indexOf(c);
            jTable1.setRowSelectionInterval(pos,pos);
        });
        new CadastroCondicaoPagamentoForm(getWindowParent(), true, new CondicaoPagamento(), callback).show();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (jTable1.getSelectedRow() < 0 ){
            JOptionPane.showMessageDialog(this, "Selecione um registro");
        }
        condicaoPagamento=condicaoPagamentoList.get(jTable1.getSelectedRow());
        CadastroCondicaoPagamentoForm.Callback callback = (c -> {
            try {
                condicaoPagamentoList = new CondicaoPagamentoService().getAll(edtPesquisa.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            Integer pos = condicaoPagamentoList.indexOf(c);
            jTable1.setRowSelectionInterval(pos,pos);
        });
        CadastroCondicaoPagamentoForm cadastroCondicaoPagamentoForm = new CadastroCondicaoPagamentoForm(getWindowParent(), true, condicaoPagamento,callback);
        cadastroCondicaoPagamentoForm.carregarEdt();
        cadastroCondicaoPagamentoForm.show();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private Integer getPosicao(CondicaoPagamento marca){
        try {
            marca = new CondicaoPagamentoService().getLast();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer tam = condicaoPagamentoList.size();
        Integer pos = 0;
        boolean achou =false;
        while ( pos < tam && !achou ){
            if (condicaoPagamentoList.get(pos).getId().equals(marca.getId())) {
                achou =true;
            } else
                pos++;
        }
        return pos;
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        condicaoPagamento = condicaoPagamentoList.get(jTable1.getSelectedRow());
        CadastroCondicaoPagamentoForm cadastroCondicaoPagamentoForm = new CadastroCondicaoPagamentoForm(getWindowParent(), true, condicaoPagamento, null);
        cadastroCondicaoPagamentoForm.show();
        if (JOptionPane.showConfirmDialog(this, "Deseja relamente excluir a condição de pagamento", "Atenção", JOptionPane.YES_NO_OPTION)!=0){
            return;
        }
        try {

        }catch (Exception ex){
            if (condicaoPagamento.getAtivo()) {
                if (JOptionPane.showConfirmDialog(this, "Não foi possivel excluir a condição de pagamento", "atenção", JOptionPane.YES_NO_OPTION) != 0) {
                    return;
                }
                condicaoPagamento.setAtivo(false);
                try {
                    new CondicaoPagamentoService().update(condicaoPagamento);
                } catch (Exception e){
                    JOptionPane.showMessageDialog(this, "Falha ao salvar " + e);
                    return;
                }
                JOptionPane.showMessageDialog(this, "Condição de pagamento desativada com sucesso");
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            condicaoPagamentoList = new  CondicaoPagamentoService().getAllAtivos(edtPesquisa.getText());
            tableModelCondicaoPagamento.setList(condicaoPagamentoList.toArray());
            this.jTable1.setModel(tableModelCondicaoPagamento);
            if (!condicaoPagamentoList.isEmpty())
                this.jTable1.setRowSelectionInterval(0,0);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,  e.getMessage());
        }
    }

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed

    }//GEN-LAST:event_btnExcluir1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnNovo;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
