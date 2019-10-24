/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.enderecos.cidades;

import gui.enderecos.estados.CadastroEstado;
import gui.enderecos.pais.CadastroPais;
import gui.modeltable.TableModelCidade;
import gui.modeltable.TableModelEstado;
import gui.swing.SociusTab;
import gui.swing.WindowPadrao;
import lib.dao.imp.endereco.cidade.CidadeDao;
import lib.dao.imp.endereco.estado.EstadoDao;
import lib.model.endereco.cidade.Cidade;
import lib.model.endereco.estado.Estado;
import lib.model.endereco.pais.Pais;
import lib.model.interno.ModuloSistema;
import lib.service.CidadeService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Diego
 */
public class ConsultaCidades extends SociusTab implements WindowPadrao {

    private List<Cidade> cidades;
    private TableModelCidade modelo;
    private CadastroCidade cadastroCidade;


    /**
     * Creates new form ConsultaPanel
     */
    public ConsultaCidades(Window parent) {
        super(parent, ModuloSistema.ENDERECO);
        initComponents();
        modelo =new TableModelCidade();
        this.jButton1ActionPerformed(null);
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
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("Consulta"); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        edtPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir.setText("excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
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
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnVisualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome ou código da cidade");

        jLabel2.setText("Status");

        cmbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Ativados", "Desativados" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovo)
                    .addComponent(btnVisualizar))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        Cidade selecionado=cidades.get(jTable1.getSelectedRow());
        cadastroCidade=new CadastroCidade(getWindowParent(), true, selecionado, null);
        cadastroCidade.carregaEdt();
        cadastroCidade.bloqueiaEdt();
        cadastroCidade.show();
    }//GEN-LAST:event_btnVisualizarActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        modelo = new TableModelCidade();
        try {
            if (cmbStatus.getSelectedIndex() == 0)
                cidades =  new CidadeService().getAll(edtPesquisa.getText());
            if (cmbStatus.getSelectedIndex() == 1)
                cidades =  new CidadeService().getAllAtivos(edtPesquisa.getText());
            if (cmbStatus.getSelectedIndex() == 2)
                cidades =  new CidadeService().getAll(edtPesquisa.getText()).stream().filter(o ->  !o.getAtivo()).collect(Collectors.toList());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,  e.getMessage());
        }
        modelo.setList(cidades.toArray());
        jTable1.setModel(modelo);
        if (cidades.isEmpty())
            if (evt!= null)
            JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado");
        else jTable1.setRowSelectionInterval(0,0);
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        Cidade cidadeSelecionado=cidades.get(jTable1.getSelectedRow());
        CadastroCidade cadastroCidade = new CadastroCidade(getWindowParent(), true, cidadeSelecionado, null);
        cadastroCidade.carregaEdt();
        cadastroCidade.bloqueiaEdt();
        cadastroCidade.show();
        if (JOptionPane.showConfirmDialog(getWindowParent(), "Deseja realmente excluir a cidade selecionada ?", "Atenção", JOptionPane.YES_NO_OPTION)==0) {
            try {
                new CidadeService().deleteByID(cidadeSelecionado.getId());
                JOptionPane.showMessageDialog(this, "Excluido com sucesso");
                cadastroCidade.dispose();
                return;
            } catch (Exception e) {
                if (JOptionPane.showConfirmDialog(this, "Não é possivel excluir o registro, deseja desativa-lo?", "ATENÇÂO", JOptionPane.YES_NO_OPTION) == 0)
                    if (!cidadeSelecionado.getAtivo()) {
                        JOptionPane.showMessageDialog(this, "Cidade já esta desativada");
                        cadastroCidade.dispose();
                        return;
                    }
                try {
                    new CidadeService().update(cidadeSelecionado);
                    JOptionPane.showMessageDialog(this,
                            "Desativado com sucesso"
                    );
                    cadastroCidade.dispose();
                    return;
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Não foi possivel desativar");
                }
            }
        }
        cadastroCidade.dispose();

    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroCidade.Callback callback = (cidade -> {
            try {
                cidades = new CidadeService().getAll(edtPesquisa.getText());
            } catch (Exception ex){
                System.out.println(ex);
            }
            modelo.setList(cidades.toArray());

            if (!cidades.isEmpty()) {
                Integer i =getPosicao(cidade);
                jTable1.setRowSelectionInterval(i,i);
            }
        });
        cadastroCidade=new CadastroCidade(getWindowParent(), true, new Cidade(), callback);
        cadastroCidade.show();
    }

    private Integer getPosicao(Cidade marca){
        try {
            marca = new CidadeService().getLast();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer tam = cidades.size();
        Integer pos = 0;
        boolean achou =false;
        while ( pos < tam && !achou ){
            if (cidades.get(pos).getId().equals(marca.getId())) {
                achou =true;
            } else
                pos++;
        }
        return pos;
    }

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        CadastroCidade.Callback callback = (cidade -> {
            try {
                cidades = new CidadeService().getAll(edtPesquisa.getText());
            } catch (Exception ex){
                System.out.println(ex);
            }
            modelo.setList(cidades.toArray());

            if (!cidades.isEmpty()) {
                Integer i = cidades.indexOf(cidade);
                jTable1.setRowSelectionInterval(i,i);
            }
        });
        Cidade selecionado=cidades.get(jTable1.getSelectedRow());
        cadastroCidade=new CadastroCidade(getWindowParent(), true, selecionado,callback );
        cadastroCidade.carregaEdt();
        cadastroCidade.show();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
