/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pessoas.clientes;

import gui.modeltable.TableModelPessoa;
import gui.swing.SociusTab;
import gui.swing.WindowPadrao;
import lib.model.interno.ModuloSistema;
import lib.model.pessoa.ClassePessoa;
import lib.model.pessoa.cliente.Cliente;
import lib.service.ClienteService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ConsultaClientesForm extends SociusTab implements WindowPadrao {

    private Cliente pessoaSelecionada;
    private List<Cliente> pessoas;
    private TableModelPessoa modelo;
    private NovoClienteForm novoPessoaForm;


    /**
     * Creates new form ConsultaPanel
     */
    public ConsultaClientesForm(Window parent) {
        super(parent, ModuloSistema.PESSOAS);
        initComponents();
        try {
            pessoas = new ClienteService().getAll("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initTabela();
        this.jButton1ActionPerformed(null);
    }

    public void initTabela(){
        modelo = new TableModelPessoa();
        modelo.setList(pessoas.toArray());
        jTable1.setModel(modelo);
        if (!pessoas.isEmpty())
            jTable1.setRowSelectionInterval(0,0);
        this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        this.jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        this.jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        this.jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
        this.jTable1.getColumnModel().getColumn(7).setPreferredWidth(125);
        this.jTable1.getColumnModel().getColumn(8).setPreferredWidth(125);
        this.jTable1.getColumnModel().getColumn(9).setPreferredWidth(50);
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
        btnVisualizar = new javax.swing.JButton();
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

        jButton1.setText("pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setText("alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnVisualizar.setText("visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(edtPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnAlterar)
                    .addComponent(btnVisualizar)
                    .addComponent(btnExcluir))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        Cliente pessoaSelecionado=pessoas.get(jTable1.getSelectedRow());
        NovoClienteForm novoClienteForm = new NovoClienteForm(getWindowParent(), true, pessoaSelecionado);
        novoClienteForm.carregaredt();
        novoClienteForm.bloqueiaedt();
        novoClienteForm.show();
        try {
            new ClienteService().deleteByID(pessoaSelecionado);
            JOptionPane.showMessageDialog(this, "Excluido com sucesso");
        }catch (Exception e) {
            if (JOptionPane.showConfirmDialog(this,"Não é possivel excluir o registro, deseja desativa-lo?", "ATENÇÂO", JOptionPane.YES_NO_OPTION)==0) {
                if (!pessoaSelecionado.getAtivo())
                    JOptionPane.showMessageDialog(this, "Pessoa já está desativada");
                try {
                    new ClienteService().update(pessoaSelecionado);
                    JOptionPane.showMessageDialog(this,
                            "Desativado com sucesso"
                    );
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Não foi possivel desativar");
                }
            }
        }
        try {
            pessoas = new ClienteService().getAll(edtPesquisa.getText());
        } catch (Exception e){
            JOptionPane.showConfirmDialog(this,"Falha ao retornar dados, contate o adminstrador do sistema \n" + e.getMessage());
        }
        modelo.setList(pessoas.toArray());
        jTable1.setModel(modelo);
        novoClienteForm.dispose();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            pessoas= new ClienteService().getAllClientesAtivos(edtPesquisa.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        modelo.setList(pessoas.toArray());
        jTable1.setModel(modelo);
        if (pessoas.isEmpty()) {
            if (evt!=null)
            JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado");
        }
        else jTable1.setRowSelectionInterval(0,0);

    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
        Cliente pessoa=new Cliente();
        NovoClienteForm novoPessoaForm = new NovoClienteForm(getWindowParent(), true, pessoa);
        novoPessoaForm.show();
    }

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        pessoaSelecionada=pessoas.get(jTable1.getSelectedRow());
        novoPessoaForm = new NovoClienteForm(getWindowParent(),false, pessoaSelecionada);
        novoPessoaForm.carregaredt();
        novoPessoaForm.desbloqueiaedt();
        novoPessoaForm.show();
    }

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        pessoaSelecionada=pessoas.get(jTable1.getSelectedRow());
        novoPessoaForm=new NovoClienteForm(getWindowParent(),false, pessoaSelecionada);
        novoPessoaForm.carregaredt();
        novoPessoaForm.bloqueiaedt();
        novoPessoaForm.show();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
