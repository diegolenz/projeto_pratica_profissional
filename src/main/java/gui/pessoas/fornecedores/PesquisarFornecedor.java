/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pessoas.fornecedores;

import gui.modeltable.TableModelFornecedor;
import gui.modeltable.TableModelPessoa;

import gui.swing.DialogPadrao;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.model.pessoa.Pessoa;
import lib.model.pessoa.fornecedor.Fornecedor;
import lib.service.FornecedorService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author diego.lenz
 */
public class PesquisarFornecedor extends DialogPadrao {
    private Fornecedor pessoaSelecionada=null;
    private TableModelFornecedor modelo;
    private PesquisarFornecedor.Callback callback;
    private List<Fornecedor> list;

    /**
     * Creates new form Pesquisar
     */

    public interface Callback {

        void handle(Pessoa pessoa);
    }

    public PesquisarFornecedor(Window parent, boolean modal, PesquisarFornecedor.Callback callBack) {
        super( parent, modal, ModuloSistema.FORNECEDORES, NivelAcessoModulo.SOMENTE_LEITURA);
        this.callback=callBack;
        modelo = new TableModelFornecedor();
        initComponents();
        btnpesquisarActionPerformed(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtpesquisa = new javax.swing.JFormattedTextField();
        btnpesquisar = new javax.swing.JToggleButton();
        btnselecionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        btnnovapessoa = new javax.swing.JButton();
        btnalt = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar fornecedor");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setModal(true);

        btnpesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnpesquisar.setText("Pesquisar");
        btnpesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesquisarActionPerformed(evt);
            }
        });

        btnselecionar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnselecionar.setText("selecionar");
        btnselecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselecionarActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabela.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tabela);

        btnnovapessoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnnovapessoa.setText("Nova pessoa");
        btnnovapessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnovapessoaActionPerformed(evt);
            }
        });

        btnalt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnalt.setText("alterar");
        btnalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaltActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir.setText("excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnVisualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(edtpesquisa)
                        .addGap(2, 2, 2)
                        .addComponent(btnpesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnnovapessoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnalt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnselecionar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnalt)
                    .addComponent(btnnovapessoa)
                    .addComponent(btnselecionar)
                    .addComponent(btnExcluir)
                    .addComponent(btnVisualizar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        pessoaSelecionada=list.get(tabela.getSelectedRow());
        CadastroFornecedorForm novapessoa=new CadastroFornecedorForm(this,false, pessoaSelecionada);
        novapessoa.carregaredt();
        novapessoa.bloqueiaedt();
        novapessoa.show();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        pessoaSelecionada=list.get(tabela.getSelectedRow());
        CadastroFornecedorForm novapessoa=new CadastroFornecedorForm(this,false, pessoaSelecionada);
        novapessoa.carregaredt();
        novapessoa.bloqueiaedt();
        novapessoa.show();
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o fornecedor selecionado ? ", "Atenção", JOptionPane.YES_NO_OPTION) == 0){
            try {
                new FornecedorService().deleteByID(pessoaSelecionada);
                JOptionPane.showMessageDialog(this, "Fornecedor excluido com sucesso");
                list = new FornecedorService().getAll("");
                modelo.setList(list.toArray());
                tabela.setModel(modelo);
            }catch (SQLException ex){

                if (!pessoaSelecionada.getAtivo()){
                    JOptionPane.showMessageDialog(this, "Não foi possivel excluir o fornecedor pois existem " +
                            "cadastros relacionado a ele \n " + "Fornecedor ja esta desativado");
                } else if (JOptionPane.showConfirmDialog(this, "Não é possivel excluir o fornecedor, pois existem" +
                        "cadastros relacionado a ele! \n Deseja Desativa-lo ?" , "Atenção", JOptionPane.YES_NO_OPTION)==0){
                    pessoaSelecionada.setAtivo(false);
                    try {
                        new FornecedorService().update(pessoaSelecionada);
                        JOptionPane.showMessageDialog(this, "Desativado com sucesso");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Falha ao desativar \n" + ex.getCause() +"\n" + ex.getMessage());
                    }
                }
            }
        }
        novapessoa.dispose();
        return;
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnaltActionPerformed(java.awt.event.ActionEvent evt) {
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        pessoaSelecionada=list.get(tabela.getSelectedRow());
        CadastroFornecedorForm novapessoa=new CadastroFornecedorForm(this,false, pessoaSelecionada);
        novapessoa.carregaredt();
        novapessoa.desbloqueiaedt();
        novapessoa.show();
    }

    private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            list= new FornecedorService().getAll(edtpesquisa.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        modelo.setList(list.toArray());
        tabela.setModel(modelo);
        if (list.isEmpty() && evt != null)
            JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado");
        else if (!list.isEmpty()){
            tabela.setRowSelectionInterval(0,0);
        }
    }

    private void btnselecionarActionPerformed(java.awt.event.ActionEvent evt) {
        //PessoaCotaDao pdao=new PessoaCotaDao();
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this , "Nenhuma pessoa selecionada");
            return;
        } else {
            pessoaSelecionada = list.get(tabela.getSelectedRow());
            if (!pessoaSelecionada.getAtivo()){
                JOptionPane.showMessageDialog(this,"Pessoa selecionada não esta ativa");
                return;
            }
            callback.handle(pessoaSelecionada);
            this.dispose();
        }
    }                                             


    private void btnnovapessoaActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroFornecedorForm novapessoa=new CadastroFornecedorForm(this, true, new Fornecedor());
        novapessoa.show();
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JButton btnalt;
    private javax.swing.JButton btnnovapessoa;
    private javax.swing.JToggleButton btnpesquisar;
    private javax.swing.JButton btnselecionar;
    private javax.swing.JFormattedTextField edtpesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
