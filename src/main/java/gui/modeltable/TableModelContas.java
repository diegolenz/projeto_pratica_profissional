package gui.modeltable;

import lib.model.comercial.ContaPagar;
import util.Util;

import javax.swing.table.AbstractTableModel;

public class TableModelContas  extends AbstractTableModel {

    public TableModelContas() {

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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ContaPagar p = (ContaPagar) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getParcela().getNumero();
            case 1:
                return Util.builDataSimples(p.getDataLancamento());
            case 2 :
                return Util.builDataSimples(p.getDataVencimento());
            case 3:
                return p.getValor();
            case 4:
                return p.getStatusConta();
            case 5:
                return p.getParcela().getFormaPagamento();



        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "numero";
            case 1:
                return "lançamento";
            case 2:
                return "Vencimento";
            case 3:
                return "Valor";
            case 4:
                return "status";
            case 5:
                return "Forma pagamento";


        }
        return "";
    }



}
