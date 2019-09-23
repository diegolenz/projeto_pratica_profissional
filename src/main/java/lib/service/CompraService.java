package lib.service;

import lib.dao.imp.comercial.compras.CompraDao;
import lib.model.comercial.Compra;
import org.springframework.util.Assert;

import java.util.Date;
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

    public List getAllComprasAtivos(String termo) throws Exception {
        return null;// compraDAO.getAllCompras(termo);
    }

    public List getAllCompras(String termo) throws Exception {
        return null;// compraDAO.getAllCompras(termo);
    }


    public void deleteByID(Compra compra) throws Exception {
        compraDAO.deleteByID(compra.getNumeroNota(), compra.getNumSerieNota(), compra.getModeloNota());
    }


}
