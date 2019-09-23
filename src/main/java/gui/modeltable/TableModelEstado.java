package gui.modeltable;

import lib.model.endereco.estado.Estado;
import lib.model.endereco.pais.Pais;

import javax.swing.table.AbstractTableModel;

public class TableModelEstado extends AbstractTableModel {

    public TableModelEstado() {

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
        Estado p = (Estado) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getSigla();
            case 3 :
                return p.getPais().getNome();
            case 4:
                return p.getAtivo()? "Ativado" : "Desativado";



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
                return "Sigla";
            case 3:
                return "Pais";
            case 4:
                return "Status";


        }
        return "";
    }



}
