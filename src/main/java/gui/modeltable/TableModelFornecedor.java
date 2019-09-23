package gui.modeltable;

import lib.model.pessoa.fornecedor.Fornecedor;
import javax.swing.table.AbstractTableModel;

public class TableModelFornecedor extends AbstractTableModel{


        public TableModelFornecedor() {
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
            return 9;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Fornecedor p = (Fornecedor) list[rowIndex];
            switch (columnIndex) {
                case 0:
                    return p.getId();
                case 1:
                    return p.getNome();
                case 2:
                    return p.getTipo();
                case 3:
                    return p.getCpfCnpj();
                case 4:
                    return p.getRgIe();
                case 5:
                    return p.getTelefone();
                case 6:
                    return p.getEmail();
                case 7:
                    return p.getCidade() != null ? p.getCidade().getNome() : "";
                case 8:
                        return p.getAtivo() ? "ATIVADO" : "DESATIVADO";

            }
            return "";
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "código";
                case 1:
                    return "Fornecedor";
                case 2:
                    return "Tipo";
                case 3:
                    return "Cpf/Cnpj";
                case 4:
                    return "RG/Ie";
                case 5:
                    return "Telefone";
                case 6:
                    return "Email";
                case 7:
                    return "Cidade";
                case 8:
                    return "Ativo";

            }
            return "";
        }



}
