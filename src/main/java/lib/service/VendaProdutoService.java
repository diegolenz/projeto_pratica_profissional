package lib.service;

import lib.dao.imp.comercial.venda.VendaProdutoDao;
import lib.dao.imp.produto.ProdutoDao;
import lib.model.comercial.ItemProduto;
import lib.model.comercial.VendaProduto;
import lib.model.produto.Produto;

import java.sql.SQLException;
import java.util.List;

public class VendaProdutoService extends Service {
    /*-------------------------------------------------------------------
     *				 		     ATTRIBUTES
     *-------------------------------------------------------------------*/
    private VendaProdutoDao vendaDAO;

    /*-------------------------------------------------------------------
     *				 		     CONSTRUCTOR
     *-------------------------------------------------------------------*/

    public VendaProdutoService() {
            this.vendaDAO = new VendaProdutoDao();
    }

    /*-------------------------------------------------------------------
     *				 		     SERVICES
     *-------------------------------------------------------------------*/

    public void save(Object venda) throws SQLException {
        vendaDAO.save((VendaProduto) venda);
    }

    public void cancelamento(VendaProduto venda) throws SQLException {
        vendaDAO.st.getConnection().setAutoCommit(false);

        ProdutoDao produtoDao = new ProdutoDao();
        produtoDao.st.getConnection().setAutoCommit(false);
        for (ItemProduto item : venda.getItensProdutos()){
            Produto produto = item.buildProduto(item);
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + item.getQuantidade());
            produtoDao.update(produto);
        }

        // produtoDao.st.getConnection().commit();
        vendaDAO.cancelar(venda);
        vendaDAO.commit();

    }

    public void update(VendaProduto venda) throws SQLException {
        // vendaDAO.update(venda);
    }

    public List getAll(String termo) throws SQLException {
        return vendaDAO.getAll(termo);
    }

    public VendaProduto getByNumSerieModelo(Integer n, String m, Integer s) throws SQLException {
        return (VendaProduto) vendaDAO.getByID(m, n, s);
    }

    public List getAllVendasAtivos(String termo) throws SQLException {
        return null;// vendaDAO.getAllVendas(termo);
    }

    public List getAllVendas(String termo) throws SQLException {
        return null;// vendaDAO.getAllVendas(termo);
    }


}
