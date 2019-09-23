package gui.modeltable;

import javax.swing.table.AbstractTableModel;

import lib.model.marca.Marca;

public class TableModelMarca extends AbstractTableModel {

	public TableModelMarca() {
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
		Marca f = (Marca) list[rowIndex];
		switch (columnIndex) {
		case 0:
			return f.getId();
		case 1:
		    return f.getNome();
			case 2:
				return f.getAtivo() ? "Ativado" : "Desativado";
		
		}
		return "";
	    }

	    @Override
	    public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Código";
		case 1:
		    return "Marca";
			case 2:
				return "Status";
	
		
		}
		return "";
	    }

	}