package lib.dao.imp.marca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lib.model.marca.Marca;


import lib.dao.AbstractDao;

public class MarcaDao<T> extends AbstractDao<T> {


	public void save(Marca marca) throws Exception {
		String sql = "INSERT INTO MARCA (nome, ativo) values ('"+marca.getNome()+"', "+ marca.getAtivo() +") ; ";
		this.st.executeUpdate(sql);
	}

	public void update(Marca marca) throws  Exception{
		String sql = "UPDATE marca SET nome = '"+ marca.getNome() +"', ativo = "+ marca.getAtivo() +" where id = "+ marca.getId() + " ;";
		this.st.executeUpdate(sql);
	}

	public Optional<Marca> findLast()throws Exception{
	    String sql = "Select id from marca order by ID desc limit 1;";
	    ResultSet resultSet = this.st.executeQuery(sql);
	    Integer id = null;
	    if (resultSet.next()){
	        id = resultSet.getInt("id");
        }
	    Optional<Marca> marca = Optional.ofNullable(this.getByID(id));
	    return marca;
    }

	public List getAll(String termoBusca)throws Exception {
		String sql = "";
		if (termoBusca.length() == 0)
			sql="SELECT * FROM marca ORDER BY nome;";
		else if ((!termoBusca.matches("[0-9]")))
			sql = "Select * from marca where id = "+ termoBusca +" ORDER BY nome;";
		else
			sql = "SELECT * FROM marca WHERE nome ="+ termoBusca +" ORDER BY nome;";

		ResultSet rs = this.st.executeQuery(sql);
		List marcas = new ArrayList();
		while (rs.next()){
			Marca marca=new Marca();
			marca.setId(rs.getInt("id"));
			marca.setAtivo(rs.getBoolean("ativo"));
			marca.setNome(rs.getString("nome"));
			marcas.add(marca);
		}
		return marcas;
	}

	public List getAllAtivos() throws Exception{
		String sql = "Select * from marca where ativo = "+1+" ORDER BY nome;";
		ResultSet rs = this.st.executeQuery(sql);
		List marcas = new ArrayList();
		while (rs.next()){
			Marca marca=new Marca();
			marca.setId(rs.getInt("id"));
			marca.setAtivo(rs.getBoolean("ativo"));
			marca.setNome(rs.getString("nome"));
			marcas.add(marca);
		}
		return marcas;
	}

	public Marca findByNomeExato(String nome)throws Exception {
		String sql = "SELECT * FROM marca WHERE UPPER(nome) = UPPER('" + nome + "') ;";
		ResultSet resultSet = this.st.executeQuery(sql);
		Optional<Marca> optionalMarca ;
	if (resultSet.next()) {
			Marca marca = new Marca();
			marca.setId(resultSet.getInt("id"));
			marca.setAtivo(resultSet.getBoolean("ativo"));
			marca.setNome(resultSet.getString("nome"));
			return marca;
		}
		return null;
	}

	public Marca getByID(Integer id) throws Exception{
		String sql = "Select * from marca where id ="+id+" ;";
		PreparedStatement preparedStatement=st.getConnection().prepareStatement(sql);
		ResultSet rs =  preparedStatement.executeQuery();
		Marca marca = null;
		while (rs.next()){
			marca=new Marca();
			marca.setId(rs.getInt("id"));
			marca.setAtivo(rs.getBoolean("ativo"));
			marca.setNome(rs.getString("nome"));

		}
		return marca;
	}

	public void deleteByID(Integer id) throws Exception{
		String sql = "DELETE FROM marca WHERE id = "+id+" ;";
		this.st.executeUpdate(sql);
	}



}
