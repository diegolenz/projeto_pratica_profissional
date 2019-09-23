package lib.dao.imp.endereco.cidade;

import lib.dao.AbstractDao;
import lib.model.endereco.cidade.Cidade;
import lib.service.EstadoService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CidadeDao extends AbstractDao {


    private Cidade cidade;
    private EstadoService estadoService;

    public CidadeDao() {
        this.cidade = new Cidade();
        this.estadoService = new EstadoService();
        //  this.cidadeDao = new CidadeDao();

    }

    public Optional<Cidade> getLast()throws Exception{
        String sql = "Select id from cidade order by ID desc limit 1;";
        ResultSet resultSet = this.st.executeQuery(sql);
        Integer id = null;
        if (resultSet.next()){
            id = resultSet.getInt("id");
        }
        Optional<Cidade> cidade = Optional.ofNullable(this.getByID(id));
        return cidade;
    }

    public void save(Object obj) throws Exception {
        Cidade cidade = (Cidade) obj;
        String sql = "";
        sql = ("INSERT INTO cidade (nome, DDD, estado_id, ativo ) values (" +
                "'" + cidade.getNome() + "','" + cidade.getDDD() + "', " + cidade.getEstado().getId() + ", " + cidade.getAtivo() + " );");

        this.st.executeUpdate(sql);
    }

    public void deleteByID(Object id) throws Exception {
        String sql = "DELETE FROM cidade WHERE id = " + id + " ;";
        this.st.executeUpdate(sql);
    }


    public List getAll() throws Exception {

        ResultSet rs = this.st.executeQuery("SELECT * FROM cidade order by nome;");
        List<Cidade> cidades = new ArrayList<>();

        while (rs.next()) {
            Cidade cidade = new Cidade();
            cidade.setId(rs.getInt("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setDDD(rs.getString("DDD"));
            cidade.setEstado(estadoService.getEstadoByID(rs.getInt("estado_id")));
            cidade.setAtivo(rs.getBoolean("ativo"));
            cidades.add(cidade);
        }
        return cidades;
    }

    public List getAllAtivos(String termo) throws Exception {
        String sql = "";
        if (termo.length() == 0)
            sql = "SELECT * FROM cidade where ativo = true ;";
        else if ((termo.matches("[0-9]")))
            sql = "Select * from cidade where id = "+ termo +" and ativo = true ;";
        else
            sql = "SELECT * FROM cidade WHERE nome like '%"+ termo +"%' and ativo = true";
        ResultSet rs = this.st.executeQuery(sql);
        List<Cidade> cidades = new ArrayList<>();

        while (rs.next()) {
            Cidade cidade = new Cidade();
            cidade.setId(rs.getInt("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setDDD(rs.getString("DDD"));
            cidade.setEstado(estadoService.getEstadoByID(rs.getInt("estado_id")));
            cidade.setAtivo(rs.getBoolean("ativo"));
            cidades.add(cidade);
        }
        return cidades;
    }

    public void update(Object obj) throws SQLException {
        cidade = (Cidade) obj;
        String sql = "UPDATE cidade SET nome = '" + cidade.getNome() + "', DDD = '" + cidade.getDDD() + "', estado_id=" + cidade.getEstado().getId() + ", ativo=" + cidade.getAtivo() + " WHERE id = " + cidade.getId() + " ;";
        this.st.executeUpdate(sql);
    }

    public List<Cidade> getByEstado(Integer idEstado) throws Exception {
        return null;
    }

    public List<Cidade> getByNomeEstado(String idEstado) throws Exception {

       return this.getAllAtivos("");
    }

    public Cidade getByID(Integer id) throws Exception {
        PreparedStatement preparedStatement=st.getConnection().
                prepareStatement("SELECT * FROM cidade WHERE id = "+id+";");
        ResultSet rs = preparedStatement.executeQuery();
        Cidade cidade=new Cidade();
        while (rs.next()) {
            cidade.setId(rs.getInt("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setDDD(rs.getString("DDD"));
            cidade.setAtivo(rs.getBoolean("ativo"));
            cidade.setEstado(this.estadoService.getEstadoByID(rs.getInt("estado_id")));
        }
        return cidade;
    }
}

