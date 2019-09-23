package gui.modeltable;

import lib.model.endereco.cidade.Cidade;
import lib.model.endereco.estado.Estado;

import javax.swing.table.AbstractTableModel;

public class TableModelCidade  extends AbstractTableModel {

    public TableModelCidade() {

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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cidade p = (Cidade) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getEstado().getNome();
            case 3:
                return p.getEstado().getPais().getNome();
            case 4:
                return p.getAtivo()? "Ativo" : "Desativado";
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Código";
            case 1:
                return "Cidade";
            case 2:
                return "Estado";
            case 3:
                return "País";
            case 4:
                return "Status";
        }
        return "";
    }



}
