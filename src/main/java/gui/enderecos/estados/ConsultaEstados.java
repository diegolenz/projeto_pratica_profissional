/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.enderecos.estados;

import gui.enderecos.pais.CadastroPais;
import gui.modeltable.TableModelEstado;
import gui.modeltable.TableModelPais;
import gui.swing.SociusTab;
import gui.swing.WindowPadrao;
import lib.dao.imp.endereco.estado.EstadoDao;
import lib.dao.imp.endereco.pais.PaisDao;
import lib.model.endereco.cidade.Cidade;
import lib.model.endereco.estado.Estado;
import lib.model.endereco.pais.Pais;
import lib.model.interno.ModuloSistema;
import lib.service.EstadoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Diego
 */
public class ConsultaEstados extends SociusTab implements WindowPadrao {

    private List<Estado> estados;
    private TableModelEstado modelo;
    private EstadoService estadoService;
    private CadastroEstado cadastroEstado;

    /**
     * Creates new form ConsultaPanel
     */
    public ConsultaEstados(Window parent) {
        super(parent, ModuloSistema.ENDERECO);
        initComponents();
        estadoService=new EstadoService();
       this.btnPesquisarActionPerformed(null);
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
        btnPesquisar = new javax.swing.JButton();
        edtPesquisa = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("Consulta"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnPesquisar.setText("pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnVisualizar.setText("visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        btnAlterar.setText("alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Status");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Estado");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "todos", "ativados", "desativados" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 531, Short.MAX_VALUE))
                            .addComponent(edtPesquisa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesquisar))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnVisualizar)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovo))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        Estado estSelecionado=estados.get(jTable1.getSelectedRow());
        cadastroEstado=new CadastroEstado(getWindowParent(), true, estSelecionado , null);
        cadastroEstado.carregaEdt();
        cadastroEstado.bloqueiaEdt();
        cadastroEstado.show();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private Integer getPosicao(Estado estado){
        try {
            estado = new EstadoService().getLast(estado);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer tam = this.estados.size();
        Integer pos = 0;
        boolean achou =false;
        while ( pos < tam && !achou ){
            if (estados.get(pos).getId().equals(estado.getId())) {
                achou =true;
            } else
                pos++;
        }
        return pos;
    }


    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        modelo = new TableModelEstado();
        try {
            if (cmbStatus.getSelectedIndex() == 0)
                estados = estadoService.getAll(edtPesquisa.getText());
            if (cmbStatus.getSelectedIndex() == 1)
                estados = estadoService.getAllAtivos(edtPesquisa.getText());
            if (cmbStatus.getSelectedIndex() == 2)
                estados = estadoService.getAll(edtPesquisa.getText()).stream().filter(o -> !o.getAtivo()).collect(Collectors.toList());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        modelo.setList(estados.toArray());
        jTable1.setModel(modelo);
        if (estados.isEmpty())
            if (evt!=null)
            JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado");
        else jTable1.setRowSelectionInterval(0,0);
    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroEstado.Callback callback = (estado -> {
            try {
                estados = new  EstadoService().getAll("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            modelo.setList(estados.toArray());
            jTable1.setModel(modelo);
            Integer pos = getPosicao(estado);
            jTable1.setRowSelectionInterval(pos,pos);
        });
        cadastroEstado=new CadastroEstado(getWindowParent(), true, new Estado(), callback);
        cadastroEstado.show();
    }                                       

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        CadastroEstado.Callback callback = (estado -> {
            try {
                estados = new  EstadoService().getAll("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Falha ao retornar dados");
            }
            modelo.setList(estados.toArray());
            jTable1.setModel(modelo);
            Integer pos = getPosicao(estado);
            jTable1.setRowSelectionInterval(pos,pos);
        });
        Estado estSelecionado=estados.get(jTable1.getSelectedRow());
        cadastroEstado=new CadastroEstado(getWindowParent(), true, estSelecionado, callback);
        cadastroEstado.carregaEdt();
        cadastroEstado.show();
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }
        Estado estadoSelecionada=estados.get(jTable1.getSelectedRow());
        CadastroEstado cadastroEstado = new CadastroEstado(getWindowParent(), true, estadoSelecionada, null);
        cadastroEstado.carregaEdt();
        cadastroEstado.bloqueiaEdt();
        cadastroEstado.show();
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a marca selecionada ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION)==1)
            return;
        try {
            new EstadoService().deleteByID(estadoSelecionada.getId());
            JOptionPane.showMessageDialog(this, "Excluido com sucesso");
            return;
        } catch (Exception e) {
            if (JOptionPane.showConfirmDialog(this,"Não é possivel excluir o registro, deseja desativa-lo?", "ATENÇÂO", JOptionPane.YES_NO_OPTION)==0) {
                if (!estadoSelecionada.getAtivo()) {
                    JOptionPane.showMessageDialog(this, "Estado já está desativada");
                    return;
                }
                try {
                    new EstadoService().update(estadoSelecionada);
                    JOptionPane.showMessageDialog(this,
                            "Desativado com sucesso"
                    );
                    return;
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Não foi possivel desativar");
                }
            }
        }
        cadastroEstado.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
