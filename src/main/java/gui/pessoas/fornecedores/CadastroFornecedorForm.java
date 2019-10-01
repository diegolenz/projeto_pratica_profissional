/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.pessoas.fornecedores;

import gui.enderecos.cidades.PesquisarCidade;
import gui.financeiro.CondicaoPagamento.PesquisarCondicaoPagamento;
import gui.modeltable.TableModelCondicaoPagamento;
import gui.swing.DialogPadrao;
import gui.swing.SwingFormatterFactory;
import javafx.scene.input.KeyCode;
import lib.model.endereco.cidade.Cidade;
import lib.model.financeiro.CondicaoPagamento.CondicaoPagamento;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.model.pessoa.Sexo;
import lib.model.pessoa.TipoPessoa;
import lib.model.pessoa.fornecedor.Fornecedor;
import lib.service.CidadeService;
import lib.service.FornecedorService;
import util.validacao.ValidacaoEmail;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Diego
 */
public class CadastroFornecedorForm extends DialogPadrao {
    private Fornecedor pessoa;
    private Sexo ultimoSexoSelecionado;
    private TableModelCondicaoPagamento tableModelCondicaoPagamento;
    private List<CondicaoPagamento> condicoesRemovidas;
    private List<CondicaoPagamento> novasCondicoes;
    private List<CondicaoPagamento> condicaoPagamentos;


    /**
     * Creates new form NovaPessoaForm
     */
    public CadastroFornecedorForm(Window parent, boolean modal, Fornecedor pessoa) {
        super(parent, modal, ModuloSistema.PESSOAS, NivelAcessoModulo.LEITURA_GRAVACAO);
        initComponents();
        this.pessoa = pessoa;
        tableModelCondicaoPagamento = new TableModelCondicaoPagamento();
        this.tblCondicoesPagamentos.setModel(tableModelCondicaoPagamento);
        this.condicaoPagamentos = new ArrayList<>();
        this.condicoesRemovidas = new ArrayList<>();
        this.novasCondicoes = new ArrayList<>();
        if (this.pessoa.getId() != null)
            lblDescricaoTela1.setText("Alterar fornecedor");
        else {
            rdAtivado.setEnabled(false);
            rdAtivado.setSelected(true);
            rdDesativado.setEnabled(false);
            rdSexoMasculino.setSelected(true);
        }
    }

    public void bloqueiaedt() {
        this.txtCpf.setEditable(false);
        this.txtNome.setEditable(false);
        this.txtTel.setEditable(false);
        this.txtRg.setEditable(false);
        this.rdSexoFeminino.setEnabled(false);
        this.rdSexoMasculino.setEnabled(false);
        this.cmbtipocad.setEnabled(false);
        this.txtTelCelular.setEditable(false);
        this.txtEmail.setEnabled(false);
        this.edtCep.setEnabled(false);
        this.edtCodigo.setEnabled(false);
        this.edtLogradouro.setEnabled(false);
        this.edtCodCidade.setEnabled(false);
        this.txtApelidoNomeFantasia.setEnabled(false);
        this.btnsalvar.setEnabled(false);
        this.txtDtNasc.setEnabled(false);
        this.edtComplemento.setEditable(false);
        this.edtNumeroResidencial.setEditable(false);
        this.edtbairro.setEditable(false);
    }

    public void desbloqueiaedt() {
        this.txtCpf.setEditable(true);
        this.txtNome.setEditable(true);
        this.txtTel.setEditable(true);
        this.txtRg.setEditable(true);
        this.txtTelCelular.setEditable(true);
        this.cmbtipocad.setEnabled(true);
        txtEmail.setEnabled(true);
        btnsalvar.setEnabled(true);

    }

