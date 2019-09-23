package lib.dao.imp.produto;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lib.model.grupo.Grupo;
import lib.model.marca.Marca;
import lib.model.produto.Produto;
import lib.service.GrupoService;
import lib.service.MarcaService;



import lib.dao.AbstractDao;

public class ProdutoDao<T> extends AbstractDao<T>{

	public Produto getLast() throws Exception{
			String sql = "Select id from produto order by ID desc limit 1;";
			ResultSet resultSet = this.st.executeQuery(sql);
			Integer id = null;
			if (resultSet.next()){
				id = resultSet.getInt("id");
			}
			Optional<Object> cidade = Optional.ofNullable(this.getByID(id));
			return (Produto) cidade.get();
		}


	public void save(Produto produto) throws Exception {
		String sql = "INSERT INTO produto (nome, ativo, data_ultima_alteracao, unidade_medida, data_cadastro, quantidade_estoque, quantidade_minima, marca_id, grupo_id, referencia, codigo_barras, valor) values ('"+produto.getNome()+"', "+ produto.getAtivo() +
				", '"+produto.getDataUltimaAlteracao() +"', '"+produto.getUnidadeMedida()+"', '"+produto.getDataCadastro()+"', "+ produto.getQuantidadeEstoque() +", "+produto.getQuantidadeMinima()+ ", "+produto.getMarca().getId()+", "+ produto.getGrupo().getId() +
				", '"+produto.getReferencia()+"', '"+produto.getCodigoBarras()+"', "+produto.getValor()+" ) ; ";
		this.st.executeUpdate(sql);
	}

	public void update(Produto produto) throws Exception{
		String sql = "update produto set nome = '"+ produto.getNome() +"', ativo = "+ produto.getAtivo() +", data_ultima_alteracao = '"+produto.getDataUltimaAlteracao()  +"', unidade_medida = '"+produto.getUnidadeMedida()+
				"', data_cadastro = '"+ produto.getDataCadastro() +"', quantidade_estoque = '"+produto.getQuantidadeEstoque()+"', quantidade_minima = "+produto.getQuantidadeMinima() + ", marca_id = "+produto.getMarca().getId()+"" +
				", grupo_id = "+produto.getGrupo().getId()+", referencia = '"+produto.getReferencia()+"'" +
				", codigo_barras = '"+produto.getCodigoBarras()+"', valor = " + produto.getValor() +" where id = "+ produto.getId() + " ;";
		this.st.executeUpdate(sql);
	}

	public List getAll(String termo) throws Exception{
		String sql ="";
		if (termo.length() == 0)
			sql = "SELECT * FROM produto ORDER BY nome;";
		else if ((termo.matches("[0-9]")))
			sql = "Select * from produto where id = "+ termo +" ORDER BY nome;";
		else
			sql = "SELECT * FROM produto WHERE nome ="+ termo +" ORDER BY nome;";

		ResultSet rs = this.st.executeQuery(sql);
		List produtos = new ArrayList();
		while (rs.next()){
			Produto produto=new Produto();
			produto.setId(rs.getInt("id"));
			produto.setAtivo(rs.getBoolean("ativo"));
			produto.setNome(rs.getString("nome"));
			produto.setUnidadeMedida(rs.getString("unidade_medida"));
			produto.setGrupo((Grupo) new GrupoService().getByID(rs.getInt("grupo_id")));
			produto.setMarca((Marca) new MarcaService().getByID(rs.getInt("marca_id")));
			produto.setValor(rs.getDouble("valor"));
			produto.setQuantidadeEstoque(rs.getDouble("quantidade_estoque"));
			produto.setDataUltimaAlteracao(rs.getDate("data_ultima_alteracao"));
			produto.setDataCadastro(rs.getDate("data_cadastro"));
			produto.setQuantidadeMinima(rs.getDouble("quantidade_minima"));
			produto.setAtivo(rs.getBoolean("ativo"));
			produto.setReferencia(rs.getString("referencia"));
			produto.setCodigoBarras(rs.getString("codigo_barras"));
			produtos.add(produto);
		}
		return produtos;
	}



	public List getAllAtivos(String termo)throws Exception{
		String sql = "";
		if (termo.length() == 0)
			sql = "SELECT * FROM produto where ativo = true ORDER BY nome;";
		else if ((termo.matches("[0-9]")))
			sql = "Select * from produto where id = "+ termo +" and ativo = true ORDER BY nome;";
		else
			sql = "SELECT * FROM produto WHERE nome like '%"+ termo +"%' and ativo = true ORDER BY nome";
		ResultSet rs = this.st.executeQuery(sql);
		List produtos = new ArrayList();
		while (rs.next()){
			Produto produto=new Produto();
			produto.setId(rs.getInt("id"));
			produto.setAtivo(rs.getBoolean("ativo"));
			produto.setNome(rs.getString("nome"));
			produto.setUnidadeMedida(rs.getString("unidade_medida"));
			produto.setGrupo((Grupo) new GrupoService().getByID(rs.getInt("grupo_id")));
			produto.setMarca((Marca) new MarcaService().getByID(rs.getInt("marca_id")));
			produto.setValor(rs.getDouble("valor"));
			produto.setQuantidadeEstoque(rs.getDouble("quantidade_estoque"));
			produto.setDataUltimaAlteracao(rs.getDate("data_ultima_alteracao"));
			produto.setDataCadastro(rs.getDate("data_cadastro"));
			produto.setQuantidadeMinima(rs.getDouble("quantidade_minima"));
			produto.setAtivo(rs.getBoolean("ativo"));
			produto.setReferencia(rs.getString("referencia"));
			produto.setCodigoBarras(rs.getString("codigo_barras"));
			produtos.add(produto);
		}
		return produtos;
	}

	public Object getByID(Integer id)throws Exception{
		String sql = "Select * from produto where id ="+id+" ;";
		ResultSet rs = this.st.executeQuery(sql);
		Produto produto=null;
		while (rs.next()){
			produto=new Produto();
			produto.setId(rs.getInt("id"));
			produto.setAtivo(rs.getBoolean("ativo"));
			produto.setNome(rs.getString("nome"));
			produto.setUnidadeMedida(rs.getString("unidade_medida"));
			produto.setGrupo((Grupo) new GrupoService().getByID(rs.getInt("id")));
			produto.setMarca((Marca) new MarcaService().getByID(rs.getInt("id")));
			produto.setValor(rs.getDouble("valor"));
			produto.setQuantidadeEstoque(rs.getDouble("quantidade_estoque"));
			produto.setDataUltimaAlteracao(rs.getDate("data_ultima_alteracao"));
			produto.setDataCadastro(rs.getDate("data_cadastro"));
			produto.setQuantidadeMinima(rs.getDouble("quantidade_minima"));
			produto.setAtivo(rs.getBoolean("ativo"));
			produto.setCodigoBarras(rs.getString("codigo_barras"));

		}
		return produto;
	}

	public void deleteByID(Integer id)throws Exception{
		String sql = "DELETE  FROM produto WHERE id = "+id+" ;";
		this.st.executeUpdate(sql);
	}




}
