package gui.modeltable;

import lib.model.endereco.estado.Estado;
import lib.model.grupo.Grupo;

import javax.swing.table.AbstractTableModel;

public class TableModelGrupo  extends AbstractTableModel {

    public TableModelGrupo() {

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
        Grupo p = (Grupo) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getAtivo()? "ATIVADO" : "DESATIVADO";

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
                return "Status";

        }
        return "";
    }



}
