package gui.modeltable;

import lib.model.financeiro.contas.ContaPagar;
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
                return p.getId();
            case 1:
                return p.getDescricao();
            case 2:
                return Util.builDataSimples(p.getDataLancamento());
            case 3 :
                return Util.builDataSimples(p.getDataVencimento());
            case 4:
                return p.getValor();
            case 5:
                return p.getStatusConta();
            case 6:
                return p.getFormaPagamento();



        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "numero";
            case 1:
                return "Descricao";
            case 2:
                return "lançamento";
            case 3:
                return "Vencimento";
            case 4:
                return "Valor";
            case 5:
                return "status";
            case 6:
                return "Forma pagamento";


        }
        return "";
    }



}
