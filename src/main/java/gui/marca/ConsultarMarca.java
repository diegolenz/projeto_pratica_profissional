/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.marca;

import gui.grupo.CadastrarGrupoForm;
import gui.modeltable.TableModelMarca;
import gui.swing.SociusTab;
import gui.swing.WindowPadrao;
import lib.dao.imp.marca.MarcaDao;
import lib.model.grupo.Grupo;
import lib.model.interno.ModuloSistema;
import lib.model.marca.Marca;
import lib.service.GrupoService;
import lib.service.MarcaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ConsultarMarca extends SociusTab implements WindowPadrao {

    private List<Marca> marcas;
    private Marca marca;
    private TableModelMarca modeloMarca;
    private CadastrarMarcaForm cadastrarMarcaForm;
    private MarcaService marcaService;

    /**
     * Creates new form ConsultaPanel
     */
    public ConsultarMarca(Window parent ) {
        super(parent, ModuloSistema.MARCAS);
        initComponents();
        modeloMarca=new TableModelMarca();
        marcaService = new MarcaService();
        btnPesquisarActionPerformed(null);
        initTable();
    }

    public void initTable(){
        this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(400);
        this.jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
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

        setBackground(new java.awt.Color(255, 255, 255));
        setName("Consulta"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir.setText("Excluir");
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

        btnAlterar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAlterar.setText("Alterar");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(edtPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar)
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
                    .addComponent(btnPesquisar)
                    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnVisualizar)
                    .addComponent(btnAlterar)
                    .addComponent(btnNovo))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            marcas = marcaService.getAll(edtPesquisa.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }
        modeloMarca.setList(marcas.toArray());
        jTable1.setModel(modeloMarca);
        if (!marcas.isEmpty())
            jTable1.setRowSelectionInterval(0,0);

        if (marcas.isEmpty()){
            if (evt != null)
                JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado");
        } else jTable1.setRowSelectionInterval(0,0);
    }

    private Integer getPosicao(Marca marca){
        try {
            if (marca.getId().equals(null))
                marca = marcaService.findLast();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer tam = marcas.size();
        Integer pos = 0;
        boolean achou =false;
        while ( pos < tam && !achou ){
            if (marcas.get(pos).getId().equals(marca.getId())) {
                achou =true;
            } else
                pos++;
        }
        return pos;
    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt){
        CadastrarMarcaForm.Callback callback = (marca -> {
            try {
                marcas = new MarcaService().getAll("");
            } catch (Exception ex){
                System.out.println(ex);
            }
            modeloMarca.setList(marcas.toArray());

            if (!marcas.isEmpty()) {
                Integer i = getPosicao(marca);
                jTable1.setRowSelectionInterval(i,i);
            }
        });
        new CadastrarMarcaForm(getWindowParent(), true, new Marca(),callback).show();
    }

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Selecione um registro");
            return;
        }
        marca=marcas.get(jTable1.getSelectedRow());
        CadastrarMarcaForm.Callback callback = (cidade -> {
            try {
                marcas = new MarcaService().getAll("");
            } catch (Exception ex){
                System.out.println(ex);
            }
            modeloMarca.setList(marcas.toArray());

            if (!marcas.isEmpty()) {
                Integer i = marcas.indexOf(cidade);
                jTable1.setRowSelectionInterval(i,i);
            }
        });
        cadastrarMarcaForm= new CadastrarMarcaForm(getWindowParent(), true,  marca, callback );
        cadastrarMarcaForm.carregaEDT();
        cadastrarMarcaForm.show();
    }

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Selecione um registro");
            return;
        }
        marca=marcas.get(jTable1.getSelectedRow());
        cadastrarMarcaForm= new CadastrarMarcaForm(getWindowParent(), true,  marca, null);
        cadastrarMarcaForm.carregaEDT();
        cadastrarMarcaForm.bloqueia();
        cadastrarMarcaForm.show();
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para continuar");
            return;
        }

        marca=marcas.get(jTable1.getSelectedRow());
        CadastrarMarcaForm cadastrarMarcaForm = new CadastrarMarcaForm(getWindowParent(), true, marca, null );
        cadastrarMarcaForm.carregaEDT();
        cadastrarMarcaForm.bloqueia();
        cadastrarMarcaForm.show();

        try {
            if (JOptionPane.showConfirmDialog(cadastrarMarcaForm, "Deseja realmente excluir a marca selecionada ?", "Atenção", JOptionPane.YES_NO_OPTION) != 0) {
                cadastrarMarcaForm.dispose();
                return;
            }
            marcaService.deleteByID(marca.getId());
            JOptionPane.showMessageDialog(this, "Excluido com sucesso");
        } catch (Exception e) {
            if (JOptionPane.showConfirmDialog(this,"Não é possivel excluir o registro, deseja desativa-lo?", "ATENÇÂO", JOptionPane.YES_NO_OPTION)==0) {
                if (!marca.getAtivo())
                    JOptionPane.showMessageDialog(this, "Marca já está desativada");
                try {
                    marcaService.update(marca);
                    JOptionPane.showMessageDialog(this,
                            "Desativado com sucesso"
                    );
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Não foi possivel desativar");
                }
            }
        }
        try {
            marcas = marcaService.getAll("");
        } catch (Exception ex){}
        modeloMarca.setList(marcas.toArray());
        jTable1.setModel(modeloMarca);
        cadastrarMarcaForm.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
