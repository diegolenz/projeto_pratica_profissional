package lib.dao.imp.comercial.compras;

import lib.dao.AbstractDao;
import lib.model.comercial.ItemServico;

import java.sql.SQLException;

public class ItemServicoDao extends AbstractDao {
   // ItemServico itemServico ;

    public void save(ItemServico itemServico) throws SQLException {
        String sql = "INSERT INTO item_servico (id, nome, qtd_total, desconto_unitario, acrescimo_unitario, valorTotal)" +
                " values ("
                + itemServico.getId() + ", '"
                + itemServico.getNome()+ "', "
                + itemServico.getQtd() + ", "
                + itemServico.getDescontoUnitario() + ","
                + itemServico.getAcrescimoUnitario() + ", "
                + itemServico.getValorTotal() + " "
                + ") ; ";
        this.st.executeUpdate(sql);
    }

    public void update(ItemServico itemServico) throws SQLException {
    }


}
