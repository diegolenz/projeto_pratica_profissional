package lib.service;

import lib.dao.imp.endereco.cidade.CidadeDao;
import lib.model.endereco.cidade.Cidade;
import org.castor.core.util.Assert;

import java.util.List;

public class CidadeService {

    CidadeDao cidadeDao;

    public void save(Cidade cidade) throws Exception{
        Assert.notNull(cidade, "cidade não pode estar nulo");
        Assert.notNull(cidade.getNome(), "Campo Nome precisa ser preenchido");
        Assert.notNull(cidade.getEstado(), "Estado não pode estar nulo");
        cidadeDao.save(cidade);
    }

    public void update(Cidade cidade) throws Exception{
        Assert.notNull(cidade, "cidade não pode estar nulo");
        Assert.notNull(cidade.getId(), "Código do cidade não pode estar nulo");
        Assert.notNull(cidade.getNome(), "Campo Nome precisa ser preenchido");
        Assert.notNull(cidade.getEstado(), "Estado não pode estar nulo");
        cidadeDao.update(cidade);
    }

    public List getAll() throws Exception {
        return cidadeDao.getAll();
    }

    public List getAllAtivos(String termo) throws Exception {
        return cidadeDao.getAllAtivos(termo);
    }


    public Cidade getCidadeByID(Integer id) throws Exception {
        Assert.notNull(id, "ID passado como parametro não pode estar nulo");
        Cidade cidade = cidadeDao.getByID(id);
        Assert.notNull(cidade.getId(), "Nenhuma cidade foi encontrada com esse código");
        return cidade;
    }

    public void deleteByID(Integer id) throws Exception {
        cidadeDao.deleteByID(id);
    }

    public List getByEstado(Integer idEstado) throws Exception {
        Assert.notNull(idEstado, "ID passado como parametro não pode estar nulo");
        return cidadeDao.getByEstado(idEstado);
    }

    public List getByNomeEstado(String nome) throws Exception {
        return cidadeDao.getByNomeEstado(nome);
    }

  public Cidade getLast(){
        return getLast();
  }

    public CidadeService() {
        this.cidadeDao = new CidadeDao();
    }
}