    public void carregaredt() {
        edtCodigo.setText(pessoa.getId().toString());
        txtNome.setText(pessoa.getNome());
        txtRg.setText(pessoa.getRgIe());
        this.edtCodigo.setText(pessoa.getId().toString());
        txtCpf.setText(pessoa.getCpfCnpj());
        txtApelidoNomeFantasia.setText(pessoa.getNomeFantasia_Apelido());
        txtTel.setText(pessoa.getTelefone());
        txtTelCelular.setText(pessoa.getTelefoneAlternativo());
        txtEmail.setText(pessoa.getEmail());
        txtDtNasc.setDate(pessoa.getDataNascimento());
        edtDtUltAtualizacao.setText(pessoa.getDataUltAlteracao().toString());
        edtDtCadastro.setText(pessoa.getDataCadastro().toString());
        edtCidade.setText(this.pessoa.getCidade().getNome());
        edtCodCidade.setText(this.pessoa.getCidade().getId().toString());
        edtEstado.setText(this.pessoa.getCidade().getEstado().getNome());
        edtPais.setText(this.pessoa.getCidade().getEstado().getPais().getNome());
        edtLogradouro.setText(this.pessoa.getLogradouro());
        edtbairro.setText(this.pessoa.getBairro());
        edtCep.setText(this.pessoa.getCep());
        edtNumeroResidencial.setValue(this.pessoa.getNumeroResidencial());
        if (this.pessoa.getSexo().equals(Sexo.MASCULINO) || this.pessoa.getId() != null)
            rdSexoMasculino.setSelected(true);
        else this.rdSexoFeminino.setSelected(true);

        if (this.pessoa.getAtivo()) {
            rdAtivado.setSelected(true);
        } else {
            rdDesativado.setSelected(true);
        }

        tableModelCondicaoPagamento.setList(pessoa.getCondicoesPagamentos().toArray());
        tblCondicoesPagamentos.setModel(tableModelCondicaoPagamento);
        condicaoPagamentos.addAll(pessoa.getCondicoesPagamentos());

        cmbtipocad.setSelectedItem(pessoa.getTipo());
    }

