package gui.modeltable;

import lib.model.financeiro.CondicaoPagamento.CondicaoPagamento;

import javax.swing.table.AbstractTableModel;

public class TableModelCondicaoPagamento extends AbstractTableModel {


    public TableModelCondicaoPagamento() {
        this.list = new Object[0];
    }


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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CondicaoPagamento p = (CondicaoPagamento) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getDesconto();
            case 3:
                return p.getMulta();
            case 4:
                return p.getJuros();



        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Codigo";
            case 1:
                return "Nome";
            case 2:
                return "Desconto";
            case 3:
                return "Multa";
            case 4:
                return "Juros";


        }
        return "";
    }
}