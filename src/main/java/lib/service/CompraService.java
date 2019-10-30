package lib.service;

import lib.dao.imp.comercial.compras.CompraDao;
import lib.dao.imp.produto.ProdutoDao;
import lib.model.comercial.Compra;
import lib.model.comercial.ItemProduto;
import lib.model.produto.Produto;

import java.sql.SQLException;
import java.util.List;

public class CompraService {
    /*-------------------------------------------------------------------
     *				 		     ATTRIBUTES
     *-------------------------------------------------------------------*/
    private CompraDao compraDAO;

    /*-------------------------------------------------------------------
     *				 		     CONSTRUCTOR
     *-------------------------------------------------------------------*/

    public CompraService() {
        this.compraDAO = new CompraDao();
    }

    /*-------------------------------------------------------------------
     *				 		     SERVICES
     *-------------------------------------------------------------------*/

    public void save(Object compra) throws SQLException {
        compraDAO.save((Compra) compra);
    }

    public void cancelamento(Compra compra) throws SQLException {
        compraDAO.st.getConnection().setAutoCommit(false);

        ProdutoDao produtoDao = new ProdutoDao();
        produtoDao.st.getConnection().setAutoCommit(false);
        for (ItemProduto item : compra.getItensProdutos()){
            Produto produto = item.buildProduto(item);
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtoDao.update(produto);
        }

       // produtoDao.st.getConnection().commit();
        compraDAO.cancelar(compra);
        compraDAO.commit();

    }

    public void update(Compra compra) throws Exception {
        // compraDAO.update(compra);
    }

    public List getAll(String termo) throws Exception {
        return compraDAO.getAll(termo);
    }

    public Compra getByNumSerieModelo(Integer n, String m, Integer s,Integer f) throws Exception {
        return (Compra) compraDAO.getByID(m, n, s, f);
    }

    public List getAllComprasAtivos(String termo) throws Exception {
        return null;// compraDAO.getAllCompras(termo);
    }

    public List getAllCompras(String termo) throws Exception {
        return null;// compraDAO.getAllCompras(termo);
    }


}
