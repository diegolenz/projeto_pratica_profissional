package gui.modeltable;

import lib.model.comercial.ItemProduto;
import lib.model.produto.Produto;

import javax.swing.table.AbstractTableModel;

public class TableModelItemProduto extends AbstractTableModel {



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
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemProduto p = (ItemProduto) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getCodigoBarras();
            case 3:
                return p.getQuantidade();
            case 4:
                return p.getValor();
            case 5:
                return p.getDescontoUnitario();
            case 6:
                return p.getAcrescimoUnitario();
            case 7:
                return p.getValorTotal();
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
                return "Código de barras";
            case 3:
                return "Quantidade";
            case 4:
                return "Custo";
            case 5:
                return "Descontos";
            case 6:
                return "Acrescimos";
            case 7:
                return "Custo total";


        }
        return "";
    }

}
