/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.produtos;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import gui.grupo.PesquisarGrupo;
import gui.marca.PesquisarMarca;

import gui.swing.DialogPadrao;
import gui.swing.DefaultComboBoxModel;
import gui.swing.SwingFormatterFactory;
import javafx.scene.input.KeyCode;
import lib.model.grupo.Grupo;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.model.marca.Marca;
import lib.model.produto.Produto;
import lib.service.GrupoService;
import lib.service.MarcaService;
import lib.service.PaisService;
import lib.service.ProdutoService;
import util.Util;

/**
 *
 * @author Diego
 */
public class CadastroProdutos extends DialogPadrao {

    private Produto produto;
    private ProdutoService produtoService;
    private DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>();
    private List<Marca> marcas;
    private Callback callback;


    /**
     * Creates new form CadastroProdutos
     */
    public CadastroProdutos(Window parent, boolean modal, Produto produto, Callback callback) {
        super(parent, modal, ModuloSistema.PRODUTOS, NivelAcessoModulo.LEITURA_GRAVACAO);

        initComponents();
       this.callback = callback;
        this.produto = produto;

        if (this.produto.getId() != null) {
            lblDescricaoFomr.setText("Alterar produto");
            this.carregarcampos();
        } else {
            rdAtivado.setEnabled(false);
            rdDesativado.setEnabled(false);
            rdAtivado.setSelected(true);
        }
        this.produtoService = new ProdutoService();
    }

    public interface Callback {
        void handle(Produto produto);
    }

    public void desbloqueiaedt() {
        btnsalvar.setEnabled(true);
    }

    public void bloqueiaedt() {
        edtCodGrupo.setEditable(false);
        edtCodMarca.setEditable(false);
        edtQtdEstoque.setEditable(false);
        edtReferencia.setEditable(false);
        edtUnidadeMedida.setEditable(false);
        edtdescricao.setEditable(false);
        edtMarca.setEditable(false);
        edtGrupo.setEditable(false);
        edtCodBarras.setEditable(false);
        btnsalvar.setEnabled(false);
    }

    public void carregarcampos() {
        this.edtcodigo.setText(produto.getId().toString());
        this.edtdescricao.setText(produto.getNome());
        this.edtprecocompra.setValue((Double) produto.getPrecoCompra());
        this.edtprecovenda.setValue((Double) produto.getValor());
        this.edtQuantidadeMinima.setValue(produto.getQuantidadeMinima());
        this.edtQtdEstoque.setValue(produto.getQuantidadeEstoque());
        edtcodigo.setText(produto.getId().toString());
        edtDtUltAtualizacao.setValue(Util.builDataSimples(produto.getDataUltimaAlteracao()));
        edtDtCadastro.setValue(Util.builDataSimples(produto.getDataCadastro()));
        if (produto.getAtivo())
            rdAtivado.setSelected(true);
        else
            rdDesativado.setSelected(true);
        if (produto.getUltimoFornecedor() != null){
            edtDtUltCompra.setValue(Util.builDataSimples(this.produto.getDataUltimaCompra()));
            edtUltFornecedor.setText(this.produto.getUltimoFornecedor().getId() + " " + this.produto.getUltimoFornecedor().getNome());
        }
        this.edtGrupo.setText(produto.getGrupo().getNome());
        this.edtMarca.setText(produto.getMarca().getNome());
        this.edtUnidadeMedida.setText(produto.getUnidadeMedida());
        this.edtCodBarras.setText(produto.getCodigoBarras());
        this.edtReferencia.setText(produto.getReferencia());
        this.edtCodMarca.setText(produto.getMarca().getId().toString());
        this.edtCodGrupo.setText(produto.getGrupo().getId().toString());

    }

