/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.sistema;

import lib.model.interno.CategoriaModuloSistema;
import lib.model.interno.ModuloSistema;
import lib.model.interno.NivelAcessoModulo;
import lib.model.interno.PermissaoAcesso;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author diego.lenz
 */
public class GrupoPermissaoPanel extends javax.swing.JPanel {
    private final CategoriaModuloSistema categoria;
    private final Map<ModuloSistema, PermissaoPanel> paineis;

    public GrupoPermissaoPanel( CategoriaModuloSistema categoria, boolean somenteLeitura) {
        this.categoria = categoria;
        this.paineis = new HashMap<>();
        initComponents();
        lblCategoria.setText(categoria.getDescricao());

        initPaineis(somenteLeitura);

//        if(somenteLeitura) {
//            lblDesmarcarTodos.setVisible(false);
//            lblSelecionarTodos.setVisible(false);
//        }
    }

    private void initPaineis(boolean somenteLeitura) {
        Arrays.stream(ModuloSistema.values())
                .filter(item -> item.getCategoria().equals(categoria))
                .forEach(item -> {
                    PermissaoPanel panel = new PermissaoPanel(item, somenteLeitura);
                    paineis.put(item, panel);
                    pnlPaineis.add(panel);

                });

        pnlPaineis.revalidate();

        revalidate();
    }

    /**
     * MÃ©todo de construÃ§Ã£o da lista de permissÃµes selecionadas pelo operador
     *
     * @return Lista de permissÃµes selecionadas
     * @author Lucas Dillmann
     * @since 1.3.1
     */
    public List<PermissaoAcesso> buildPermissoes() {
        return paineis.entrySet()
                .stream()
                .filter(item -> item.getValue().isModuloSelecionado())
                .map(item -> {
                    PermissaoAcesso permissao = new PermissaoAcesso();
                    permissao.setModulo(item.getValue().getModulo());
                    permissao.setNivelAcesso(
                            item.getValue().isLeituraGravacaoSelecionado()
                                    ? NivelAcessoModulo.LEITURA_GRAVACAO
                                    : NivelAcessoModulo.SOMENTE_LEITURA
                    );

                    return permissao;
                })
                .collect(Collectors.toList());
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPaineis = new javax.swing.JPanel();
        lblSelecionarTodos = new javax.swing.JLabel();
        lblDesmarcarTodos = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlPaineis.setBackground(new java.awt.Color(255, 255, 255));
        pnlPaineis.setMaximumSize(new java.awt.Dimension(560, 23));
        pnlPaineis.setMinimumSize(new java.awt.Dimension(560, 23));
        pnlPaineis.setPreferredSize(new java.awt.Dimension(560, 23));
        pnlPaineis.setLayout(new javax.swing.BoxLayout(pnlPaineis, javax.swing.BoxLayout.Y_AXIS));

        lblSelecionarTodos.setForeground(new java.awt.Color(0, 0, 153));
        lblSelecionarTodos.setText("<html><u>Selecionar todos</u></html>");
        lblSelecionarTodos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSelecionarTodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSelecionarTodosMouseClicked(evt);
            }
        });

        lblDesmarcarTodos.setForeground(new java.awt.Color(0, 0, 153));
        lblDesmarcarTodos.setText("<html><u>Desmarcar todos</u></html>");
        lblDesmarcarTodos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDesmarcarTodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDesmarcarTodosMouseClicked(evt);
            }
        });

        lblCategoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCategoria.setText("{categoriaModuloSistema.descricao}");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSelecionarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDesmarcarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(pnlPaineis, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSelecionarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDesmarcarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPaineis, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblSelecionarTodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSelecionarTodosMouseClicked
        paineis.values().forEach(item -> item.setModuloSelecionado(true));
    }//GEN-LAST:event_lblSelecionarTodosMouseClicked

    private void lblDesmarcarTodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDesmarcarTodosMouseClicked
        paineis.values().forEach(item -> item.setModuloSelecionado(false));
    }//GEN-LAST:event_lblDesmarcarTodosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDesmarcarTodos;
    private javax.swing.JLabel lblSelecionarTodos;
    private javax.swing.JPanel pnlPaineis;
    // End of variables declaration//GEN-END:variables
}
