/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.financeiro.CondicaoPagamento;

import gui.financeiro.formaPagamento.PesquisarFormaPagamento;
import gui.modeltable.TableModelParcelaSimples;
import gui.swing.DialogPadrao;
import gui.swing.SwingFormatterFactory;
import javafx.scene.input.KeyCode;
import lib.dao.imp.financeiro.condicaoPagamentoDAO.CondicaoPagamentoDAO;
import lib.model.financeiro.CondicaoPagamento.CondicaoPagamento;
import lib.model.financeiro.formaPagamento.FormaPagamento;
import lib.model.financeiro.parcela.Parcela;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.service.CondicaoPagamentoService;
import lib.service.FormaPagamentoService;
import lib.service.ParcelaService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Diego
 */
public class CadastroCondicaoPagamentoForm extends DialogPadrao {

    private CondicaoPagamento condicaoPagamento;
    private CondicaoPagamentoDAO condicaoPagamentoDAO;
    private TableModelParcelaSimples tableModelParcelaSimples;
    private List<FormaPagamento> formaPagamentoList;
    private FormaPagamento formaPagamento;
    private Callback callback;


    interface Callback {
        void handle(CondicaoPagamento condicaoPagamento);
    }

    /**
     * Creates new form CadastroCondicaoPagamentoForm
     */
    public CadastroCondicaoPagamentoForm(Window parent, boolean modal, CondicaoPagamento condicaoPagamento, Callback callback) {
        super( parent, modal, ModuloSistema.PESSOAS, NivelAcessoModulo.LEITURA_GRAVACAO);
        initComponents();
        this.condicaoPagamento = condicaoPagamento;
        this.tableModelParcelaSimples = new TableModelParcelaSimples();
        this.callback = callback;

        if (condicaoPagamento.getId() == null){
            condicaoPagamento.setParcelas(new ArrayList<>());
            condicaoPagamento.setAtivo(true);
        } else {
            this.lblDEscricaoForm.setText("Alterar condição de pagamento");
        }


        tableModelParcelaSimples.setList(condicaoPagamento.getParcelas().toArray());
        jTable1.setModel(tableModelParcelaSimples);
        this.tblParcelas.setModel(tableModelParcelaSimples);
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        lblDEscricaoForm = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAddParcela = new javax.swing.JButton();
        spnDiasParcela = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblParcelas = new javax.swing.JTable();
        edtPorcentagem = new javax.swing.JFormattedTextField();
        btnRemoverParcela = new javax.swing.JButton();
        edtFormaPagamento = new javax.swing.JTextField();
        edtCoFormaPagamento = new javax.swing.JTextField();
        btnpesquisar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        edtNumeroParcela = new javax.swing.JFormattedTextField();
        edtNomeCondicao = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        edtDesconto = new javax.swing.JFormattedTextField();
        edtJuros = new javax.swing.JFormattedTextField();
        edtMulta = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblDEscricaoForm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDEscricaoForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-cash-in-hand-30.png"))); // NOI18N
        lblDEscricaoForm.setText("Nova condição de pagamento");

        btnSalvar.setText("salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setEnabled(false);

        btnAddParcela.setText("+");
        btnAddParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddParcelaActionPerformed(evt);
            }
        });

        spnDiasParcela.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblParcelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblParcelas);

        edtPorcentagem.setFormatterFactory( SwingFormatterFactory.buildPorcentagem(0D, 100D));
        edtPorcentagem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnRemoverParcela.setText("-");
        btnRemoverParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverParcelaActionPerformed(evt);
            }
        });

        edtFormaPagamento.setEditable(false);
        edtFormaPagamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtCoFormaPagamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCoFormaPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCoFormaPagamentoKeyPressed(evt);
            }
        });

        btnpesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pesquisar.png"))); // NOI18N
        btnpesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesquisarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Numero");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Dias");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Porcentagem");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Forma de pagamento");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Cód");

        edtNumeroParcela.setEditable(false);
        edtNumeroParcela.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtNumeroParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(spnDiasParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(edtPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(edtCoFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(109, 273, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edtFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddParcela)
                        .addGap(5, 5, 5)
                        .addComponent(btnRemoverParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnpesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(edtPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(edtFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(edtCoFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(edtNumeroParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spnDiasParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddParcela)
                        .addComponent(btnRemoverParcela)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        edtNomeCondicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDesconto.setFormatterFactory( SwingFormatterFactory.buildPorcentagem(0D, 100D));
        edtDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtJuros.setFormatterFactory( SwingFormatterFactory.buildPorcentagem(0D, 100D));
        edtJuros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtMulta.setFormatterFactory( SwingFormatterFactory.buildPorcentagem(0D, 100D));
        edtMulta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Condição de pagamento");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Desconto");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Juros");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Multa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(edtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(edtMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 419, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNomeCondicao)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtNomeCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setText("Obs : campos obrigatórios são assinalados com '*'");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDEscricaoForm, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDEscricaoForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void carregar(){
        this.condicaoPagamento.setNome(edtNomeCondicao.getText());
        this.condicaoPagamento.setQuantidadeParcelas(this.condicaoPagamento.getParcelas().size());
        this.condicaoPagamento.setJuros((Double) edtJuros.getValue());
        this.condicaoPagamento.setDesconto((Double) edtDesconto.getValue());
        this.condicaoPagamento.setMulta((Double) edtMulta.getValue());
    }

    public void carregarEdt(){
        this.edtNomeCondicao.setText(condicaoPagamento.getNome());
        this.edtMulta.setValue(condicaoPagamento.getMulta());
        this.edtDesconto.setValue(condicaoPagamento.getDesconto());
        this.edtJuros.setValue(condicaoPagamento.getJuros());
    }

    private void btnAddParcelaActionPerformed(java.awt.event.ActionEvent evt) {
        Parcela parcela = new Parcela();
        if ((Double)edtPorcentagem.getValue() * 100 > 100) {
            JOptionPane.showMessageDialog(this, "Valor de porcentagem da parcela é maior que o permitido (100%)");
            this.edtPorcentagem.requestFocus();
            return;
        }
        if ((Double)edtPorcentagem.getValue() * 100 < 1) {
            JOptionPane.showMessageDialog(this, "Valor de porcentagem da parcela é menor que o minimo permitido (1%)");
            this.edtPorcentagem.requestFocus();
            return;
        }
        if ((Integer) spnDiasParcela.getValue() < 0 ){
            JOptionPane.showMessageDialog(this, "Quantidade de dias menor que a permitida");
            this.spnDiasParcela.requestFocus();
            return;
        }

        parcela.setAtivo(true);
        parcela.setDias((Integer) spnDiasParcela.getValue());
        for (Parcela p : condicaoPagamento.getParcelas()){
            if (  parcela.getDias() <= p.getDias() ) {
                JOptionPane.showMessageDialog(this, "Dias da parcela adicionada devem ser maior que" +
                        " os dias da parcela anterior");
                return;
            }

        }
        if (formaPagamento == null){
            JOptionPane.showMessageDialog(this, "Selecione uma forma dde pagamento");
            return;
        }
        parcela.setFormaPagamento(formaPagamento);
        if (condicaoPagamento.getParcelas().isEmpty()){
            parcela.setNumero(1);
        } else {
            parcela.setNumero(condicaoPagamento.getParcelas().get(condicaoPagamento.getParcelas().size()-1).getNumero()+1);
        }

        parcela.setPorcentagem((Double) edtPorcentagem.getValue() * 100);
        this.condicaoPagamento.getParcelas().add(parcela);
        tableModelParcelaSimples.setList(this.condicaoPagamento.getParcelas().toArray());
    }                                         


    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        this.carregar();
        CondicaoPagamento condicao = null;
        try{
             condicao = new CondicaoPagamentoService().getByNome(this.condicaoPagamento.getNome());}
        catch (SQLException ex){
            JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
        }
        if (condicao != null){
             JOptionPane.showMessageDialog(this, "Nome da condição de pagamento ja esta em uso");
             edtNomeCondicao.requestFocus();
             return;
        }
        try {
            new CondicaoPagamentoService().save(condicaoPagamento);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Salvo com sucesso");
        dispose();
        callback.handle(condicaoPagamento);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRemoverParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverParcelaActionPerformed
       Parcela  parcela = new Parcela();
        try {
            parcela = this.condicaoPagamento.getParcelas().get(tblParcelas.getSelectedRow());
            this.condicaoPagamento.getParcelas().remove(parcela);
            new ParcelaService().deleteByID(parcela.getId());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        };
        tableModelParcelaSimples.setList(this.condicaoPagamento.getParcelas().toArray());

    }//GEN-LAST:event_btnRemoverParcelaActionPerformed

    private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpesquisarActionPerformed
        PesquisarFormaPagamento.Callback callback = (formaPagamento -> {
            if (formaPagamento!=null){
                if (formaPagamento ==null)
                    formaPagamento=new FormaPagamento() ;

               this.formaPagamento=formaPagamento;
               this.edtFormaPagamento.setText(formaPagamento.getNome());
               this.edtCoFormaPagamento.setText(formaPagamento.getId().toString());
            }
        });
        new PesquisarFormaPagamento(this, true, callback).show();
    }//GEN-LAST:event_btnpesquisarActionPerformed

    private void edtCoFormaPagamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCoFormaPagamentoKeyPressed
        if (evt.getKeyCode() == KeyCode.ENTER.impl_getCode()) {
            try {
                FormaPagamentoService formaPagamentoService =new FormaPagamentoService();
                FormaPagamento formaPagamento= (FormaPagamento) formaPagamentoService.getByID(Integer.parseInt(edtCoFormaPagamento.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if ( this.formaPagamento != null){
                this.formaPagamento = formaPagamento;
                this.edtFormaPagamento.setText(this.formaPagamento.getNome());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma forma de pagamento com esse código foi encontrado");
                edtCoFormaPagamento.setText(this.formaPagamento.getId().toString());
            }
        }
    }//GEN-LAST:event_edtCoFormaPagamentoKeyPressed

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
            java.util.logging.Logger.getLogger(CadastroCondicaoPagamentoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCondicaoPagamentoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCondicaoPagamentoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCondicaoPagamentoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroCondicaoPagamentoForm dialog = new CadastroCondicaoPagamentoForm(new javax.swing.JFrame(), true, new CondicaoPagamento(), null);
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
    private javax.swing.JButton btnAddParcela;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRemoverParcela;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnpesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField edtCoFormaPagamento;
    private javax.swing.JFormattedTextField edtDesconto;
    private javax.swing.JTextField edtFormaPagamento;
    private javax.swing.JFormattedTextField edtJuros;
    private javax.swing.JFormattedTextField edtMulta;
    private javax.swing.JTextField edtNomeCondicao;
    private javax.swing.JFormattedTextField edtNumeroParcela;
    private javax.swing.JFormattedTextField edtPorcentagem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblDEscricaoForm;
    private javax.swing.JSpinner spnDiasParcela;
    private javax.swing.JTable tblParcelas;
    // End of variables declaration//GEN-END:variables
}
