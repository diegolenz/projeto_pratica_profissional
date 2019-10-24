/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.financeiro.formaPagamento;

import gui.modeltable.TableModelMeioMovimentoCaixa;
import gui.swing.SociusTab;
import gui.swing.WindowPadrao;
import lib.dao.imp.financeiro.formaPagamentoDAO.FormaPagamentoDAO;
import lib.model.financeiro.formaPagamento.FormaPagamento;
import lib.model.interno.ModuloSistema;
import lib.service.FormaPagamentoService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Diego
 */
public class ConsultaFormasPagamentos extends SociusTab implements WindowPadrao {
    /**
     * Creates new form ConsultaPanel
     */

    private List<FormaPagamento> formasPagamento;
    private CadastroFormaPagamentoForm cadastroMeioPagamento;
    private TableModelMeioMovimentoCaixa tableModelMeioMovimentoCaixa;


    public ConsultaFormasPagamentos(Window parent) {
        super(parent, ModuloSistema.FORMAS_PAGAMENTO);
        initComponents();
        tableModelMeioMovimentoCaixa=new TableModelMeioMovimentoCaixa();
       this.btnpesquisarActionPerformed(null);
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
        btnpesquisar = new javax.swing.JButton();
        edtPesquisa = new javax.swing.JTextField();
        btnVisualizar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("Consulta"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnpesquisar.setText("pesquisar");
        btnpesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesquisarActionPerformed(evt);
            }
        });

        btnVisualizar.setText("visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        btnAlterar.setText("alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnExcluir.setText("excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(edtPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnpesquisar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnpesquisar)
                    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVisualizar)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            formasPagamento = new FormaPagamentoDAO().getAll(edtPesquisa.getText());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }
        tableModelMeioMovimentoCaixa.setList(formasPagamento.toArray());
        jTable1.setModel(tableModelMeioMovimentoCaixa);
        if (!formasPagamento.isEmpty())
            this.jTable1.setRowSelectionInterval(0,0);
        else JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado");
    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroFormaPagamentoForm.Callback callback = (produto -> {
            try {
                formasPagamento = new  FormaPagamentoService().getAll("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            Integer pos = formasPagamento.indexOf(produto);
            jTable1.setRowSelectionInterval(pos,pos);
        });
        new CadastroFormaPagamentoForm(getWindowParent(), true, new FormaPagamento(), callback).show();
    }

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this,"Selecione um registro!");
            return;
        }
        CadastroFormaPagamentoForm.Callback callback = (produto -> {
            try {
                formasPagamento = new  FormaPagamentoService().getAll("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            Integer pos = formasPagamento.indexOf(produto);
            jTable1.setRowSelectionInterval(pos,pos);
        });
        FormaPagamento formaPagamento=formasPagamento.get(jTable1.getSelectedRow());
        CadastroFormaPagamentoForm cadastroMeioPagamento=new CadastroFormaPagamentoForm(getWindowParent(), true, formaPagamento, callback);
        cadastroMeioPagamento.carregaEDT();
        cadastroMeioPagamento.show();

    }

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        if (jTable1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this,"Selecione um registro!");
            return;
        }
        FormaPagamento formaPagamento=formasPagamento.get(jTable1.getSelectedRow());
        CadastroFormaPagamentoForm cadastroMeioPagamento=new CadastroFormaPagamentoForm(getWindowParent(), true, formaPagamento, null);
        cadastroMeioPagamento.show();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        FormaPagamento pessoaSelecionado=formasPagamento.get(jTable1.getSelectedRow());
        try {
            new FormaPagamentoService().deleteByID(pessoaSelecionado.getId());
            JOptionPane.showMessageDialog(this, "Excluido com sucesso");
            return;

        }catch (Exception e) {
            if (JOptionPane.showConfirmDialog(this,"Não é possivel excluir o registro, deseja desativa-lo?", "ATENÇÂO", JOptionPane.YES_NO_OPTION)==0) {
                if (!pessoaSelecionado.getAtivo())
                    JOptionPane.showMessageDialog(this, "Pessoa já está desativada");
                try {
                    new FormaPagamentoService().update(pessoaSelecionado);
                    JOptionPane.showMessageDialog(this,
                            "Desativado com sucesso"
                    );
                    return;
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Não foi possivel desativar");
                }
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnDesativarActionPerformed(java.awt.event.ActionEvent evt) {
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JButton btnpesquisar;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
