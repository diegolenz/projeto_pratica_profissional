/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.produtos;

import gui.marca.CadastrarMarcaForm;
import gui.modeltable.TableModelProduto;
import gui.produtos.CadastroProdutos;
import gui.swing.DialogPadrao;
import lib.dao.imp.produto.ProdutoDao;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.model.produto.Produto;
import lib.service.ProdutoService;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 * @author diego.lenz
 */
public class PesquisarProduto extends DialogPadrao {
    private PesquisarProduto.Callback callback;
    private List<Produto> produtos;
    private ProdutoService produtoService;
    private Produto produto;
    private TableModelProduto modelo;

    /**
     * Creates new form Pesquisar
     */

    public interface Callback {

        void handle(Produto produto);
    }

    public PesquisarProduto(Window parent, boolean modal, PesquisarProduto.Callback callBack) {
        super( parent, modal, ModuloSistema.PRODUTOS_SERVICOS, NivelAcessoModulo.SOMENTE_LEITURA);
        this.callback=callBack;
        this.produtoService = new ProdutoService();
        initComponents();
        initTabela();

    }

    public void initTabela(){
        modelo = new TableModelProduto();
        try {
            produtos = new ProdutoService().getAll("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        modelo.setList(produtos.toArray());
        tabela.setModel(modelo);
        if (!produtos.isEmpty())
            tabela.setRowSelectionInterval(0,0);
        this.tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
        this.tabela.getColumnModel().getColumn(6).setPreferredWidth(20);
        this.tabela.getColumnModel().getColumn(7).setPreferredWidth(20);
        this.tabela.getColumnModel().getColumn(8).setPreferredWidth(70);
        this.tabela.getColumnModel().getColumn(9).setPreferredWidth(70);
        this.tabela.getColumnModel().getColumn(10).setPreferredWidth(70);
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
        btnNovo = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnExcluirDesativar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar produto");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setModal(true);

        btnpesquisar.setText("Pesquisar");
        btnpesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesquisarActionPerformed(evt);
            }
        });

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

        btnNovo.setText("novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btneditar.setText("Alterar");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btnVisualizar.setText("visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        btnExcluirDesativar.setText("excluir");
        btnExcluirDesativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirDesativarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(edtpesquisa)
                        .addGap(2, 2, 2)
                        .addComponent(btnpesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirDesativar)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluirDesativar)
                        .addComponent(btneditar)
                        .addComponent(btnNovo)
                        .addComponent(btnVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnselecionar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        modelo =new TableModelProduto();
            try {
                produtos= produtoService.getAllAtivos(edtpesquisa.getText());
            } catch (Exception e) {
               JOptionPane.showMessageDialog(this, e.getMessage());
            }

        modelo.setList(produtos.toArray());
        tabela.setModel(modelo);
    }                                            

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroProdutos.Callback callback = (produto -> {
            try {
                produtos = new  ProdutoService().getAll("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            Integer pos = produtos.indexOf(produto);
            tabela.setRowSelectionInterval(pos,pos);
        });
        new CadastroProdutos(this, true, new Produto(), callback).show();
    }

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this , "Nenhuma pessoa selecionada");
            return;
        }
        produto=produtos.get(tabela.getSelectedRow());
        CadastroProdutos.Callback callback = (produto -> {
            try {
                produtos = new  ProdutoService().getAll("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            Integer pos = produtos.indexOf(produto);
            tabela.setRowSelectionInterval(pos,pos);
        });
        new CadastroProdutos(this, true,  produto, callback).show();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        produto = produtos.get(tabela.getSelectedRow());
        CadastroProdutos cadastroProdutos = new CadastroProdutos(this, true, produto,null);
        cadastroProdutos.carregarcampos();
        cadastroProdutos.bloqueiaedt();
        cadastroProdutos.show();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnExcluirDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirDesativarActionPerformed
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        produto = produtos.get(tabela.getSelectedRow());
        CadastroProdutos.Callback callback = (produto -> {
            try {
                produtos = new  ProdutoService().getAll("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            Integer pos = produtos.indexOf(produto);
            tabela.setRowSelectionInterval(pos,pos);
        });
        CadastroProdutos cadastroProdutos = new CadastroProdutos(getWindowParent(), true, produto, callback);
        cadastroProdutos.carregarcampos();
        cadastroProdutos.bloqueiaedt();
        cadastroProdutos.show();
        try {
            new ProdutoService().deleteByID(produto.getId());
            JOptionPane.showMessageDialog(this, "Excluido com sucesso");
        } catch (Exception e) {
            if (JOptionPane.showConfirmDialog(this,"Não é possivel excluir o registro, deseja desativa-lo?", "ATENÇÂO", JOptionPane.YES_NO_OPTION)==0) {
                if (!produto.getAtivo())
                JOptionPane.showMessageDialog(this, "Produto já está desativada");
                try {
                    new ProdutoService().update(produto);
                    JOptionPane.showMessageDialog(this,
                        "Desativado com sucesso"
                    );
                    return;
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Não foi possivel desativar");
                    return;
                }
            }
        }
        cadastroProdutos.dispose();
        try {
            produtos = new ProdutoService().getAll("");
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Falha ao retornar dados");
        }
        modelo.setList(produtos.toArray());
        if (!produtos.isEmpty())
        tabela.setRowSelectionInterval(0,0);
    }//GEN-LAST:event_btnExcluirDesativarActionPerformed

    private void btnselecionarActionPerformed(java.awt.event.ActionEvent evt) {
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this , "Nenhuma pessoa selecionada");
            return;
        } else {
            produto = produtos.get(tabela.getSelectedRow());
            if (!produto.getAtivo()) {
                JOptionPane.showMessageDialog(this,"Produto selecionado não esta ativo");
                return;
            }
            callback.handle(produto);
            this.dispose();
        }
    }                                             

    private void cmbselecionapesquisaActionPerformed(java.awt.event.ActionEvent evt) {

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
            java.util.logging.Logger.getLogger(PesquisarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesquisarProduto dialog = new PesquisarProduto(new javax.swing.JDialog(), true,null);
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
    private javax.swing.JButton btnExcluirDesativar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JButton btneditar;
    private javax.swing.JToggleButton btnpesquisar;
    private javax.swing.JButton btnselecionar;
    private javax.swing.JFormattedTextField edtpesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}