package gui.modeltable;

import lib.model.financeiro.TipoMensalidade;

import javax.swing.table.AbstractTableModel;

public class TableModelTipoMensalidade extends AbstractTableModel {
    public TableModelTipoMensalidade() {

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
        TipoMensalidade p = (TipoMensalidade) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getDescricao();
            case 2:
                return p.getValor();
            case 3:
                return p.getVencimento();

        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "codigo";
            case 1:
                return "descricao";
            case 2:
                return "valor";
            case 3:
                return "vencimento";


        }
        return "";
    }



}

