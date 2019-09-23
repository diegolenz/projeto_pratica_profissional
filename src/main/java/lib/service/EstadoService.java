package lib.service;

import lib.dao.imp.endereco.estado.EstadoDao;
import lib.model.endereco.estado.Estado;
import org.springframework.util.Assert;


import java.util.List;

public class EstadoService {

    EstadoDao estadoDao;

    public void save(Estado estado) throws Exception{
        Assert.notNull(estado, "estado não pode estar nulo");
        Assert.notNull(estado.getNome(), "Campo Nome precisa ser preenchido");
        Assert.notNull(estado.getSigla(), "Campo sigla é obrigatório");
        Assert.notNull(estado.getPais(), "Selecione um país");
        estadoDao.save(estado);
    }

    public void update(Estado estado) throws Exception{
        Assert.notNull(estado, "estado não pode estar nulo");
        Assert.notNull(estado.getId(), "Código do estado não pode estar nulo");
        Assert.notNull(estado.getNome(), "Campo Nome precisa ser preenchido");
        Assert.notNull(estado.getSigla(), "Campo sigla é obrigatório");
        Assert.notNull(estado.getPais(), "Selecione um país");
        estadoDao.update(estado);
    }

    public List getAll(String termos) throws Exception {
        return estadoDao.getAll(termos);
    }

    public List getAllAtivos(String termo) throws Exception {
        List list = estadoDao.getAllAtivos(termo);
       // Assert.isTrue(!list.isEmpty(), "Nenhum resultado encontrado");
        return list;
    }


    public Estado getEstadoByID(Integer id) throws Exception {
        Assert.notNull(id, "ID passado como parametro não pode estar nulo");
        Estado estado = estadoDao.getByID(id);
     //   Assert.notNull(estado, "Não foi encontrado nenhum estado com esse código");
        return  estado;
    }

    public void deleteByID(Integer id) throws Exception {
        estadoDao.deleteByID(id);
    }

    public List getByPais(Integer idPais) throws Exception {
        return estadoDao.getByPais(idPais);
    }

    public Estado getLast(Estado estado)throws Exception{
        return estadoDao.getLast().get();

    }

    public EstadoService() {
        this.estadoDao = new EstadoDao();
    }
}
