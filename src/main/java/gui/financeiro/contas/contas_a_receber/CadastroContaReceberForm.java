/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.financeiro.contas.contas_a_receber;

import gui.financeiro.contas.contas_a_pagar.*;
import gui.financeiro.formaPagamento.PesquisarFormaPagamento;
import gui.pessoas.fornecedores.PesquisarFornecedor;
import gui.swing.DialogPadrao;
import gui.swing.SwingFormatterFactory;
import javafx.scene.input.KeyCode;
import lib.model.financeiro.StatusConta;
import lib.model.financeiro.contas.ContaPagar;
import lib.model.financeiro.contas.ContaReceber;
import lib.model.financeiro.formaPagamento.FormaPagamento;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.model.pessoa.cliente.Cliente;
import lib.model.pessoa.fornecedor.Fornecedor;
import lib.service.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public class CadastroContaReceberForm extends DialogPadrao {

    /**
     * Creates new form CadastroContaPagarForm
     */

    /**
     * Creates new form CadastroContaReceberForm
     */
    public CadastroContaReceberForm(Window parent, boolean modal, ContaReceber contaReceber) {
        super(parent, modal, ModuloSistema.CONTAS_PAGAR, NivelAcessoModulo.LEITURA_GRAVACAO);
        initComponents();
        this.contaReceber = contaReceber;
        contaReceberService = new ContaReceberService();
        cmbStatus.setSelectedItem(contaReceber.getStatusConta());
    }

    private ContaReceber contaReceber;
    private ContaReceberService contaReceberService;

    public void bloqueiaEdt(){
        edtDescricao.setEnabled(false);
        edtCodFornecedor.setEnabled(false);
        edtCoFormaPagamento.setEnabled(false);
        edtDesconto.setEnabled(false);
        edtJuros.setEnabled(false);
        edtMulta.setEnabled(false);
        edtValor.setEnabled(false);
        edtDtLancamento.setEnabled(false);
        edtDtPagamento.setEnabled(false);
    }

    public void carregaEdt(){
        edtDescricao.setText(contaReceber.getDescricao());
        edtDesconto.setValue(contaReceber.getDesconto()/100);
        edtValor.setValue(contaReceber.getValor());
        edtMulta.setValue(contaReceber.getMulta()/100);
        edtJuros.setValue(contaReceber.getJuros()/100);
        edtDtPagamento.setDate(contaReceber.getDataPagamento());
        edtDtLancamento.setDate(contaReceber.getDataLancamento());
        edtDtVencimento.setDate(contaReceber.getDataVencimento());


        if (contaReceber.getFormaPagamento() != null) {
            edtCoFormaPagamento.setText(contaReceber.getFormaPagamento().getId().toString());
            edtFormaPagamento.setText(contaReceber.getFormaPagamento().getNome());
        }
        if (contaReceber.getRecebedor() != null) {
            edtFornecedor.setText(contaReceber.getRecebedor().getNome());
            edtCodFornecedor.setValue(contaReceber.getRecebedor().getId());
        }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        edtDesconto = new javax.swing.JFormattedTextField();
        edtJuros = new javax.swing.JFormattedTextField();
        edtMulta = new javax.swing.JFormattedTextField();
        edtFormaPagamento = new javax.swing.JTextField();
        edtCoFormaPagamento = new javax.swing.JTextField();
        btnpesquisar = new javax.swing.JButton();
        btnpesquisar.setContentAreaFilled(false);
        btnpesquisar.setOpaque(true);
        btnpesquisar.setBackground(Color.white);
        edtCodFornecedor = new javax.swing.JFormattedTextField();
        edtFornecedor = new javax.swing.JTextField();
        btnAlterarFornecedor = new javax.swing.JButton();
        btnAlterarFornecedor.setContentAreaFilled(false);
        btnAlterarFornecedor.setOpaque(true);
        btnAlterarFornecedor.setBackground(Color.white);
        edtDtLancamento = new com.toedter.calendar.JDateChooser();
        edtDtVencimento = new com.toedter.calendar.JDateChooser();
        edtDtPagamento = new com.toedter.calendar.JDateChooser();
        edtCod = new javax.swing.JTextField();
        edtDescricao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        edtValor = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        lblDescricaoForm = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de contas a pagar");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Descrição");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Forma de pagamento");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Desconto ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Juros");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Valor");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Multa");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Data de lançamento");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Data de vencimento");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Forneecedor");

        edtDesconto.setFormatterFactory( SwingFormatterFactory.buildPorcentagem(0D, 100D));
        edtDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtJuros.setFormatterFactory( SwingFormatterFactory.buildPorcentagem(0D, 100D));
        edtJuros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtMulta.setFormatterFactory( SwingFormatterFactory.buildPorcentagem(0D, 100D));
        edtMulta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        edtCodFornecedor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        edtCodFornecedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCodFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodFornecedorKeyPressed(evt);
            }
        });

        edtFornecedor.setEditable(false);
        edtFornecedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAlterarFornecedor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnAlterarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pesquisar.png"))); // NOI18N
        btnAlterarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarFornecedorActionPerformed(evt);
            }
        });

        edtDtLancamento.setEnabled(false);

        edtDtPagamento.setEnabled(false);

        edtCod.setEditable(false);
        edtCod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Data pagametno");

        edtValor.setFormatterFactory(  SwingFormatterFactory.buildMoeda(0D, null));
        edtValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Status");

        cmbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbStatus.setEnabled(false);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${statusContas}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, cmbStatus);
        bindingGroup.addBinding(jComboBoxBinding);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(521, 521, 521))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(edtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtDescricao))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(edtCoFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(edtFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnpesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel3))
                                                .addGap(55, 55, 55)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(edtCodFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(edtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel12))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAlterarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(edtDtVencimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(edtDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(edtJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(edtMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(edtDtLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(edtDtPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(edtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel12))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(edtFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtCoFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(edtFornecedor)
                                .addComponent(edtCodFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAlterarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnpesquisar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(edtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(edtMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(edtJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(edtDtVencimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(edtDtLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtDtPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStatus)))
                .addContainerGap())
        );

        lblDescricaoForm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDescricaoForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-cash-in-hand-30.png"))); // NOI18N
        lblDescricaoForm.setText("Nova conta a pagar");

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescricaoForm, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblDescricaoForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtCoFormaPagamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCoFormaPagamentoKeyPressed
        if (evt.getKeyCode() == KeyCode.ENTER.impl_getCode()) {
            FormaPagamento formaPagamento = null;
            try {
                FormaPagamentoService formaPagamentoService =new FormaPagamentoService();
                formaPagamento = (FormaPagamento) formaPagamentoService.getByID(Integer.parseInt(edtCoFormaPagamento.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if ( formaPagamento != null){
                this.contaReceber.setFormaPagamento(formaPagamento);
                this.edtFormaPagamento.setText(this.contaReceber.getFormaPagamento().getNome());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma forma de pagamento com esse código foi encontrado");
                if (contaReceber.getFormaPagamento() != null)
                    edtCoFormaPagamento.setText(this.contaReceber.getFormaPagamento().getId().toString());
            }
        }
    }//GEN-LAST:event_edtCoFormaPagamentoKeyPressed

    private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpesquisarActionPerformed
        PesquisarFormaPagamento.Callback callback = (formaPagamento -> {
            if (formaPagamento!=null){
                if (formaPagamento ==null)
                    formaPagamento=new FormaPagamento() ;

                this.contaReceber.setFormaPagamento(formaPagamento);
                this.edtFormaPagamento.setText(formaPagamento.getNome());
                this.edtCoFormaPagamento.setText(formaPagamento.getId().toString());
            }
        });
        new PesquisarFormaPagamento(this, true, callback).show();
    }//GEN-LAST:event_btnpesquisarActionPerformed

    private void edtCodFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodFornecedorKeyPressed
        if (evt.getKeyCode() == KeyCode.ENTER.impl_getCode()) {
            Cliente fornecedor = null;
            try {
                fornecedor = ((Cliente) new ClienteService().getByID(Integer.parseInt(edtCodFornecedor.getText())));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (fornecedor != null) {
                this.contaReceber.setRecebedor((Cliente) fornecedor);
                this.edtFornecedor.setText(fornecedor.getNome());
                this.contaReceber.setRecebedor(fornecedor);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhhum resultado encontrado");
            }
        }
    }//GEN-LAST:event_edtCodFornecedorKeyPressed

    private void btnAlterarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarFornecedorActionPerformed
        PesquisarFornecedor.Callback callback = (p) -> {
            if (p == null) {
                return;
            }
            this.contaReceber.setRecebedor((Cliente) p);
            this.edtFornecedor.setText(p.getNome());
            this.edtCodFornecedor.setText(p.getId().toString());
        };

        new PesquisarFornecedor(this, true, callback).show();
    }//GEN-LAST:event_btnAlterarFornecedorActionPerformed


    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (edtDescricao.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Descrição é obrigatória");
            edtDescricao.requestFocus();
            return;
        }
        if (contaReceber.getFormaPagamento() == null){
            JOptionPane.showMessageDialog(this, "Forma de pagamento é obrigatória.");
            edtCoFormaPagamento.requestFocus();
            return;
        }
        if (contaReceber.getRecebedor() == null){
            JOptionPane.showMessageDialog(this, "Fornecedor é obrigatório.");
            edtCodFornecedor.requestFocus();
            return;
        }
        contaReceber.setValor((Double) edtValor.getValue());
        contaReceber.setDescricao(edtDescricao.getText());
        contaReceber.setPaga(false);
        contaReceber.setDataVencimento(edtDtVencimento.getDate());
        contaReceber.setJuros((Double) edtJuros.getValue() * 100);
        contaReceber.setDesconto((Double) edtDesconto.getValue() * 100);
        contaReceber.setMulta((Double) edtMulta.getValue() * 100);
        if (contaReceber.getId() == null)
            contaReceber.setDataLancamento(new Date());

        try {
            if (contaReceber.getId() == null)
                new ContaReceberService().save(contaReceber);
            else
                new ContaReceberService().update(contaReceber);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Falha ao salvar :" + ex.getCause() + "\n" +ex.getMessage());
            return;
        }
        dispose();

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Com o cancelamento, nenhum dado alterado será salvo \n Deseja realmente cancelar o cadastro de conta a receber ? ",  "Atenção", JOptionPane.YES_NO_OPTION) ==0 ){
            dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    public List getStatusContas(){
        return Arrays.asList(StatusConta.values());
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarFornecedor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnpesquisar;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JTextField edtCoFormaPagamento;
    private javax.swing.JTextField edtCod;
    private javax.swing.JFormattedTextField edtCodFornecedor;
    private javax.swing.JFormattedTextField edtDesconto;
    private javax.swing.JTextField edtDescricao;
    private com.toedter.calendar.JDateChooser edtDtLancamento;
    private com.toedter.calendar.JDateChooser edtDtPagamento;
    private com.toedter.calendar.JDateChooser edtDtVencimento;
    private javax.swing.JTextField edtFormaPagamento;
    private javax.swing.JTextField edtFornecedor;
    private javax.swing.JFormattedTextField edtJuros;
    private javax.swing.JFormattedTextField edtMulta;
    private javax.swing.JFormattedTextField edtValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescricaoForm;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
