package gui.modeltable;

import lib.model.financeiro.caixa.Caixa;

import javax.swing.table.AbstractTableModel;

public class TableModelCaixas extends AbstractTableModel {

    public TableModelCaixas() {

        this.list = new Object[0];
    }



    protected Object list[];

    public Object[] getList() {
        return list;
    }

    public void setList(Object[] list){
        this.list = list;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.list.length;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Caixa p = (Caixa) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getStatusCompleto();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Código";
            case 1:
                return "Caixa";
            case 2:
                return "Status";
        }
        return "";
    }
}
