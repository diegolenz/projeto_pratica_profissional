package lib.service;

import lib.dao.imp.endereco.pais.PaisDao;
import lib.model.endereco.pais.Pais;
import org.springframework.util.Assert;


import java.util.List;

public class PaisService {

    private PaisDao paisDao;

    public void save(Pais pais) throws Exception{
        Assert.notNull(pais, "pais não pode estar nulo");
        Assert.notNull(pais.getNome(), "Campo Nome precisa ser preenchido");
        Assert.notNull(pais.getDdi(), "Campo DDI é obrigatório");
        paisDao.save(pais);
    }

    public void update(Pais pais) throws Exception{
        Assert.notNull(pais, "pais não pode estar nulo");
        Assert.notNull(pais.getId(), "Código do pais não pode estar nulo");
        Assert.notNull(pais.getNome(), "Campo Nome precisa ser preenchido");
        Assert.notNull(pais.getDdi(), "Campo DDI é obrigatório");
        paisDao.update(pais);
    }

    public List getAll(String termos) throws Exception {
        return paisDao.getAll(termos);
    }

    public List getAllAtivos(String termo) throws Exception {
        List list = paisDao.getAllAtivos(termo);
      //  Assert.isTrue(list.size() > 0, "Nenhum resultado encontrado");
        return list;
    }

    public void deleteByID(Integer id) throws Exception {
        paisDao.deleteByID(id);
    }

    public void mudarStatus(Pais pais){ }

    public Pais getPaisByID(Integer id) throws Exception {
        Assert.notNull(id, "ID passado como parametro não pode estar nulo");
        return paisDao.getByID(id);
    }

    public PaisService() {
        this.paisDao = new PaisDao();
    }

}


