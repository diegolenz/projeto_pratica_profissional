package gui.modeltable;



import lib.model.servico.Servico;

import javax.swing.table.AbstractTableModel;

public class TableModelServico extends AbstractTableModel {

    public TableModelServico() {
        this.list = new Object[0];
    }


    private static final long serialVersionUID = 1L;

    protected Object list[];

    public Object[] getList() {
        return list;
    }

    public void setList(Object[] list) {
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
        Servico p = (Servico) list[rowIndex];
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getGrupo().getNome();
            case 3:
                return p.getValor();
            case 4:
                return p.getDt_ult_alteracao();
            case 5:
                return p.isAtivo();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Código";
            case 1:
                return "Serviço";
            case 2:
                return "Grupo";
            case 3:
                return "Custo";
            case 4:
                return "Ultima atualização";
            case 5:
                return "Ativo";
        }
        return "";
    }
}

