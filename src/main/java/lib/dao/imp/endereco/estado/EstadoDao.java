package lib.dao.imp.endereco.estado;

import lib.dao.AbstractDao;
import lib.model.endereco.cidade.Cidade;
import lib.model.endereco.estado.Estado;

import lib.service.PaisService;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class
EstadoDao extends AbstractDao {

    private Estado estado;
    private PaisService paisService;

    public EstadoDao() {
        this.estado = new Estado();
        this.paisService = new PaisService();
      //  this.estadoDao = new EstadoDao();

    }

    public void save(Object obj) throws Exception {
        Estado estado = (Estado) obj;
        String sql = "";
        sql = ("INSERT INTO estado (nome, sigla, pais_id, ativo ) values (" +
                "'" + estado.getNome() + "','" + estado.getSigla() + "', " + estado.getPais().getId() + ", "+estado.getAtivo()+" );");

        this.st.executeUpdate(sql);
    }

    public void deleteByID(Object id) throws Exception {
        String sql = "DELETE FROM estado WHERE id = " + id + " ;";
        this.st.executeUpdate(sql);
    }


    public Optional<Estado> getLast()throws Exception{
        String sql = "Select id from estado order by ID desc limit 1;";
        ResultSet resultSet = this.st.executeQuery(sql);
        Integer id = null;
        if (resultSet.next()){
            id = resultSet.getInt("id");
        }
        Optional<Estado> estado = Optional.ofNullable(this.getByID(id));
        return estado;
    }

    public List getAll(String termo) throws Exception {
        String sql = "";
        if (termo.length() == 0)
            sql = "SELECT * FROM estado  ;";
        else if ((termo.matches("[0-9]")))
            sql = "Select * from estado where id like %"+ termo +"% ;";
        else
            sql = "SELECT * FROM estado WHERE nome like '%"+ termo +"%' ;";
        ResultSet rs = this.st.executeQuery(sql);
        List<Estado> estados = new ArrayList<>();

        while (rs.next()) {
            Estado estado = new Estado();
            estado.setId(rs.getInt("id"));
            estado.setNome(rs.getString("nome"));
            estado.setSigla(rs.getString("sigla"));
            estado.setPais( paisService.getPaisByID(rs.getInt("pais_id")));
            estado.setAtivo( rs.getBoolean("ativo"));
            estados.add(estado);
        }
        return estados;
    }

    public List getAllAtivos(String termo) throws Exception {

        String sql = "";
        if (termo.length() == 0)
            sql = "SELECT * FROM estado where ativo = true ;";
        else if ((termo.matches("[0-9]")))
            sql = "Select * from estado where id like %"+ termo +"% and ativo = true ;";
        else
            sql = "SELECT * FROM estado WHERE nome like '%"+ termo +"%' and ativo = true";
        ResultSet rs = this.st.executeQuery(sql);
        List<Estado> estados = new ArrayList<>();

        while (rs.next()) {
            Estado estado = new Estado();
            estado.setId(rs.getInt("id"));
            estado.setNome(rs.getString("nome"));
            estado.setSigla(rs.getString("sigla"));
            estado.setPais( paisService.getPaisByID(rs.getInt("pais_id")));
            estado.setAtivo( rs.getBoolean("ativo"));
            estados.add(estado);
        }
        return estados;
    }



    public void update(Object obj) throws SQLException {
        estado = (Estado) obj;
        String sql = "UPDATE estado SET nome = '" + estado.getNome() + "', sigla = '" + estado.getSigla() + "', pais_id = "+estado.getPais().getId()+", ativo=" + estado.getAtivo() + " WHERE id = " + estado.getId() + " ;";
        this.st.executeUpdate(sql);
    }

    public List<Estado> getByPais(Integer idPais) throws Exception {

        ResultSet rs = this.st.executeQuery("SELECT * FROM estado where pais_id" + idPais + " ;");
        List<Estado> estados=new ArrayList<>();
        while (rs.next()) {
            Estado estado = new Estado();
            estado.setId(rs.getInt("id"));
            estado.setNome(rs.getString("nome"));
            estado.setSigla(rs.getString("sigla"));
            estado.setPais(paisService.getPaisByID(rs.getInt("pais_id")));
            estados.add(estado);
        }
        return estados;
    }

    public Estado getByID(Integer id) throws Exception {
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM estado WHERE ID = "+id+";");
        ResultSet rs = preparedStatement.executeQuery();
        Estado estado=new Estado();
        while (rs.next()) {
            estado.setId(rs.getInt("id"));
            estado.setNome(rs.getString("nome"));
            estado.setSigla(rs.getString("sigla"));
            estado.setAtivo(rs.getBoolean("ativo"));
            estado.setPais(paisService.getPaisByID(rs.getInt("pais_id")));
        }
        return estado;
    }




}
