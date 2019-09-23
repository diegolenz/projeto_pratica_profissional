package gui.modeltable;

import lib.model.interno.TipoOperador;
import javax.swing.table.AbstractTableModel;

public class TableModelTipoOperador extends AbstractTableModel {


    public TableModelTipoOperador() {
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
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TipoOperador p = (TipoOperador) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getDescricao();



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


        }
        return "";
    }

}