    public void carregarproduto() {
        produto.setNome(this.edtdescricao.getText());
        produto.setQuantidadeEstoque((Double) edtQtdEstoque.getValue());
        produto.setQuantidadeMinima((Double) edtQuantidadeMinima.getValue());
        produto.setCodigoBarras(edtCodBarras.getText());
        if (produto.getId() == null) {
            produto.setDataCadastro(new Date(System.currentTimeMillis()));
            produto.setAtivo(true);
        }
        if (rdAtivado.isSelected())
            produto.setAtivo(true);
        else produto.setAtivo(false);
        produto.setDataUltimaAlteracao(new Date(System.currentTimeMillis()));
        produto.setValor((Double) edtprecovenda.getValue());
        produto.setUnidadeMedida(edtUnidadeMedida.getText());
        produto.setPrecoCompra((Double) edtprecocompra.getValue());
        produto.setReferencia(edtReferencia.getText());
        //fazer if pra validar numero, por que se não não faz a conversão
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        edtdescricao = new javax.swing.JTextField();
        edtcodigo = new javax.swing.JTextField();
        edtMarca = new javax.swing.JTextField();
        AltMarca = new javax.swing.JButton();
        AltMarca.setContentAreaFilled(false);
        AltMarca.setOpaque(true);
        AltMarca.setBackground(Color.white);
        edtGrupo = new javax.swing.JTextField();
        btnAltGrupo = new javax.swing.JButton();
        btnAltGrupo.setContentAreaFilled(false);         btnAltGrupo.setOpaque(true);         btnAltGrupo.setBackground(Color.white);
        edtprecocompra = new javax.swing.JFormattedTextField();
        edtprecovenda = new javax.swing.JFormattedTextField();
        edtUnidadeMedida = new javax.swing.JTextField();
        edtCodBarras = new javax.swing.JTextField();
        edtUltFornecedor = new javax.swing.JTextField();
        edtReferencia = new javax.swing.JTextField();
        edtCodGrupo = new javax.swing.JTextField();
        edtCodMarca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        edtQuantidadeMinima = new javax.swing.JFormattedTextField();
        edtQtdEstoque = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        edtDtCadastro = new javax.swing.JFormattedTextField();
        edtDtUltCompra = new javax.swing.JFormattedTextField();
        edtDtUltAtualizacao = new javax.swing.JFormattedTextField();
        btnsalvar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        lblDescricaoFomr = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rdDesativado = new javax.swing.JRadioButton();
        rdAtivado = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de produto");
        setModalExclusionType(null);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        edtdescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtdescricao.setAutoscrolls(false);

        edtcodigo.setEditable(false);
        edtcodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        edtcodigo.setAutoscrolls(false);

        edtMarca.setEditable(false);
        edtMarca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtMarca.setAutoscrolls(false);
        edtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtMarcaActionPerformed(evt);
            }
        });

        AltMarca.setBackground(new java.awt.Color(255, 255, 255));
        AltMarca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AltMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pesquisar.png"))); // NOI18N
        AltMarca.setBorder(null);
        AltMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AltMarcaActionPerformed(evt);
            }
        });

        edtGrupo.setEditable(false);
        edtGrupo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtGrupo.setAutoscrolls(false);
        edtGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtGrupoActionPerformed(evt);
            }
        });

        btnAltGrupo.setBackground(new java.awt.Color(255, 255, 255));
        btnAltGrupo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAltGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pesquisar.png"))); // NOI18N
        btnAltGrupo.setBorder(null);
        btnAltGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltGrupoActionPerformed(evt);
            }
        });

        edtprecocompra.setEditable(false);
        edtprecocompra.setFormatterFactory(SwingFormatterFactory.buildMoeda(0D, null));
        edtprecocompra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtprecovenda.setFormatterFactory(  SwingFormatterFactory.buildMoeda(0D, null));
        edtprecovenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtUnidadeMedida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtCodBarras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCodBarras.setAutoscrolls(false);

        edtUltFornecedor.setEditable(false);
        edtUltFornecedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtUltFornecedor.setAutoscrolls(false);

        edtReferencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtReferencia.setAutoscrolls(false);
        edtReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtReferenciaActionPerformed(evt);
            }
        });

        edtCodGrupo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCodGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodGrupoKeyPressed(evt);
            }
        });

        edtCodMarca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCodMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodMarcaKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Produto *");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Unm *");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Referencia");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Código de barras");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Preço *");

        edtQuantidadeMinima.setFormatterFactory(  SwingFormatterFactory.buildNumeroReal(0D, 1000000D)
        );
        edtQuantidadeMinima.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtQtdEstoque.setEditable(false);
        edtQtdEstoque.setFormatterFactory(  SwingFormatterFactory.buildNumeroReal(0D, null));
        edtQtdEstoque.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Quantidade minima");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Quantidade atual");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Cód");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Marca *");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Grupo *");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Cód");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Ultimo fornecedor");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Custo da ult compra");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Data cadastro");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Data da ult atualização");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Data da ult compra");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Funcionario da ultima atualização");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDtCadastro.setEditable(false);
        edtDtCadastro.setFormatterFactory(  SwingFormatterFactory.buildData());
        edtDtCadastro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDtUltCompra.setEditable(false);
        edtDtUltCompra.setFormatterFactory(  SwingFormatterFactory.buildData());
        edtDtUltCompra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDtUltAtualizacao.setEditable(false);
        edtDtUltAtualizacao.setFormatterFactory(  SwingFormatterFactory.buildData());
        edtDtUltAtualizacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(edtDtCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtDtUltAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(edtDtUltCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edtprecocompra))
                                .addGap(30, 30, 30)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(edtUltFornecedor)
                                .addGap(15, 15, 15))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(20, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(edtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(edtCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(edtprecovenda, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(edtQtdEstoque)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(edtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(edtdescricao)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edtUnidadeMedida)
                                    .addComponent(edtQuantidadeMinima)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtCodMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(edtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AltMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtCodGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(edtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAltGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtprecovenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtQuantidadeMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtQtdEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAltGrupo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AltMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtCodMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtCodGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtprecocompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtUltFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtDtUltCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtDtCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(edtDtUltAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
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

        lblDescricaoFomr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDescricaoFomr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/businesssettings_thebox_theproduct_negocio_2327.png"))); // NOI18N
        lblDescricaoFomr.setText("Novo produto");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Obs : campos obrigatórios são assinalados com '*'");

        buttonGroup1.add(rdDesativado);
        rdDesativado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdDesativado.setText("Desativado");

        buttonGroup1.add(rdAtivado);
        rdAtivado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdAtivado.setText("Ativado");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncancelar))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescricaoFomr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(16, 16, 16)
                        .addComponent(rdAtivado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdDesativado)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(rdAtivado)
                        .addComponent(rdDesativado))
                    .addComponent(lblDescricaoFomr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnsalvar)
                    .addComponent(btncancelar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAltGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltGrupoActionPerformed
        PesquisarGrupo.Callback callback = (grupoRetorno) -> {
            if (grupoRetorno == null) {

                return;
            }
            this.produto.setGrupo(grupoRetorno);
            this.edtGrupo.setText(grupoRetorno.getNome());
            this.edtCodGrupo.setText(grupoRetorno.getId().toString());
        };
        PesquisarGrupo pesquisarMarca = new PesquisarGrupo(this, true, callback);
        pesquisarMarca.show();
    }//GEN-LAST:event_btnAltGrupoActionPerformed

    private void edtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtMarcaActionPerformed

    private void edtCodGrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodGrupoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyCode.ENTER.impl_getCode()) {
            Grupo grupo = null;
            try {
                GrupoService grupoService = new GrupoService();
                grupo = (Grupo) grupoService.getByID(Integer.parseInt(edtCodGrupo.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (grupo != null) {
                produto.setGrupo(grupo);
                this.edtGrupo.setText(produto.getGrupo().getNome());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum grupo foi encontrado com esse código");
                edtCodGrupo.setText(produto.getGrupo().getId().toString());
            }
        }
    }//GEN-LAST:event_edtCodGrupoKeyPressed

    private void edtCodMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodMarcaKeyPressed
        if (evt.getKeyCode() == KeyCode.ENTER.impl_getCode()) {
            Marca marca = null;
            try {
                marca = (Marca) new MarcaService().getByID(Integer.parseInt(edtCodMarca.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (marca != null) {
                produto.setMarca(marca);
                this.edtMarca.setText(produto.getMarca().getNome());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum grupo foi encontrado com esse código");
                this.edtCodMarca.setText(this.produto.getMarca().getId().toString());
            }
        }
    }//GEN-LAST:event_edtCodMarcaKeyPressed

    private void edtReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtReferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtReferenciaActionPerformed

    private void edtGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtGrupoActionPerformed

    private void AltMarcaActionPerformed(java.awt.event.ActionEvent evt) {
        PesquisarMarca.Callback callback = (marcaRetorno) -> {
            if (marcaRetorno == null) {
                return;
            }
            this.produto.setMarca(marcaRetorno);
            this.edtMarca.setText(marcaRetorno.getNome());
            this.edtCodMarca.setText(marcaRetorno.getId().toString());
        };
        PesquisarMarca pesquisarMarca = new PesquisarMarca(this, true, callback);
        pesquisarMarca.show();

    }

    private void btnsalvarActionPerformed(java.awt.event.ActionEvent evt) {
        this.carregarproduto();
        if (produto.getNome().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Campo produto não informado");
            this.edtdescricao.requestFocus();
            return;
        }
        if (produto.getUnidadeMedida().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Campo unidade de mediade não informado");
            this.edtUnidadeMedida.requestFocus();
            return;
        }
        if (produto.getMarca() == null){
            JOptionPane.showMessageDialog(this, "Marca não informado");
            this.edtCodMarca.requestFocus();
            return;
        }
        if (produto.getGrupo() == null){
            JOptionPane.showMessageDialog(this, "Grupo do produto não informado");
            this.edtCodGrupo.requestFocus();
            return;
        }
	    try {
            if (produto.getId()== null){
                produtoService.save(produto);
        } else
            produtoService.update(produto);
        JOptionPane.showMessageDialog(this, "Salvo com sucesso");
        dispose();
        this.callback.handle(produto);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Falha ao salvar : " + e.getMessage());
        return;
    }

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
            java.util.logging.Logger.getLogger(CadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroProdutos dialog = new CadastroProdutos(new javax.swing.JFrame(), true, null, null);
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
    private javax.swing.JButton AltMarca;
    private javax.swing.JButton btnAltGrupo;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnsalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField edtCodBarras;
    private javax.swing.JTextField edtCodGrupo;
    private javax.swing.JTextField edtCodMarca;
    private javax.swing.JFormattedTextField edtDtCadastro;
    private javax.swing.JFormattedTextField edtDtUltAtualizacao;
    private javax.swing.JFormattedTextField edtDtUltCompra;
    private javax.swing.JTextField edtGrupo;
    private javax.swing.JTextField edtMarca;
    private javax.swing.JFormattedTextField edtQtdEstoque;
    private javax.swing.JFormattedTextField edtQuantidadeMinima;
    private javax.swing.JTextField edtReferencia;
    private javax.swing.JTextField edtUltFornecedor;
    private javax.swing.JTextField edtUnidadeMedida;
    private javax.swing.JTextField edtcodigo;
    private javax.swing.JTextField edtdescricao;
    private javax.swing.JFormattedTextField edtprecocompra;
    private javax.swing.JFormattedTextField edtprecovenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblDescricaoFomr;
    private javax.swing.JRadioButton rdAtivado;
    private javax.swing.JRadioButton rdDesativado;
    // End of variables declaration//GEN-END:variables
}
