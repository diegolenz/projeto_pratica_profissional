package gui.modeltable;

import lib.model.financeiro.parcela.Parcela;

import javax.swing.table.AbstractTableModel;

public class TableModelParcela extends AbstractTableModel {

    public TableModelParcela() {
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
        Parcela p = (Parcela) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getNumero();
            case 1:
                return p.getDias();
            case 2:
                return p.getPorcentagem();
            case 3:
                return  p.getFormaPagamento().getNome() ;


        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Numero";
            case 1:
                return "Dias";
            case 2:
                return "Porcentagem";
            case 3:
                return "Forma de pagamento";



        }
        return "";
    }

}
