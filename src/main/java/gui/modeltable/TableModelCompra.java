package gui.modeltable;

import lib.model.comercial.Compra;
import sun.management.Util;

import javax.swing.table.AbstractTableModel;

public class TableModelCompra extends AbstractTableModel {

    public TableModelCompra() {

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
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Compra p = (Compra) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getNumeroNota();
            case 1:
                return p.getNumSerieNota();
            case 2:
                return p.getModeloNota();
            case 3:
                return p.getFornecedor().getNome();
            case 4:
                return p.getFuncionario() == null ? "Suporte" : p.getFuncionario().getNome();
            case 5:
                return util.Util.builDataSimples( p.getDtEmisssao());
            case 6:
                return util.Util.builDataSimples(p.getDtChegada());
            case 7:
                return  "ativo";//p.isAtivo() ? "Ativo" : "Desativado";
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Numero";
            case 1:
                return "Série";
            case 2:
                return "Modelo";
            case 3:
                return "Fornecedor";
            case 4:
                return "Funcionario";
            case 5:
                return "Data de emissão";
            case 6 :
                return "Data de chegada";
            case 7:
                return "Status";
        }
        return "";
    }



}
