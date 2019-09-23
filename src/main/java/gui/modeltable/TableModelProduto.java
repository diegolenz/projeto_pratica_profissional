package gui.modeltable;

import lib.model.produto.Produto;

import javax.swing.table.AbstractTableModel;


public class TableModelProduto extends AbstractTableModel {

    public TableModelProduto() {
        this.list = new Object[0];
    }


    private static final long serialVersionUID = 1L;

    protected Object list[];

    public Object[] getList() {
        return list;
    }

    public void setList(Object[] list) {
        this.list = list;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.list.length;
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto p = (Produto) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getCodigoBarras();
            case 3:
                return p.getReferencia();
            case 4:
                return p.getMarca().getNome();
            case 5:
                return p.getGrupo().getNome();
            case 6:
                return p.getQuantidadeEstoque();
            case 7:
                return p.getQuantidadeMinima();
            case 8:
                return p.getPrecoCompra();
            case 9:
                return p.getValor();
            case 10:
                return p.getAtivo() ? "ATIVO" : "DESATIVADO";
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Código";
            case 1:
                return "Produto";
            case 2:
                return "Cód. de barras";
            case 3:
                return "Referencia";
            case 4:
                return "Marca";
            case 5:
                return  "Grupo";
            case 6:
                return "Qtd atual";
            case 7:
                return "Qtd minima";
            case 8:
                return "Custo de compra";
            case 9:
                return "Custo de venda";
            case 10:
                return "Status";

        }
        return "";
    }

}