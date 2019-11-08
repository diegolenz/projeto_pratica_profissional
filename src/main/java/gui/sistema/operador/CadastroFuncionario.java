/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.sistema.operador;

import com.jidesoft.dialog.JideOptionPane;
import gui.enderecos.cidades.PesquisarCidade;
import gui.modeltable.TableModelTipoOperador;
import gui.sistema.PesquisarGrupoOperador;
import gui.swing.DialogPadrao;
import gui.swing.SwingFormatterFactory;
import javafx.scene.input.KeyCode;
import lib.model.endereco.cidade.Cidade;
import lib.model.interno.Funcionario;
import lib.model.interno.GrupoFuncionario;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.model.pessoa.Sexo;
import lib.model.pessoa.TipoPessoa;
import lib.service.CidadeService;
import lib.service.FuncionarioService;
import util.validacao.ValidacaoEmail;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @author Diego
 */
public class CadastroFuncionario extends DialogPadrao {
    private Funcionario funcionario;
    private FuncionarioService funcionarioService;
    private List<GrupoFuncionario> gruposRemover;
    private List<GrupoFuncionario> gruposAdicionar;
    private TableModelTipoOperador tableModelTipoOperador;


    /**
     * Creates new form CadastroFuncionario
     */
    public CadastroFuncionario(Window parent, boolean modal, Funcionario funcionario) {
        super(parent, modal, ModuloSistema.SISTEMA_OPERADORES, NivelAcessoModulo.LEITURA_GRAVACAO);
        initComponents();
        this.funcionario = funcionario;
        this.funcionarioService = new FuncionarioService();
        this.gruposRemover = new ArrayList<>();
        this.gruposAdicionar = new ArrayList<>();
        tableModelTipoOperador = new TableModelTipoOperador();
        if (funcionario.getId() == null) {
            rdAtivado.setEnabled(false);
            rdDesativado.setEnabled(false);
        }
    }

    public void bloqueiaedt() {
        this.txtCpf.setEditable(false);
        this.txtNome.setEditable(false);
        this.txtTel.setEditable(false);
        this.txtRg.setEditable(false);
        this.rdSexoFeminino.setEnabled(false);
        this.rdSexoMasculino.setEnabled(false);

        this.txtTelCelular.setEditable(false);
        this.txtEmail.setEnabled(false);
        this.edtCep.setEnabled(false);
        this.edtCodigo.setEnabled(false);
        this.edtLogradouro.setEnabled(false);
        this.edtCodCidade.setEnabled(false);

        this.btnsalvar.setVisible(false);
        this.txtDtNasc.setEnabled(false);
        this.edtComplemento.setEditable(false);
        this.edtNumeroResidencial.setEditable(false);
        this.edtbairro.setEditable(false);
        this.edtSenha.setEnabled(false);
        this.edtConfirmacaoSenha.setEnabled(false);
        this.edtUsuario.setEnabled(false);
        this.btnAddGrupo.setVisible(false);
        this.btnsalvar.setVisible(false);
        this.btncancelar.setVisible(false);
        this.dtDemissao.setEnabled(false);
        this.dtAdmissao.setEnabled(false);
        this.btnRemoverGrupoo.setVisible(false);
    }

    public void carregaredt() {
        edtCodigo.setText(funcionario.getId().toString());
        txtNome.setText(funcionario.getNome());
        txtRg.setText(funcionario.getRgIe());
        this.edtCodigo.setText(funcionario.getId().toString());
        txtCpf.setText(funcionario.getCpfCnpj());
        txtTel.setText(funcionario.getTelefone());
        txtTelCelular.setText(funcionario.getTelefoneAlternativo());
        txtEmail.setText(funcionario.getEmail());
        txtDtNasc.setDate(funcionario.getDataNascimento());
        edtDtUltAtualizacao.setText(funcionario.getDataUltAlteracao().toString());
        edtDtCadastro.setText(funcionario.getDataCadastro().toString());
        edtCidade.setText(this.funcionario.getCidade().getNome());
        edtCodCidade.setText(this.funcionario.getCidade().getId().toString());
        edtEstado.setText(this.funcionario.getCidade().getEstado().getNome());
        edtPais.setText(this.funcionario.getCidade().getEstado().getPais().getNome());
        edtLogradouro.setText(this.funcionario.getLogradouro());
        edtbairro.setText(this.funcionario.getBairro());
        edtCep.setText(this.funcionario.getCep());
        edtNumeroResidencial.setValue(this.funcionario.getNumeroResidencial());
        if (this.funcionario.getId() != null || this.funcionario.getSexo().equals(Sexo.MASCULINO))
            rdSexoMasculino.setSelected(true);
        else if (this.funcionario.getSexo().equals(Sexo.FEMININO)) this.rdSexoFeminino.setSelected(true);

        if (this.funcionario.isAtivo()) {
            rdAtivado.setSelected(true);
        } else {
            rdDesativado.setSelected(true);
        }

        this.edtSenha.setText(funcionario.getSenha());
        this.edtUsuario.setText(funcionario.getUsuario());
        if (funcionario.getDataDemissao() != null)
        this.dtDemissao.setDate(funcionario.getDataDemissao());
        this.dtAdmissao.setDate(funcionario.getDataAdmissao());
        tableModelTipoOperador.setList(funcionario.getGrupos().toArray());
        tblGrupos.setModel(tableModelTipoOperador);

    }

