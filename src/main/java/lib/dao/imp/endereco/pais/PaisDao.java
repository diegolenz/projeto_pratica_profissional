package lib.dao.imp.endereco.pais;

import lib.dao.AbstractDao;
import lib.model.endereco.pais.Pais;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDao extends AbstractDao {

    private Pais pais;

    public void save(Object obj) throws Exception {
        Pais pais=(Pais) obj;
        String sql="";
        sql=("INSERT INTO pais (nome, ddi, ativo) values (" +
                "'" +  pais.getNome()+ "','"+pais.getDdi()+ "', "+pais.getAtivo()+" );");

        this.st.executeUpdate(sql);
    }


    public void deleteByID(Object id) throws Exception {
        String sql = "DELETE FROM pais WHERE id = "+id+" ;";
        this.st.executeUpdate(sql);
    }


    public List getAll(String termo) throws SQLException {
        String sql = "";
        if (termo.length() == 0)
            sql = "SELECT * FROM pais where ;";
        else if ((termo.matches("[0-9]")))
            sql = "Select * from pais where id like %"+ termo +"% ;";
        else
            sql = "SELECT * FROM pais WHERE nome like '%"+ termo +"%' ;";
        ResultSet rs = this.st.executeQuery(sql);
        List<Pais> paises=new ArrayList<>();
        while (rs.next()) {
            Pais pais=new Pais();
            pais.setId(rs.getInt("id"));
            pais.setNome(rs.getString("nome"));
            pais.setDdi(rs.getString("ddi"));
            pais.setAtivo(rs.getBoolean("ativo"));
            paises.add(pais);
        }
        return paises;
    }

    public List getAllAtivos(String termo) throws SQLException {
        String sql = "";
        if (termo.length() == 0)
            sql = "SELECT * FROM pais where ativo = true ;";
        else if ((termo.matches("[0-9]")))
            sql = "Select * from pais where id = "+ termo +" and ativo = true ;";
        else
            sql = "SELECT * FROM pais WHERE nome like '%"+ termo +"%' and ativo = true";
        ResultSet rs = this.st.executeQuery(sql);
        List<Pais> paises=new ArrayList<>();
        while (rs.next()) {
            Pais pais=new Pais();
            pais.setId(rs.getInt("id"));
            pais.setNome(rs.getString("nome"));
            pais.setDdi(rs.getString("ddi"));
            pais.setAtivo(rs.getBoolean("ativo"));
            paises.add(pais);
        }
        return paises;
    }

    public Pais getByID(Integer id) throws SQLException {
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM pais WHERE id = "+id+";");
    //    ResultSet rs = this.st.executeQuery("SELECT * FROM pais WHERE ID = "+id+";");
        ResultSet rs = preparedStatement.executeQuery();
        Pais pais=new Pais();

        while (rs.next()) {
            pais.setId(rs.getInt("id"));
            pais.setNome(rs.getString("nome"));
            pais.setDdi(rs.getString("ddi"));
            pais.setAtivo(rs.getBoolean("ativo"));
        }
        return pais;
    }



    public void update(Object obj) throws SQLException{
        pais=(Pais) obj;
        String sql = "UPDATE pais SET nome = '"+pais.getNome()+"', ddi = '"+ pais.getDdi()+"', ativo="+pais.getAtivo()+" WHERE id = "+ pais.getId()+" ;";
        this.st.executeUpdate(sql);
    }

}
