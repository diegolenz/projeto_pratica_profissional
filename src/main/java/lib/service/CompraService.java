package lib.service;

import lib.dao.imp.comercial.compras.CompraDao;
import lib.model.comercial.Compra;
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

    public void save(Object compra)  throws Exception {
        compraDAO.save((Compra)compra);
    }


    public void update(Compra compra) throws Exception {
       // compraDAO.update(compra);
    }

    public List getAll(String termo) throws Exception {
        return compraDAO.getAll(termo);
    }

    public Compra getByNumSerieModelo(Integer n, String m, Integer s) throws Exception {
        return (Compra) compraDAO.getByID(m,n,s);
    }

    public List getAllComprasAtivos(String termo) throws Exception {
        return null;// compraDAO.getAllCompras(termo);
    }

    public List getAllCompras(String termo) throws Exception {
        return null;// compraDAO.getAllCompras(termo);
    }




}
