package lib.dao.imp.pessoa;

import lib.dao.AbstractDao;
import lib.model.pessoa.Sexo;
import lib.model.pessoa.TipoPessoa;
import lib.model.pessoa.fornecedor.Fornecedor;
import lib.service.CidadeService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDao extends AbstractDao {

    private Fornecedor fornecedor;

    public FornecedorDao() {
        this.fornecedor = new Fornecedor();
    }

    public Integer getUltimoIDPessoa()throws Exception {
       ResultSet resultSet = this.st.executeQuery("Select id from pessoa order by ID desc limit 1");
       Integer id=null;
       while (resultSet.next())
            id = resultSet.getInt("id");
       return id;
    }

    public void save(Object obj) throws Exception {
        Fornecedor fornecedor = (Fornecedor) obj;
        String date = "NULL";
        if (fornecedor.getDataNascimento() !=null)
            date = "'" + fornecedor.getDataNascimento().toString() +"'";

        // Insere informações da pessoa
        String sql = "INSERT INTO pessoa (" +
                " nome," +
                " cpf_cnpj," +
                " data_nascimento," +
                " email," +
                " sexo," +
                " nome_fantasia_apelido," +
                " rg_ie," +
                " telefone," +
                " telefone_alternativo," +
                " tipo," +
                " cidade_id," +
                " bairro," +
                " logradouro," +
                " complemento," +
                " numero_residencial, " +
                " cep" +
                ") values (" +
                "'" +    fornecedor.getNome() +
                "','" +  fornecedor.getCpfCnpj() +
                "', " + date +
                ", '" + fornecedor.getEmail() +
                "', " +  fornecedor.getSexo().ordinal() +
                ", '" +  fornecedor.getNomeFantasia_Apelido() +
                "', '" + fornecedor.getRgIe() +
                "', '" + fornecedor.getTelefone() +
                "', '" + fornecedor.getTelefoneAlternativo() +
                "', " +  fornecedor.getTipo().ordinal() +
                ", " +   fornecedor.getCidade().getId() +
                ", '" +  fornecedor.getBairro() +
                "', '" + fornecedor.getLogradouro() +
                "', '" + fornecedor.getComplemento() +
                "', '" +  fornecedor.getNumeroResidencial() +
                "', '" +  fornecedor.getCep() +
                "' ); ";

        this.st.executeUpdate(sql);

        //Insere fornecedor referenciando a ultima pessoa inserida no banco
        String sqlFornecedor =
                "INSERT INTO fornecedor (ativo, data_cadastro, data_ultima_alteracao, pessoa_id) values ("+
                    fornecedor.getAtivo() + ", '" +
                    fornecedor.getDataCadastro() + "','" +
                    fornecedor.getDataUltAlteracao() +"'," +
                        getUltimoIDPessoa() +
                " );";
        this.st.executeUpdate(sqlFornecedor);
    }

    public void deleteByID(Object id) throws Exception {
        String sql = "DELETE FROM fornecedor WHERE id = " + id + " ;";
        this.st.executeUpdate(sql);
    }

    public List getAllFornecedores(String termoBusca) throws Exception {
        String sql ="";
        if (termoBusca.length() ==0)
            sql = "SELECT * FROM fornecedor;" ;
        else if ((termoBusca.matches("[0-9]")))
            sql = "SELECT * FROM fornecedor where  id = "+termoBusca+" " ;
        else
            sql = "select * from fornecedor where pessoa_id = (select id from pessoa where nome like '%"+termoBusca+"%' );";

        ResultSet rs = this.st.executeQuery(sql);
        List<Fornecedor> fornecedors = new ArrayList<>();

        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setAtivo( rs.getBoolean("ativo"));
            fornecedor.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            fornecedor.setDataCadastro(rs.getDate("data_cadastro"));
            getPessoaByID(rs.getInt("pessoa_id"), fornecedor);

            fornecedors.add(fornecedor);
        }
        return fornecedors;
    }

    public void update(Object obj) throws SQLException {
        fornecedor = (Fornecedor) obj;
        String date = "NULL";
        if (fornecedor.getDataNascimento() !=null)
            date = "'" + fornecedor.getDataNascimento().toString() +"'";
        String sql = "UPDATE pessoa SET nome = '" + fornecedor.getNome() +
                "', sexo = " + fornecedor.getSexo().ordinal() +
                ",  nome_fantasia_apelido = '" + fornecedor.getNomeFantasia_Apelido() +
                "', rg_ie = '" + fornecedor.getRgIe() +
                "', telefone = '" + fornecedor.getTelefone()  +
                "', telefone_alternativo = '" + fornecedor.getTelefoneAlternativo() +
                "', tipo = " + fornecedor.getTipo().ordinal() +
                ", data_nascimento = '" + date +
                "', email = '" + fornecedor.getEmail() +
                "', logradouro = '" + fornecedor.getLogradouro() +
                "', complemento = '" + fornecedor.getComplemento() +
                "', cep ='"+ fornecedor.getCep() +
                "', cidade_ID = " + fornecedor.getCidade().getId() +
                ", numero_residencial = '" + fornecedor.getNumeroResidencial() +
                "'    WHERE id = " + fornecedor.getId() + " ;";
        this.st.executeUpdate(sql);
        sql = "UPDATE fornecedor SET ativo = "+fornecedor.getAtivo() +
                ",  data_cadastro = '" + fornecedor.getDataCadastro() +
                "', data_ultima_alteracao = '"+ fornecedor.getDataUltAlteracao() + "' where id = " + fornecedor.getId() +" ;";
        this.st.executeUpdate(sql);
    }

    public List<Fornecedor> getAtivos() throws Exception {

        ResultSet rs = this.st.executeQuery("SELECT * FROM fornecedor where ativo = " + 1 + " ;");
        List<Fornecedor> fornecedors=new ArrayList<>();
        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor=new Fornecedor();
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setAtivo( rs.getBoolean("ativo"));
            fornecedor.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            fornecedor.setDataCadastro(rs.getDate("data_cadastro"));
            getPessoaByID(rs.getInt("pessoa_id"), fornecedor);

            fornecedors.add(fornecedor);
        }
        return fornecedors;
    }

    public Fornecedor getByID(Integer id) throws Exception {
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM fornecedor WHERE ID = "+id+";");
        ResultSet rs = preparedStatement.executeQuery();
        Fornecedor fornecedor=new Fornecedor();
        while (rs.next()) {
            fornecedor=new Fornecedor();
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setAtivo( rs.getBoolean("ativo"));
            fornecedor.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            fornecedor.setDataCadastro(rs.getDate("data_cadastro"));
            getPessoaByID(rs.getInt("id"), fornecedor);
        }
        return fornecedor;
    }

    public Fornecedor getPessoaByID(Integer id, Fornecedor fornecedor)throws Exception{
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM pessoa WHERE id = '"+ id +"' ;");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setNomeFantasia_Apelido(rs.getString("nome_fantasia_apelido"));
            fornecedor.setBairro(rs.getString("bairro"));
            fornecedor.setLogradouro(rs.getString("logradouro"));
            fornecedor.setComplemento(rs.getString("complemento"));
            fornecedor.setCidade(new CidadeService().getCidadeByID(rs.getInt("cidade_ID")));
            fornecedor.setNumeroResidencial(rs.getString("numero_residencial"));
            fornecedor.setCep(rs.getString("cep"));
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setTelefoneAlternativo(rs.getString("telefone_alternativo"));
            fornecedor.setDataNascimento(rs.getDate("data_nascimento"));
            fornecedor.setRgIe(rs.getString("rg_ie"));
            fornecedor.setCpfCnpj(rs.getString("cpf_cnpj"));
            fornecedor.setEmail(rs.getString("email"));


            Integer sexo = rs.getInt("sexo");
            switch (sexo) {
                case 0 : fornecedor.setSexo(Sexo.MASCULINO);
                case 1 : fornecedor.setSexo(Sexo.FEMININO);
                case 2 : fornecedor.setSexo(Sexo.OUTROS);
            }

            Integer tipo = rs.getInt("tipo");
            switch (tipo) {
                case 0 : fornecedor.setTipo(TipoPessoa.FISICA);
                case 1 : fornecedor.setTipo(TipoPessoa.JURIDICA);
                case 2 : fornecedor.setTipo(TipoPessoa.ESTRANGEIRO);
            }

        }
        return fornecedor;
    }

    public Fornecedor getByCpfCnpjExato(String cpf) throws Exception {
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM fornecedor WHERE id = "+
                "(SELECT id FROM pessoa WHERE cpf_cnpj = '"+cpf+"' );");
        ResultSet rs = preparedStatement.executeQuery();
        Fornecedor fornecedor = null ;
        if (rs.next()) {
            fornecedor=new Fornecedor();
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setAtivo( rs.getBoolean("ativo"));
            fornecedor.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            fornecedor.setDataCadastro(rs.getDate("data_cadastro"));
            getPessoaByID(fornecedor.getId(), fornecedor);
        }
        return fornecedor;
    }
}
