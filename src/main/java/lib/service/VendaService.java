package lib.service;

import lib.dao.imp.financeiro.venda.VendaDao;
import lib.dao.imp.produto.ProdutoDao;
import lib.model.comercial.Venda;
import lib.model.comercial.ItemProduto;
import lib.model.produto.Produto;

import java.sql.SQLException;
import java.util.List;

public class VendaService {
    /*-------------------------------------------------------------------
     *				 		     ATTRIBUTES
     *-------------------------------------------------------------------*/
    private VendaDao vendaDAO;

    /*-------------------------------------------------------------------
     *				 		     CONSTRUCTOR
     *-------------------------------------------------------------------*/

    public VendaService() {
        this.vendaDAO = new VendaDao();
    }

    /*-------------------------------------------------------------------
     *				 		     SERVICES
     *-------------------------------------------------------------------*/

    public void save(Object venda) throws SQLException {
        vendaDAO.save((Venda) venda);
    }

    public void cancelamento(Venda venda) throws SQLException {
        vendaDAO.st.getConnection().setAutoCommit(false);

        ProdutoDao produtoDao = new ProdutoDao();
        produtoDao.st.getConnection().setAutoCommit(false);
        for (ItemProduto item : venda.getItensProdutos()){
            Produto produto = item.buildProduto(item);
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtoDao.update(produto);
        }

        // produtoDao.st.getConnection().commit();
        vendaDAO.cancelar(venda);
        vendaDAO.commit();

    }

    public void update(Venda venda) throws Exception {
        // vendaDAO.update(venda);
    }

    public List getAll(String termo) throws Exception {
        return vendaDAO.getAll(termo);
    }

    public Venda getByNumSerieModelo(Integer n, String m, Integer s, Integer f) throws Exception {
        return (Venda) vendaDAO.getByID(m, n, s,f);
    }

    public List getAllVendasAtivos(String termo) throws Exception {
        return null;// vendaDAO.getAllVendas(termo);
    }

    public List getAllVendas(String termo) throws Exception {
        return null;// vendaDAO.getAllVendas(termo);
    }


}