    public void carregafuncionario() {
        funcionario.setNome(this.txtNome.getText());
        if (!txtCpf.getText().replaceAll("[^0-9]", "").trim().isEmpty())
            funcionario.setCpfCnpj(txtCpf.getText());
        funcionario.setDataNascimento(this.txtDtNasc.getDate());
        if (!txtRg.getText().replaceAll("[^0-9]", "").trim().isEmpty())
            funcionario.setRgIe(txtRg.getText());
        if (rdSexoMasculino.isSelected())
            funcionario.setSexo(Sexo.MASCULINO);
        else
            funcionario.setSexo(Sexo.FEMININO);

        funcionario.setTelefone(this.txtTel.getText());
        funcionario.setTelefoneAlternativo(this.txtTelCelular.getText());
        funcionario.setEmail(txtEmail.getText());

        funcionario.setNumeroResidencial(edtNumeroResidencial.getText());
        funcionario.setBairro(edtbairro.getText());
        if (!edtCep.getText().replaceAll("[^0-9]", "").trim().isEmpty())
            funcionario.setCep(edtCep.getText());
        funcionario.setLogradouro(edtLogradouro.getText());
        funcionario.setComplemento(edtComplemento.getText());

        if (funcionario.getId() == null)
            funcionario.setDataCadastro(new Date());
        funcionario.setDataUltAlteracao(new Date());
        if (rdAtivado.isSelected())
            funcionario.setAtivo(true);
        else
            funcionario.setAtivo(false);
        funcionario.setSenha(edtSenha.getText());
        funcionario.setUsuario(edtUsuario.getText());
        funcionario.setDataAdmissao(dtAdmissao.getDate());
        funcionario.setDataDemissao(dtDemissao.getDate());
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPasswordField1 = new javax.swing.JPasswordField();
        lblDescricaoTela = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btncancelar = new javax.swing.JButton();
        btnsalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        edtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
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
        jLabel4 = new javax.swing.JLabel();
        lblNomeCompleto1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblSexo1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblRgIe1 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblDataNascimento1 = new javax.swing.JLabel();
        rdSexoMasculino = new javax.swing.JRadioButton();
        rdSexoFeminino = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        txtDtNasc = new com.toedter.calendar.JDateChooser();
        lblCpfCnpj1 = new javax.swing.JLabel();
        edtNumeroResidencial = new javax.swing.JFormattedTextField();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGrupos = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        dtAdmissao = new com.toedter.calendar.JDateChooser();
        dtDemissao = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edtDtCadastro = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        edtDtUltAtualizacao = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnAddGrupo = new javax.swing.JButton();
        btnRemoverGrupoo = new javax.swing.JButton();
        edtConfirmacaoSenha = new javax.swing.JPasswordField();
        jLabel38 = new javax.swing.JLabel();
        edtSenha = new javax.swing.JPasswordField();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        edtUsuario = new javax.swing.JTextField();
        chkAlterarSenhaProximoLogin = new javax.swing.JCheckBox();
        rdAtivado = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        rdDesativado = new javax.swing.JRadioButton();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de funcionario");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        lblDescricaoTela.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDescricaoTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/business_application_addmale_useradd_insert_add_user_client_2312.png"))); // NOI18N
        lblDescricaoTela.setText("Novo funcionario");

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        edtCodigo.setEditable(false);
        edtCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCodigo.setPreferredSize(new java.awt.Dimension(16, 40));

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNome.setMinimumSize(new java.awt.Dimension(16, 40));

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
        btnAltCidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
            txtTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtTelCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelCelular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Numero *");

        lblNomeCompleto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNomeCompleto1.setText("Nome completo *");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Código");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Logradouro *");

        lblSexo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSexo1.setText("Sexo");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("CEP *");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Complemento");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("País *");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Cód ");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Estado *");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Bairro");

        lblRgIe1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRgIe1.setText("RG");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Telefone celular *");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Email");

        lblDataNascimento1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDataNascimento1.setText("Data de nascimento");

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

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Telefone *");

        txtDtNasc.setBackground(new java.awt.Color(255, 255, 255));
        txtDtNasc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblCpfCnpj1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCpfCnpj1.setText("CPF *");

        edtNumeroResidencial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Cidade *");

        tblGrupos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblGrupos);

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("Grupos");

        dtAdmissao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        dtDemissao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Data admissão");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Data demissão");

        edtDtCadastro.setEditable(false);
        edtDtCadastro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Dt. cadastro");

        edtDtUltAtualizacao.setEditable(false);
        edtDtUltAtualizacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Dt. ultima atualização");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Funcionario do cadastro");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Funcionario da ultima atualização");

        btnAddGrupo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddGrupo.setText("+");
        btnAddGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGrupoActionPerformed(evt);
            }
        });

        btnRemoverGrupoo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnRemoverGrupoo.setText("-");
        btnRemoverGrupoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverGrupooActionPerformed(evt);
            }
        });

        edtConfirmacaoSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("Confirmação de senha");

        edtSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("Senha ");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Usuário");

        edtUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        chkAlterarSenhaProximoLogin.setBackground(new java.awt.Color(255, 255, 255));
        chkAlterarSenhaProximoLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkAlterarSenhaProximoLogin.setText("Alterar a senha no próximo login");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(edtLogradouro)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(edtNumeroResidencial)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(edtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(249, 249, 249)
                                .addComponent(btnAddGrupo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoverGrupoo)
                                .addGap(380, 380, 380))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTelCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTel)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCpf)
                                            .addComponent(lblCpfCnpj1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtRg)
                                            .addComponent(lblRgIe1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblDataNascimento1)))
                                .addGap(0, 24, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(lblNomeCompleto1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(105, 105, 105))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(rdSexoMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rdSexoFeminino))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(edtCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAltCidade)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(edtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtConfirmacaoSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(edtDtCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(4, 4, 4)))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(edtDtUltAtualizacao)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkAlterarSenhaProximoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(dtAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(dtDemissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(79, 79, 79))
                                    .addComponent(txtDtNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNomeCompleto1)
                        .addGap(5, 5, 5)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblSexo1)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdSexoMasculino)
                            .addComponent(rdSexoFeminino))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jLabel4)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(jLabel32)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtNumeroResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel36))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(edtCodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnAltCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(edtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(8, 8, 8))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelCelular)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblCpfCnpj1)
                                        .addGap(2, 2, 2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRgIe1)
                                        .addComponent(lblDataNascimento1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtRg)
                                    .addComponent(txtCpf)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(8, 8, 8)
                                .addComponent(txtTel))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDtNasc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel38)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(edtConfirmacaoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkAlterarSenhaProximoLogin)))
                        .addComponent(jLabel40)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoverGrupoo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(8, 8, 8)
                                .addComponent(dtDemissao, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtDtCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtDtUltAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel2);

        buttonGroup1.add(rdAtivado);
        rdAtivado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdAtivado.setSelected(true);
        rdAtivado.setText("Ativado");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Status :");

        buttonGroup1.add(rdDesativado);
        rdDesativado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdDesativado.setText("Desativado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescricaoTela, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdAtivado)
                        .addGap(0, 0, 0)
                        .addComponent(rdDesativado))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDescricaoTela)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel3))
                        .addComponent(rdAtivado)
                        .addComponent(rdDesativado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtCodCidadeKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyCode.ENTER.impl_getCode()) {
            try {
                CidadeService cidadeService = new CidadeService();
                this.funcionario.setCidade((Cidade) cidadeService.getCidadeByID(Integer.parseInt(edtCodCidade.getText())));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (this.funcionario.getCidade() != null) {
                this.edtCidade.setText(this.funcionario.getCidade().getNome());
                this.edtEstado.setText(this.funcionario.getCidade().getEstado().getNome());
                this.edtPais.setText(this.funcionario.getCidade().getEstado().getPais().getNome());
            }
        }
    }

    private void btnAltCidadeActionPerformed(java.awt.event.ActionEvent evt) {
        PesquisarCidade.Callback callback = (cidade -> {
            if (cidade != null) {
                funcionario.setCidade(cidade);
                edtCidade.setText(cidade.getNome());
                edtCodCidade.setText(cidade.getId().toString());
                edtPais.setText(cidade.getEstado().getPais().getNome());
                edtEstado.setText(cidade.getEstado().getNome());
            }
        });
        new PesquisarCidade(this, true, callback).show();
    }

    private void rdSexoMasculinoActionPerformed(java.awt.event.ActionEvent evt) {
        if (rdSexoFeminino.isSelected())
            rdSexoFeminino.setSelected(false);
    }

    private void rdSexoFemininoActionPerformed(java.awt.event.ActionEvent evt) {
        if (rdSexoMasculino.isSelected())
            rdSexoMasculino.setSelected(false);
    }

    private void btnAddGrupoActionPerformed(java.awt.event.ActionEvent evt) {
        PesquisarGrupoOperador.Callback callback = (condicao) -> {
            if (condicao == null) {
                return;
            }
            if (funcionario.getGrupos().contains(condicao)) {
                JOptionPane.showMessageDialog(this, "Condição já selecionada");
                return;
            }
            funcionario.getGrupos().add(condicao);
            gruposAdicionar.add(condicao);
            if (!funcionario.getGrupos().contains(condicao))
                gruposAdicionar.add(condicao);
            this.tableModelTipoOperador.setList(funcionario.getGrupos().toArray());
            this.tblGrupos.setModel(tableModelTipoOperador);
        };

        new PesquisarGrupoOperador(this, true, callback).show();
    }

    private void btnRemoverGrupooActionPerformed(java.awt.event.ActionEvent evt) {
        GrupoFuncionario grupo = funcionario.getGrupos().get(tblGrupos.getSelectedRow());

        if (funcionario.getGrupos().contains(grupo)) {
            funcionario.getGrupos().remove(grupo);
            gruposRemover.add(grupo);
        }
        this.tableModelTipoOperador.setList(funcionario.getGrupos().toArray());
        this.tblGrupos.setModel(tableModelTipoOperador);
    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "CANCELAR AÇÃO", "Nenhum dado alterado será salvo \nTem certeza que deseja cnacelar a ação ?", JOptionPane.YES_NO_OPTION) == 0) {
            dispose();
            return;
        }
    }

    private void btnsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalvarActionPerformed
        this.carregafuncionario();
        if (funcionario.getNome().trim().isEmpty()) {
            if (!funcionario.getTipo().equals(TipoPessoa.JURIDICA)) {
                JOptionPane.showMessageDialog(this, "Nome não informado");
                this.txtNome.requestFocus();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Razão social não informado");
                this.txtNome.requestFocus();
                return;
            }
        }

        if (funcionario.getLogradouro().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Logradouro não informado");
            this.edtLogradouro.requestFocus();
            return;
        }
        if (funcionario.getNumeroResidencial().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Numero não informado");
            this.edtNumeroResidencial.requestFocus();
            return;
        }
        if (funcionario.getCidade() == null) {
            JOptionPane.showMessageDialog(this, "Cidade não informada");
            this.edtCodCidade.requestFocus();
            return;
        }
        if (funcionario.getTelefone().replaceAll("[^0-9]", "").trim().isEmpty() && funcionario.getTelefoneAlternativo().replaceAll("[^0-9]", "").trim().isEmpty() &&
                funcionario.getEmail().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ao menos um contato deve ser informado, telefone, celular ou email");
            this.txtTel.requestFocus();
            return;
        }
        if (!funcionario.getEmail().trim().isEmpty()) {
            if (!ValidacaoEmail.isValidEmailAddress(funcionario.getEmail())) {
                JOptionPane.showMessageDialog(this, "Email informado não é valido ");
                this.txtEmail.requestFocus();
                return;
            }
        }
        if (funcionario.getCpfCnpj().trim().isEmpty()) {
            if (funcionario.getTipo().equals(TipoPessoa.FISICA))
                JOptionPane.showMessageDialog(this, "Campo CPF não informado");
            else if (funcionario.getTipo().equals(TipoPessoa.JURIDICA))
                JOptionPane.showMessageDialog(this, "Campo CNPJ não informado");
            else
                JOptionPane.showMessageDialog(this, "Campo Documento não informado");
            txtCpf.requestFocus();
            return;
        }

        if (funcionario.getDataNascimento() != null && funcionario.getDataNascimento().after(new Date())) {
            JOptionPane.showMessageDialog(
                    this,
                    "A data de fundação não pode ser maior que a data atual"
            );
            return;
        }

        if (funcionario.getSenha().length() < 5) {
            JOptionPane.showMessageDialog(this, "Senha invalida, a senha deve possuir no minimo 5 caractres");
            return;
        }
        if (!edtSenha.getText().equals(edtConfirmacaoSenha.getText())) {
            JideOptionPane.showMessageDialog(this, "Senha e confirmação de senha não conferem");
            edtSenha.requestFocus();
            return;
        }

        if (funcionario.getDataAdmissao() == null) {
            JideOptionPane.showMessageDialog(this, "Campo data de admissão é obrigatório");
            dtAdmissao.requestFocus();
            return;
        }

        if (funcionario.getDataDemissao() != null && funcionario.getDataDemissao().compareTo(funcionario.getDataAdmissao()) < 0) {
            JideOptionPane.showMessageDialog(this, "Campo data de demissão deve ser maior que de data de admissão");
            dtDemissao.requestFocus();
            return;
        }

        if (funcionario.getDataDemissao() != null) {
            Integer res = JOptionPane.showConfirmDialog(this,  "Ao informar a data de demissão o funcionario será automaticamente desativado \n Deseja continuar", "ATENÇÃO",
                    JOptionPane.YES_NO_OPTION);
            if (res == 0) {
                funcionario.setAtivo(false);
            } else {
                dtDemissao.requestFocus();
                return;
            }
        }

        if (funcionario.getGrupos().size() == 0) {
            JOptionPane.showMessageDialog(this, "O funcionario deve conter no minimo um grupo");
            return;
        }


        try {
            FuncionarioService fornecedorService = new FuncionarioService();
            if (funcionario.getId() == null) {
                fornecedorService.save(funcionario);
            } else {
                fornecedorService.update(funcionario);
                if (!gruposRemover.isEmpty())
                    fornecedorService.deleteGrupos(gruposRemover, funcionario.getId());
                if (!gruposAdicionar.isEmpty())
                    fornecedorService.salvarGrupos(gruposAdicionar, funcionario.getId());

            }
            JOptionPane.showMessageDialog(this, "salvo com sucesso");
            dispose();
        } catch (
                Exception ex) {
            JOptionPane.showMessageDialog(this, "falha ao salvar, " + ex.getMessage());

        }

    }//GEN-LAST:event_btnsalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddGrupo;
    private javax.swing.JButton btnAltCidade;
    private javax.swing.JButton btnRemoverGrupoo;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnsalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkAlterarSenhaProximoLogin;
    private com.toedter.calendar.JDateChooser dtAdmissao;
    private com.toedter.calendar.JDateChooser dtDemissao;
    private javax.swing.JFormattedTextField edtCep;
    private javax.swing.JTextField edtCidade;
    private javax.swing.JTextField edtCodCidade;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtComplemento;
    private javax.swing.JPasswordField edtConfirmacaoSenha;
    private javax.swing.JTextField edtDtCadastro;
    private javax.swing.JTextField edtDtUltAtualizacao;
    private javax.swing.JTextField edtEstado;
    private javax.swing.JTextField edtLogradouro;
    private javax.swing.JFormattedTextField edtNumeroResidencial;
    private javax.swing.JTextField edtPais;
    private javax.swing.JPasswordField edtSenha;
    private javax.swing.JTextField edtUsuario;
    private javax.swing.JTextField edtbairro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblCpfCnpj1;
    private javax.swing.JLabel lblDataNascimento1;
    private javax.swing.JLabel lblDescricaoTela;
    private javax.swing.JLabel lblNomeCompleto1;
    private javax.swing.JLabel lblRgIe1;
    private javax.swing.JLabel lblSexo1;
    private javax.swing.JRadioButton rdAtivado;
    private javax.swing.JRadioButton rdDesativado;
    private javax.swing.JRadioButton rdSexoFeminino;
    private javax.swing.JRadioButton rdSexoMasculino;
    private javax.swing.JTable tblGrupos;
    private javax.swing.JFormattedTextField txtCpf;
    private com.toedter.calendar.JDateChooser txtDtNasc;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtTel;
    private javax.swing.JFormattedTextField txtTelCelular;
    // End of variables declaration//GEN-END:variables
}
