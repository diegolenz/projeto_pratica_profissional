package gui.modeltable;


import lib.model.interno.Funcionario;
import javax.swing.table.AbstractTableModel;

public class TableModelOperador extends AbstractTableModel {

    public TableModelOperador() {

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
        Funcionario p = (Funcionario) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getCpfCnpj();
            case 3:
                return p.getUsuario();
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
                return "Documento";
            case 3:
                return "Usuário";

        }
        return "";
    }



}

