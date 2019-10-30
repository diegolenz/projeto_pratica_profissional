package gui.modeltable;

import lib.model.interno.GrupoFuncionario;

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
        GrupoFuncionario p = (GrupoFuncionario) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return "";
            case 3:
                return "";


        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Código";
            case 1:
                return "Nome";
            case 2:
                return "Data cadastro";
            case 3:
                return "Data da ultima alteração";
        }
        return "";
    }

}
