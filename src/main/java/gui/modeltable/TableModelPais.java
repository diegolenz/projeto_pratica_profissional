package gui.modeltable;

import lib.model.endereco.pais.Pais;

import javax.swing.table.AbstractTableModel;

public class TableModelPais extends AbstractTableModel {

    public TableModelPais() {

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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pais p = (Pais) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getDdi();
            case 3:
            return p.getAtivo()? "ATIVO" : "INATIVO";



        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "codigo";
            case 1:
                return "nome";
            case 2:
                return "ddi";


        }
        return "";
    }



}