    public void carregapessoa() {
        pessoa.setNome(this.txtNome.getText());
        if (!txtCpf.getText().replaceAll("[^0-9]", "").trim().isEmpty())
            pessoa.setCpfCnpj(txtCpf.getText());
        pessoa.setDataNascimento(this.txtDtNasc.getDate());
        if (!txtRg.getText().replaceAll("[^0-9]", "").trim().isEmpty())
            pessoa.setRgIe(txtRg.getText());
        pessoa.setNomeFantasia_Apelido(txtApelidoNomeFantasia.getText());
        if (rdSexoMasculino.isSelected())
            pessoa.setSexo(Sexo.MASCULINO);
        else
            pessoa.setSexo(Sexo.FEMININO);

        if (cmbtipocad.getSelectedItem() == "FISICA") {
            pessoa.setTipo(TipoPessoa.FISICA);
        } else if (cmbtipocad.getSelectedItem() == "JURIDICA") {
            pessoa.setTipo(TipoPessoa.JURIDICA);
        } else pessoa.setTipo(TipoPessoa.ESTRANGEIRO);

        if (!txtTel.getText().replaceAll("[^0-9]", "").trim().isEmpty())
            pessoa.setTelefone(this.txtTel.getText());
        if (!txtTelCelular.getText().replaceAll("[^0-9]", "").trim().isEmpty())
            pessoa.setTelefoneAlternativo(this.txtTelCelular.getText());
        pessoa.setEmail(txtEmail.getText());
        pessoa.setNumeroResidencial(edtNumeroResidencial.getText());
        pessoa.setBairro(edtbairro.getText());
        if (!edtCep.getText().replaceAll("[^0-9]", "").trim().isEmpty())
            pessoa.setCep(edtCep.getText());
        pessoa.setLogradouro(edtLogradouro.getText());
        pessoa.setComplemento(edtComplemento.getText());

        if (pessoa.getId() == null)
            pessoa.setDataCadastro(new Date());
        pessoa.setDataUltAlteracao(new Date());

        pessoa.setAtivo(true);
        pessoa.setCondicoesPagamentos(condicaoPagamentos);

    }


    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbtipocad = new javax.swing.JComboBox<>();
        edtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtApelidoNomeFantasia = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCpf = new javax.swing.JFormattedTextField();
        edtCidade = new javax.swing.JTextField();
        edtCodCidade = new javax.swing.JTextField();
        btnAltCidade = new javax.swing.JButton();
        btnAltCidade.setContentAreaFilled(false); 
        btnAltCidade.setOpaque(true);  
        btnAltCidade.setBackground(Color.white);
        edtEstado = new javax.swing.JTextField();
        edtPais = new javax.swing.JTextField();
        edtbairro = new javax.swing.JTextField();
        edtCep = new javax.swing.JFormattedTextField(
            SwingFormatterFactory.buildCep()
        );
        edtLogradouro = new javax.swing.JTextField();
        txtRg = new javax.swing.JFormattedTextField();
        edtComplemento = new javax.swing.JTextField();
        txtTel = new javax.swing.JFormattedTextField();
        txtTelCelular = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        lblNomeCompleto = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblApelido = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblRgIE = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblDtNascimento = new javax.swing.JLabel();
        rdSexoMasculino = new javax.swing.JRadioButton();
        rdSexoFeminino = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        txtDtNasc = new com.toedter.calendar.JDateChooser();
        lblCpfCnpj = new javax.swing.JLabel();
        edtNumeroResidencial = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCondicoesPagamentos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContatos = new javax.swing.JTable();
        btnRemoverCondicao = new javax.swing.JButton();
        btnAddCondicao = new javax.swing.JButton();
        btnRemoverContato = new javax.swing.JButton();
        btnAddContato = new javax.swing.JButton();
        lblDescricaoTela1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btncancelar = new javax.swing.JButton();
        btnsalvar = new javax.swing.JButton();
        rdAtivado = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        rdDesativado = new javax.swing.JRadioButton();
        edtDtCadastro = new javax.swing.JTextField();
        edtDtUltAtualizacao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cmbtipocad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbtipocad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FISICA", "JURIDICA", "ESTRANGEIRO" }));
        cmbtipocad.setMinimumSize(new java.awt.Dimension(128, 40));
        cmbtipocad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtipocadActionPerformed(evt);
            }
        });

        edtCodigo.setEditable(false);
        edtCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCodigo.setPreferredSize(new java.awt.Dimension(16, 40));

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNome.setMinimumSize(new java.awt.Dimension(16, 40));

        txtApelidoNomeFantasia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApelidoNomeFantasia.setMinimumSize(new java.awt.Dimension(16, 40));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtCidade.setEditable(false);
        edtCidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtCodCidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCodCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodCidadeKeyPressed(evt);
            }
        });

        btnAltCidade.setBackground(new java.awt.Color(255, 255, 255));
        btnAltCidade.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnAltCidade.setForeground(new java.awt.Color(255, 255, 255));
        btnAltCidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pesquisar.png"))); // NOI18N
        btnAltCidade.setBorder(null);
        btnAltCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltCidadeActionPerformed(evt);
            }
        });

        edtEstado.setEditable(false);
        edtEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtPais.setEditable(false);
        edtPais.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtbairro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            edtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edtCep.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtLogradouro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtRg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtComplemento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtTelCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelCelular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Numero *");

        lblNomeCompleto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNomeCompleto.setText("Nome completo *");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Código");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Logradouro *");

        lblApelido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblApelido.setText("Apelido");

        lblSexo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSexo.setText("Sexo *");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tipo *");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("CEP *");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Complemento ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("País *");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Cód *");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Estado *");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Bairro");

        lblRgIE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRgIE.setText("RG ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Telefone celular");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Email ");

        lblDtNascimento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDtNascimento.setText("Data de nascimento");

        rdSexoMasculino.setBackground(new java.awt.Color(255, 255, 255));
        rdSexoMasculino.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdSexoMasculino.setText("Masculino");
        rdSexoMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSexoMasculinoActionPerformed(evt);
            }
        });

        rdSexoFeminino.setBackground(new java.awt.Color(255, 255, 255));
        rdSexoFeminino.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdSexoFeminino.setText("Feminino");
        rdSexoFeminino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSexoFemininoActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Telefone ");

        txtDtNasc.setBackground(new java.awt.Color(255, 255, 255));
        txtDtNasc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblCpfCnpj.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCpfCnpj.setText("CPF *");

        edtNumeroResidencial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Cidade *");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Condições de pagamento");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Contatos");

        tblCondicoesPagamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCondicoesPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblCondicoesPagamentos);

        tblContatos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblContatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblContatos);

        btnRemoverCondicao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnRemoverCondicao.setText("-");
        btnRemoverCondicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverCondicaoActionPerformed(evt);
            }
        });

        btnAddCondicao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddCondicao.setText("+");
        btnAddCondicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCondicaoActionPerformed(evt);
            }
        });

        btnRemoverContato.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnRemoverContato.setText("-");
        btnRemoverContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverContatoActionPerformed(evt);
            }
        });

        btnAddContato.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddContato.setText("+");
        btnAddContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddCondicao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoverCondicao))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddContato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoverContato))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbtipocad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(edtCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAltCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(edtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(edtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(edtNumeroResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(edtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(541, 541, 541)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNomeCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApelidoNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApelido, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdSexoMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(rdSexoFeminino, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCpfCnpj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(103, 103, 103))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRgIE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSexo)
                        .addComponent(lblApelido)
                        .addComponent(lblNomeCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbtipocad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtApelidoNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdSexoMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdSexoFeminino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(jLabel6)
                        .addComponent(jLabel16))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNumeroResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jLabel24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(edtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(edtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnAltCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addGap(2, 2, 2))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRgIE)
                            .addComponent(lblDtNascimento)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCpfCnpj)
                        .addComponent(jLabel20)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRemoverCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRemoverContato, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddContato, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblDescricaoTela1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDescricaoTela1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/business_application_addmale_useradd_insert_add_user_client_2312.png"))); // NOI18N
        lblDescricaoTela1.setText("Novo fornecedor");

        jLabel2.setText("Obs : campos obrigatórios são assinalados com '*'");

        btncancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btncancelar.setText("cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnsalvar.setText("salvar");
        btnsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalvarActionPerformed(evt);
            }
        });

        rdAtivado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdAtivado.setText("Ativado");
        rdAtivado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAtivadoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Status :");

        rdDesativado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdDesativado.setText("Desativado");
        rdDesativado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDesativadoActionPerformed(evt);
            }
        });

        edtDtCadastro.setEditable(false);
        edtDtCadastro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edtDtUltAtualizacao.setEditable(false);
        edtDtUltAtualizacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Data de cadastro");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Data da ultima alteração");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescricaoTela1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(527, 527, 527)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdAtivado)
                                .addGap(0, 0, 0)
                                .addComponent(rdDesativado))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtDtCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(edtDtUltAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescricaoTela1)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel3))
                            .addComponent(rdAtivado)
                            .addComponent(rdDesativado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtDtUltAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtDtCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalvarActionPerformed
        this.carregapessoa();

        if (pessoa.getNome().trim().isEmpty()) {
            if (!pessoa.getTipo().equals(TipoPessoa.JURIDICA)) {
                JOptionPane.showMessageDialog(this, "Nome não informado");
                this.txtNome.requestFocus();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Razão social não informado");
                this.txtNome.requestFocus();
                return;
            }
        }

        if (pessoa.getTipo().equals(TipoPessoa.JURIDICA) && pessoa.getNomeFantasia_Apelido().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome fantasia não informado");
            this.txtApelidoNomeFantasia.requestFocus();
            return;
        }
        if (pessoa.getLogradouro().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Logradouro não informado");
            this.edtLogradouro.requestFocus();
            return;
        }
        if (pessoa.getNumeroResidencial().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Numero não informado");
            this.edtNumeroResidencial.requestFocus();
            return;
        }
        if (pessoa.getCep().replaceAll("[^0-9]", "").trim().isEmpty() && !pessoa.getTipo().equals(TipoPessoa.ESTRANGEIRO)) {
            JOptionPane.showMessageDialog(this, "CEP não informado");
            this.edtCep.requestFocus();
            return;
        }
        if (pessoa.getCidade() == null) {
            JOptionPane.showMessageDialog(this, "Cidade não informada");
            this.edtCodCidade.requestFocus();
            return;
        }
        if (pessoa.getTelefone().replaceAll("[^0-9]", "").trim().isEmpty() && pessoa.getTelefoneAlternativo().replaceAll("[^0-9]", "").trim().isEmpty() &&
                pessoa.getEmail().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ao menos um contato deve ser informado, telefone, celular ou email");
            this.txtTel.requestFocus();
            return;
        }
        if (!pessoa.getEmail().trim().isEmpty()) {
           if (! ValidacaoEmail.isValidEmailAddress(pessoa.getEmail())) {
                JOptionPane.showMessageDialog(this, "Email informado não é valido ");
                this.txtEmail.requestFocus();
                return;
            }
        }
        if (pessoa.getCpfCnpj().trim().isEmpty()) {
            if (pessoa.getTipo().equals(TipoPessoa.FISICA))
                JOptionPane.showMessageDialog(this, "Campo CPF não informado");
            else if (pessoa.getTipo().equals(TipoPessoa.JURIDICA))
                JOptionPane.showMessageDialog(this, "Campo CNPJ não informado");
            else
                JOptionPane.showMessageDialog(this, "Campo Documento não informado");
            txtCpf.requestFocus();
            return;
        }

        if (pessoa.getDataNascimento() != null && pessoa.getDataNascimento().after(new Date())) {
            JOptionPane.showMessageDialog(
                    this,
                    "A data de fundação não pode ser maior que a data atual"
            );
            return;
        }

        try {
            FornecedorService fornecedorService = new FornecedorService();
            if (pessoa.getId() == null) {
                fornecedorService.save(pessoa);
            } else {
                fornecedorService.update(pessoa);
                if (!condicoesRemovidas.isEmpty())
                    fornecedorService.deleteCondicoes(condicoesRemovidas, pessoa.getId());
                if (!novasCondicoes.isEmpty())
                    fornecedorService.salvarCondicoes(novasCondicoes, pessoa.getId());

            }
            JOptionPane.showMessageDialog(this, "salvo com sucesso");
            dispose();
        } catch (
                Exception ex) {
            JOptionPane.showMessageDialog(this, "falha ao salvar, " + ex.getMessage());
        }

    }//GEN-LAST:event_btnsalvarActionPerformed

    private void btnAltCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltCidadeActionPerformed
        PesquisarCidade.Callback callback = (cidade -> {
            if (cidade != null) {
                pessoa.setCidade(cidade);
                edtCidade.setText(cidade.getNome());
                edtCodCidade.setText(cidade.getId().toString());
                edtPais.setText(cidade.getEstado().getPais().getNome());
                edtEstado.setText(cidade.getEstado().getNome());
            }
        });
        new PesquisarCidade(this, true, callback).show();
    }//GEN-LAST:event_btnAltCidadeActionPerformed

    private void edtCodCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodCidadeKeyPressed
        if (evt.getKeyCode() == KeyCode.ENTER.impl_getCode()) {
            try {
                CidadeService cidadeService = new CidadeService();
                this.pessoa.setCidade((Cidade) cidadeService.getCidadeByID(Integer.parseInt(edtCodCidade.getText())));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (this.pessoa.getCidade() != null) {
                this.edtCidade.setText(this.pessoa.getCidade().getNome());
                this.edtEstado.setText(this.pessoa.getCidade().getEstado().getNome());
                this.edtPais.setText(this.pessoa.getCidade().getEstado().getPais().getNome());
            }
        }
    }//GEN-LAST:event_edtCodCidadeKeyPressed

    private void rdDesativadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDesativadoActionPerformed
        if (!rdDesativado.isSelected())
            rdDesativado.setSelected(true);
        rdAtivado.setSelected(false);
    }//GEN-LAST:event_rdDesativadoActionPerformed

    private void rdAtivadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAtivadoActionPerformed
        if (!rdAtivado.isSelected())
            rdAtivado.setSelected(true);
        rdDesativado.setSelected(false);
    }//GEN-LAST:event_rdAtivadoActionPerformed

    private void rdSexoMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSexoMasculinoActionPerformed
        if (!rdSexoMasculino.isSelected())
            rdSexoMasculino.setSelected(true);
        rdSexoFeminino.setSelected(false);
    }//GEN-LAST:event_rdSexoMasculinoActionPerformed

    private void rdSexoFemininoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSexoFemininoActionPerformed
        if (!rdSexoFeminino.isSelected())
            rdSexoFeminino.setSelected(true);
        rdSexoMasculino.setSelected(false);
    }//GEN-LAST:event_rdSexoFemininoActionPerformed

    private void btnAddCondicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCondicaoActionPerformed
        PesquisarCondicaoPagamento.Callback callback = (condicao) -> {
            if (condicao == null){
                return ;
            }
            if (condicaoPagamentos.contains(condicao)){
                JOptionPane.showMessageDialog(this, "Condição já selecionada");
                return;
            }
            condicaoPagamentos.add(condicao);
            if (!pessoa.getCondicoesPagamentos().contains(condicao))
                novasCondicoes.add(condicao);
            this.tableModelCondicaoPagamento.setList(condicaoPagamentos.toArray());
            this.tblCondicoesPagamentos.setModel(tableModelCondicaoPagamento);
        };

        new PesquisarCondicaoPagamento(this, true, callback ).show();
    }//GEN-LAST:event_btnAddCondicaoActionPerformed

    private void btnRemoverCondicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverCondicaoActionPerformed
        CondicaoPagamento condicaoPagamento = this.condicaoPagamentos.remove(this.tblCondicoesPagamentos.getSelectedRow());
       condicaoPagamentos.remove(condicaoPagamento);
        if (pessoa.getCondicoesPagamentos().contains(condicaoPagamento)){
            if (condicoesRemovidas == null)
                condicaoPagamento = new CondicaoPagamento();
            condicoesRemovidas.add(condicaoPagamento);
        }
        this.tableModelCondicaoPagamento.setList(condicaoPagamentos.toArray());
        this.tblCondicoesPagamentos.setModel(tableModelCondicaoPagamento);
    }//GEN-LAST:event_btnRemoverCondicaoActionPerformed

    private void btnAddContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddContatoActionPerformed

    private void btnRemoverContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverContatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoverContatoActionPerformed

    private Fornecedor getPessoa() {
        return pessoa;
    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "nenhum dado alterado será salvo, deseja mesmo cancelar?", "CANCELAR", JOptionPane.YES_NO_OPTION) == 0) {
            dispose();
        }
        ;
    }

    private void cmbtipocadActionPerformed(java.awt.event.ActionEvent evt) {
        if (cmbtipocad.getSelectedItem() == "FISICA") {
            lblApelido.setText("Apelido");
            lblNomeCompleto.setText("Nome completo *");
            lblCpfCnpj.setText("CPF *");
            lblDtNascimento.setText("Data de nascimento");
            lblRgIE.setText("RG");
            lblSexo.setVisible(true);
            try {
                edtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
                txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
                txtRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            rdSexoFeminino.setEnabled(true);
            rdSexoMasculino.setEnabled(true);
            rdSexoFeminino.setVisible(false);
            rdSexoMasculino.setVisible(false);
            if (ultimoSexoSelecionado.equals(Sexo.MASCULINO))
                rdSexoMasculino.setSelected(true);
            else
                rdSexoFeminino.setSelected(true);
            lblSexo.setEnabled(true);

        } else if (cmbtipocad.getSelectedItem() == "ESTRANGEIRO") {
            lblApelido.setText("Apelido");
            lblNomeCompleto.setText("Nome completo *");
            lblCpfCnpj.setText("Documento *");
            lblDtNascimento.setText("Data de nascimento");
            lblRgIE.setText("Documento");
            lblSexo.setVisible(true);

            edtCep.setFormatterFactory(null);
            txtCpf.setFormatterFactory(null);
            txtRg.setFormatterFactory(null);
            txtCpf.setText("");
            txtRg.setText("");
            edtCep.setText("");
            rdSexoFeminino.setVisible(false);
            rdSexoMasculino.setVisible(false);
            rdSexoFeminino.setEnabled(true);
            rdSexoMasculino.setEnabled(true);
            lblSexo.setEnabled(true);
            if (ultimoSexoSelecionado.equals(Sexo.MASCULINO))
                rdSexoMasculino.setSelected(true);
            else
                rdSexoFeminino.setSelected(true);

        } else {
            lblApelido.setText("Nome fantasia *");
            lblNomeCompleto.setText("razão Social *");
            lblCpfCnpj.setText("CNPJ *");
            lblDtNascimento.setText("Data de fundação");
            lblRgIE.setText("IE");
            try {
                edtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
                txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
                txtRg.setFormatterFactory(null);
                txtRg.setText("");

            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }

            rdSexoFeminino.setEnabled(false);
            rdSexoFeminino.setVisible(false);
            rdSexoMasculino.setVisible(false);
            rdSexoMasculino.setEnabled(false);
            lblSexo.setVisible(false);
            if (rdSexoFeminino.isSelected())
                ultimoSexoSelecionado = Sexo.FEMININO;
            else ultimoSexoSelecionado = Sexo.MASCULINO;
            rdSexoMasculino.setSelected(false);
            rdSexoFeminino.setSelected(false);
            lblSexo.setEnabled(false);
        }
        this.txtRg.revalidate();
        this.txtCpf.revalidate();
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
            java.util.logging.Logger.getLogger(CadastroFornecedorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroFornecedorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroFornecedorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroFornecedorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroFornecedorForm dialog = new CadastroFornecedorForm(new javax.swing.JDialog(), true, null);
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
    private javax.swing.JButton btnAddCondicao;
    private javax.swing.JButton btnAddContato;
    private javax.swing.JButton btnAltCidade;
    private javax.swing.JButton btnRemoverCondicao;
    private javax.swing.JButton btnRemoverContato;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnsalvar;
    private javax.swing.JComboBox<String> cmbtipocad;
    private javax.swing.JFormattedTextField edtCep;
    private javax.swing.JTextField edtCidade;
    private javax.swing.JTextField edtCodCidade;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtComplemento;
    private javax.swing.JTextField edtDtCadastro;
    private javax.swing.JTextField edtDtUltAtualizacao;
    private javax.swing.JTextField edtEstado;
    private javax.swing.JTextField edtLogradouro;
    private javax.swing.JFormattedTextField edtNumeroResidencial;
    private javax.swing.JTextField edtPais;
    private javax.swing.JTextField edtbairro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApelido;
    private javax.swing.JLabel lblCpfCnpj;
    private javax.swing.JLabel lblDescricaoTela1;
    private javax.swing.JLabel lblDtNascimento;
    private javax.swing.JLabel lblNomeCompleto;
    private javax.swing.JLabel lblRgIE;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JRadioButton rdAtivado;
    private javax.swing.JRadioButton rdDesativado;
    private javax.swing.JRadioButton rdSexoFeminino;
    private javax.swing.JRadioButton rdSexoMasculino;
    private javax.swing.JTable tblCondicoesPagamentos;
    private javax.swing.JTable tblContatos;
    private javax.swing.JTextField txtApelidoNomeFantasia;
    private javax.swing.JFormattedTextField txtCpf;
    private com.toedter.calendar.JDateChooser txtDtNasc;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtTel;
    private javax.swing.JFormattedTextField txtTelCelular;
    // End of variables declaration//GEN-END:variables

}
