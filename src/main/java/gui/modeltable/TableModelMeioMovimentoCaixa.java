package gui.modeltable;

import lib.model.financeiro.formaPagamento.FormaPagamento;

import javax.swing.table.AbstractTableModel;

public class TableModelMeioMovimentoCaixa extends AbstractTableModel {

    public TableModelMeioMovimentoCaixa() {
        // TODO Auto-generated constructor stub
    }





    protected Object list[];

    public Object[] getList() {
        return list;
    }

    public void setList(Object[] list) {
        this.list = list;
        this.fireTableDataChanged();
    }

    //  @Override
    public int getRowCount() {
        return this.list.length;
    }

    //@Override
    public int getColumnCount() {
        return 3;
    }

    //@Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FormaPagamento f = (FormaPagamento) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return f.getId();
            case 1:
                return f.getNome();
            case 2:
                return f.getAtivo()==true? "Ativado" : "Desativado";

        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Forma de pagamento";
            case 2:
                return "Status";


        }
        return "";
    }

}
