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

    public GrupoPermissaoPanel( CategoriaModuloSistema categoria, List<PermissaoAcesso> permissoes) {
        this.categoria = categoria;
        this.paineis = new HashMap<>();
        initComponents();
        lblCategoria.setText(categoria.getDescricao());

        initPaineis(permissoes);

//        if(somenteLeitura) {
//            lblDesmarcarTodos.setVisible(false);
//            lblSelecionarTodos.setVisible(false);
//        }
    }

    private void initPaineis(List<PermissaoAcesso> permissaoAcessos) {
        Arrays.stream(ModuloSistema.values())
                .filter(item -> item.getCategoria().equals(categoria))
                .forEach(item -> {
                    PermissaoPanel panel = new PermissaoPanel(item);
                    paineis.put(item, panel);
                    for (PermissaoAcesso permissaoAcesso : permissaoAcessos){
                        if (permissaoAcesso.getModulo().equals(item)){
                            if (permissaoAcesso.getNivelAcesso().equals(NivelAcessoModulo.LEITURA_GRAVACAO)){
                                panel.setGravacao();
                            } else
                                panel.setLeitura();
                        }
                    }
                    pnlPaineis.add(panel);
                });

        pnlPaineis.revalidate();
        revalidate();
    }

    private void initSelecoes(List<ModuloSistema> modulosSistemas){
    }

    /**
     *
     *
     * @return Lista de permissÃµes selecionadas
     * @author Lucas Dillmann
     * @since 1.3.1
     */
    public List<PermissaoAcesso> buildPermissoes() {
        return paineis.entrySet()
                .stream()
                .filter(item -> item.getValue().isLeituraSelecionado() || item.getValue().isLeituraGravacaoSelecionado())
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
        lblCategoria = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlPaineis.setBackground(new java.awt.Color(255, 255, 255));
        pnlPaineis.setMaximumSize(new java.awt.Dimension(560, 23));
        pnlPaineis.setMinimumSize(new java.awt.Dimension(560, 23));
        pnlPaineis.setPreferredSize(new java.awt.Dimension(560, 23));
        pnlPaineis.setLayout(new javax.swing.BoxLayout(pnlPaineis, javax.swing.BoxLayout.Y_AXIS));

        lblCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCategoria.setText("{categoriaModuloSistema.descricao}");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCategoria)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlPaineis, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPaineis, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JPanel pnlPaineis;
    // End of variables declaration//GEN-END:variables
}
