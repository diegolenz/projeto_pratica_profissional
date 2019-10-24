/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.enderecos.cidades;

import gui.modeltable.TableModelCidade;

import gui.swing.DialogPadrao;
import lib.model.endereco.cidade.Cidade;
import lib.model.endereco.estado.Estado;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.service.CidadeService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author diego.lenz
 */
public class PesquisarCidade extends DialogPadrao {
    private Cidade cidadeSelecionada;
    private TableModelCidade modelo;
    private PesquisarCidade.Callback callback;
    private List<Cidade> list;
    private CidadeService cidadeService ;
    private Estado estado;
    private CadastroCidade cadastroCidade;

    /**
     * Creates new form Pesquisar
     */

    public interface Callback {
        void handle(Cidade cidade);
    }

    public void setEstado(Estado estado){
        this.estado=estado;
    }

    public PesquisarCidade(Window  parent, boolean modal, PesquisarCidade.Callback callBack) {
        super( parent, modal, ModuloSistema.ENDERECO, NivelAcessoModulo.SOMENTE_LEITURA);
        this.callback=callBack;
        initComponents();
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

        edtpesquisa = new javax.swing.JFormattedTextField();
        btnpesquisar = new javax.swing.JToggleButton();
        btnselecionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbStatusCidade = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar cidade");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setModal(true);

        btnpesquisar.setBackground(new java.awt.Color(255, 255, 255));
        btnpesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        btnExcluir.setText("excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome ou código da cidade");

        jLabel2.setText("Status");

        cmbStatusCidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Ativados", "Desativados" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar)
                        .addGap(7, 7, 7)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnselecionar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 522, Short.MAX_VALUE))
                            .addComponent(edtpesquisa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStatusCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnpesquisar)))
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
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStatusCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnpesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnselecionar)
                    .addComponent(btnExcluir)
                    .addComponent(btnNovo)
                    .addComponent(btnAlterar)
                    .addComponent(btnVisualizar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnpesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpesquisarActionPerformed
        modelo = new TableModelCidade();
        try {
            if (cmbStatusCidade.getSelectedIndex() == 0)
                list =  new CidadeService().getAll(edtpesquisa.getText());
            if (cmbStatusCidade.getSelectedIndex() == 1)
                list =  new CidadeService().getAllAtivos(edtpesquisa.getText());
            if (cmbStatusCidade.getSelectedIndex() == 2)
                list =  new CidadeService().getAll(edtpesquisa.getText()).stream().filter(o ->  !o.getAtivo()).collect(Collectors.toList());
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,  e.getMessage());
        }
       modelo.setList(list.toArray());
       tabela.setModel(modelo);
       if (list.isEmpty()) {
           if (evt != null)
               JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado");
       }
       else tabela.setRowSelectionInterval(0,0);
    }//GEN-LAST:event_btnpesquisarActionPerformed

    private void btnselecionarActionPerformed(java.awt.event.ActionEvent evt) {
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this , "Nenhuma cidade selecionada");
            return;
        } else {
            cidadeSelecionada = (Cidade) list.get(this.tabela.getSelectedRow());
            if (!cidadeSelecionada.getAtivo()){
                JOptionPane.showMessageDialog(this,"A cidade selecionada não esta ativa");
                return;
            }
            callback.handle(cidadeSelecionada);
            this.dispose();
        }
    }

    private Integer getPosicao(Cidade marca){
        try {
            marca = new CidadeService().getLast();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer tam = list.size();
        Integer pos = 0;
        boolean achou =false;
        while ( pos < tam && !achou ){
            if (list.get(pos).getId().equals(marca.getId())) {
                achou =true;
            } else
                pos++;
        }
        return pos;
    }


    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        CadastroCidade.Callback callback = (cidade -> {
                try {
                    list = new CidadeService().getAll(edtpesquisa.getText());
                } catch (Exception ex){
                    System.out.println(ex);
                }
                modelo.setList(list.toArray());

                if (!list.isEmpty()) {
                    Integer i = getPosicao(cidade);
                    tabela.setRowSelectionInterval(i,i);
                }
        });
        new CadastroCidade(this, true, new Cidade(), callback).show();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        CadastroCidade.Callback callback = (cidade -> {
            try {
                list = new CidadeService().getAll(edtpesquisa.getText());
            } catch (Exception ex){
                System.out.println(ex);
            }
            modelo.setList(list.toArray());

            if (!list.isEmpty()) {
                Integer i = getPosicao(cidade);
                tabela.setRowSelectionInterval(i,i);
            }
        });
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        Cidade selecionado=list.get(tabela.getSelectedRow());
        cadastroCidade=new CadastroCidade(this, true, selecionado, callback);
        cadastroCidade.carregaEdt();
        cadastroCidade.show();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }

        Cidade cidadeSelecionado=list.get(tabela.getSelectedRow());
        CadastroCidade cadastroCidade = new CadastroCidade(this, true, cidadeSelecionado, null);
        cadastroCidade.carregaEdt();
        cadastroCidade.show();
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a cidade selecionada ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION)==1)
            return;
        try {
            cidadeService.deleteByID(cidadeSelecionado.getId());
            JOptionPane.showMessageDialog(this, "Excluido com sucesso");
            return;

        }catch (Exception e) {
            if (JOptionPane.showConfirmDialog(this,"Não é possivel excluir o registro, deseja desativa-lo?", "ATENÇÂO", JOptionPane.YES_NO_OPTION)==0)
                if (!cidadeSelecionado.getAtivo())
                    JOptionPane.showMessageDialog(this, "Cidade já esta desativado");
            try {
                cidadeService.update(cidadeSelecionado);
                JOptionPane.showMessageDialog(this,
                        "Desativado com sucesso"
                );
                return;
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Não foi possivel desativar");
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        if (tabela.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        CadastroCidade.Callback callback = (cidade -> {
            try {
                list = new CidadeService().getAll(edtpesquisa.getText());
            } catch (Exception ex){
                System.out.println(ex);
            }
            modelo.setList(list.toArray());

            if (!list.isEmpty()) {
                Integer i = list.indexOf(cidade);
                tabela.setRowSelectionInterval(i,i);
            }
        });
        Cidade selecionado=list.get(tabela.getSelectedRow());
        cadastroCidade=new CadastroCidade(this, true, selecionado, null);
        cadastroCidade.carregaEdt();
        cadastroCidade.bloqueiaEdt();
        cadastroCidade.show();
    }//GEN-LAST:event_btnVisualizarActionPerformed

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
            java.util.logging.Logger.getLogger(PesquisarCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisarCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisarCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisarCidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesquisarCidade dialog = new PesquisarCidade(new javax.swing.JFrame(), true,null);
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
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JToggleButton btnpesquisar;
    private javax.swing.JButton btnselecionar;
    private javax.swing.JComboBox<String> cmbStatusCidade;
    private javax.swing.JFormattedTextField edtpesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
