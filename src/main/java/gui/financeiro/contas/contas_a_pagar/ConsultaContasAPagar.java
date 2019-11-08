/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.financeiro.contas.contas_a_pagar;

import gui.modeltable.TableModelContas;
import gui.swing.DefaultComboBoxModel;
import gui.swing.SociusTab;
import gui.swing.WindowPadrao;
import lib.model.financeiro.contas.ContaPagar;
import lib.model.financeiro.StatusConta;
import lib.model.financeiro.contas.ContaReceber;
import lib.model.interno.ModuloSistema;
import lib.service.ContaPagarService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;

/**
 * @author Diego
 */
public class ConsultaContasAPagar extends SociusTab implements WindowPadrao {

    private List<ContaPagar> contaPagarList;
    private TableModelContas tableModelContas;

    /**
     * Creates new form ConsultaPanel
     */
    public ConsultaContasAPagar(Window parent) {
        super(parent, ModuloSistema.CONTAS_PAGAR);
        initComponents();
        tableModelContas = new TableModelContas();
        btnPesquisarActionPerformed(null);
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
        btnPesquisar = new javax.swing.JButton();
        edtRecebedor = new javax.swing.JTextField();
        btnDesativar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edtDataLancamento = new com.toedter.calendar.JDateChooser();
        edtDataVencimento = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        edtDataPagamento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        edtFuncionario = new javax.swing.JTextField();
        edtDataVencimentoFinal = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        edtDataLancamentoFinal = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        edtDataPagamentoFinal = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        btnLimparFiltros = new javax.swing.JButton();
        btnReceber = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("Consulta"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        edtRecebedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnDesativar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDesativar.setText("Contas");
        btnDesativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesativarActionPerformed(evt);
            }
        });

        btnVisualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnVisualizar.setText("visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAlterar.setText("alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNovo.setText("Nova");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Recebedor");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Periodo da data de lançamento");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Periodo da data de vencimento");

        edtDataLancamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDataVencimento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Periodo da data de pagamento");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Status");

        cmbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ComboBoxModel cmbModelStatusConta = new DefaultComboBoxModel();
        ((DefaultComboBoxModel) cmbModelStatusConta).addElement("Todos");
        ((DefaultComboBoxModel) cmbModelStatusConta).addElement(StatusConta.QUITADA);
        ((DefaultComboBoxModel) cmbModelStatusConta).addElement(StatusConta.ATRASADO);
        ((DefaultComboBoxModel) cmbModelStatusConta).addElement(StatusConta.PENDENTE);
        ((DefaultComboBoxModel) cmbModelStatusConta).addElement(StatusConta.PAGA_COM_ATRASO);
        cmbStatus.setModel(cmbModelStatusConta);

        edtDataPagamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Funcioario");

        edtFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDataVencimentoFinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Até");

        edtDataLancamentoFinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Até");

        edtDataPagamentoFinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Até");

        btnLimparFiltros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLimparFiltros.setText("Limpar");
        btnLimparFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparFiltrosActionPerformed(evt);
            }
        });

        btnReceber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReceber.setText("Receber");
        btnReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReceber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDesativar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(edtDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(13, 13, 13)
                                .addComponent(edtDataLancamentoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(edtRecebedor))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(edtDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtDataVencimentoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtFuncionario))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(230, 329, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(edtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addGap(14, 14, 14)
                                        .addComponent(edtDataPagamentoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimparFiltros)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtRecebedor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(1, 1, 1)))
                            .addComponent(edtDataPagamentoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLimparFiltros)
                                .addComponent(btnPesquisar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtDataLancamentoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtDataVencimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(edtDataVencimentoFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnDesativar)
                    .addComponent(btnAlterar)
                    .addComponent(btnVisualizar)
                    .addComponent(btnReceber))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        new CadastroContaPagarForm(getWindowParent(), true, new ContaPagar()).show();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado");
            return;
        }
        ContaPagar contaPagar = null;
        try {
            contaPagar = new ContaPagarService().getById(contaPagarList.get(jTable1.getSelectedRow()).getId());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Falha ao retornar dados \n" + ex.getCause() + "\n " + ex.getMessage());
            return;
        }
        if (contaPagar.getCompra() != null){
            JOptionPane.showMessageDialog(this, "Compras vinculadas a compra não podem ser editadas \nConta vinculada a compra N "+contaPagar.getCompra().getNumeroNota()+", Modelo"+ contaPagar.getCompra().getModeloNota() +
                    ", série "+contaPagar.getCompra().getNumSerieNota()+", fornecedor"+ contaPagar.getRecebedor().getNome());
        }
        CadastroContaPagarForm cadastroContaPagarForm = new CadastroContaPagarForm(getWindowParent(), true, contaPagar);
        cadastroContaPagarForm.carregaEdt();
        cadastroContaPagarForm.show();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Nenhum registro selecionado");
            return;
        }
        ContaPagar contaPagar = null;
        try {
            contaPagar = new ContaPagarService().getById(contaPagarList.get(jTable1.getSelectedRow()).getId());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Falha ao retornar dados \n" + ex.getCause() + "\n " + ex.getMessage());
            return;
        }

        CadastroContaPagarForm cadastroContaPagarForm = new CadastroContaPagarForm(getWindowParent(), true, contaPagar);
        cadastroContaPagarForm.carregaEdt();
        cadastroContaPagarForm.bloqueiaEdt();
        cadastroContaPagarForm.show();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnDesativarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (jTable1.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Selecione um resistro");
            return;
        }
        ContaPagar contaSelecionada = contaPagarList.get(jTable1.getSelectedRow());
        if (contaSelecionada.getCompra() != null){
            JOptionPane.showMessageDialog(this, "Contas criadas apartir de uma compra só poderão ser desativadas pelo cancelamento da mesma");
            return;
        }
        CadastroContaPagarForm cadastroContaPagarForm = new CadastroContaPagarForm(getWindowParent(), true, contaSelecionada);
        cadastroContaPagarForm.carregaEdt();
        cadastroContaPagarForm.bloqueiaEdt();
        cadastroContaPagarForm.show();
        if (JOptionPane.showConfirmDialog(getWindowParent(),"Deseja realmente cancelar a conta", "Atenção", JOptionPane.YES_NO_OPTION)==0){
            try {
                contaSelecionada.setAtivo(false);
                new ContaPagarService().update(contaSelecionada);
                JOptionPane.showMessageDialog(this, "Conta desativada com sucesso");
            }catch (SQLException ex){
                JOptionPane.showConfirmDialog(getWindowParent(), "Falha as desativar o resistr ! \n As seguintes restrições foram encontradas: \n"
                        +ex.getCause() +"\n" + ex.getMessage());
            }
        }
        cadastroContaPagarForm.dispose();
    }

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        HashMap<String, Object> termos = new HashMap<>();
        // if (edtDataLancamento.getDate() != null)
        termos.put("data_lancamento", edtDataLancamento.getDate());
        termos.put("data_lancamento_final", edtDataLancamentoFinal.getDate());
        termos.put("data_pagamento", edtDataPagamento.getDate());
        termos.put("data_pagamento_final", edtDataPagamentoFinal.getDate());
        termos.put("data_vencimento", edtDataVencimento.getDate());
        termos.put("data_vncimento_final", edtDataVencimentoFinal.getDate());
        termos.put("funcionario", edtFuncionario.getText());
        termos.put("recebedor", edtRecebedor.getText());
        try {
            contaPagarList = new ContaPagarService().getAll(termos);
            tableModelContas.setList(contaPagarList.toArray());
            jTable1.setModel(tableModelContas);
            if (contaPagarList.isEmpty() ) {
                if (evt != null) {
                    JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado");
                }
            } else
                jTable1.setRowSelectionInterval(0,0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Falha ao retornar dados:" + e.getCause() + "\n Contate o adminstrador do sistema");
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnLimparFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparFiltrosActionPerformed
       edtDataPagamento.setDate(null);
       edtDataLancamento.setDate(null);
       edtDataVencimento.setDate(null);
       edtDataLancamentoFinal.setDate(null);
       edtDataPagamentoFinal.setDate(null);
       edtDataVencimentoFinal.setDate(null);
       edtFuncionario.setText("");
       edtRecebedor.setText("");
       cmbStatus.setSelectedIndex(0);
    }//GEN-LAST:event_btnLimparFiltrosActionPerformed

    private void btnReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceberActionPerformed
        ContaPagar contaPagar = contaPagarList.get(jTable1.getSelectedRow());
        if ( contaPagar == null){
            JOptionPane.showMessageDialog(this, "Selecione um registro");
            return;
        }
        new ContaPagarForm(getWindowParent(), true, contaPagar).show();
    }//GEN-LAST:event_btnReceberActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnDesativar;
    private javax.swing.JButton btnLimparFiltros;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnReceber;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.toedter.calendar.JDateChooser edtDataLancamento;
    private com.toedter.calendar.JDateChooser edtDataLancamentoFinal;
    private com.toedter.calendar.JDateChooser edtDataPagamento;
    private com.toedter.calendar.JDateChooser edtDataPagamentoFinal;
    private com.toedter.calendar.JDateChooser edtDataVencimento;
    private com.toedter.calendar.JDateChooser edtDataVencimentoFinal;
    private javax.swing.JTextField edtFuncionario;
    private javax.swing.JTextField edtRecebedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
