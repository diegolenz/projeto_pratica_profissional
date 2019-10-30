package lib.dao.imp.pessoa;

import lib.dao.AbstractDao;
import lib.model.pessoa.Pessoa;
import lib.model.pessoa.Sexo;
import lib.model.pessoa.TipoPessoa;
import lib.model.pessoa.cliente.Cliente;
import lib.service.CidadeService;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDAO extends AbstractDao {


    private Cliente cliente;

    public ClienteDAO() {
        this.cliente = new Cliente();
    }

    public Integer getUltimoIDPessoa()throws Exception {
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("Select id from pessoa order by ID desc limit 1");
        ResultSet rs = preparedStatement.executeQuery();
       // ResultSet resultSet = this.st.executeQuery("Select id from pessoa order by ID desc limit 1");
        Integer id=null;
        while (rs.next())
            id = rs.getInt("id");
        return id;
    }

    public String buildDate(Date date){
        return date != null ? date.toString() : "" ;
    }

    public void save(Object obj) throws Exception {
        Cliente cliente = (Cliente) obj;
        String sql = "";
        sql = "INSERT INTO pessoa (" +
                " nome," +
                " cpf_cnpj," ;
        if (cliente.getDataNascimento()!= null){
            sql+="data_nascimento, ";
        }
        sql += " email," +
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
                "'" + cliente.getNome() +
                "','" + cliente.getCpfCnpj();
        if (cliente.getDataNascimento() != null) {
            sql+=  "', '" + cliente.getDataNascimento() ;
        }
        sql +=   "', '" + cliente.getEmail() +
                "', " + cliente.getSexo().ordinal() +
                ", '" + cliente.getNomeFantasia_Apelido() +
                "', '" + cliente.getRgIe() +
                "', '" + cliente.getTelefone() +
                "', '" + cliente.getTelefoneAlternativo() +
                "', " + cliente.getTipo().ordinal() +
                ", " +  cliente.getCidade().getId() +
                ", '" +  cliente.getBairro() +
                "', '" + cliente.getLogradouro() +
                "', '" + cliente.getComplemento() +
                "', '" + cliente.getNumeroResidencial() +
                "', '" + cliente.getCep() +
                "');";

        this.st.executeUpdate(sql);
        //Insere fornecedor referenciando a ultima pessoa inserida no banco
        Integer idCliente = getUltimoIDPessoa();
        String sqlCliente =
                "INSERT INTO cliente (id, ativo, data_cadastro, data_ultima_alteracao, pessoa_id) values ("+
                        idCliente + ", " +
                        cliente.getAtivo() + ", '" +
                        cliente.getDataCadastro() + "','" +
                        cliente.getDataUltAlteracao() +"'," +
                        getUltimoIDPessoa() +
                        " );";
        this.st.executeUpdate(sqlCliente);
    }

    public void deleteByID(Object id) throws Exception {
        String sql = "DELETE FROM cliente WHERE id = " + id + " ;";
        this.st.executeUpdate(sql);
    }

    public List getAllClientes(String termoBusca) throws Exception {
        String sql ="";
        if (termoBusca.length() ==0)
            sql = "SELECT * FROM cliente where ativo = true ;" ;
        else if ((termoBusca.matches("[0-9]")))
            sql = "SELECT * FROM cliente where  id = "+termoBusca+" and ativo = true ;" ;
        else
            sql =   "select * from cliente where pessoa_id = (select id from pessoa where nome like '%"+termoBusca+"%' );";

        ResultSet rs = this.st.executeQuery(sql);
        List<Cliente> clientes = new ArrayList<>();

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setAtivo( rs.getBoolean("ativo"));
            cliente.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            cliente.setDataCadastro(rs.getDate("data_cadastro"));
            getPessoaByID(cliente.getId(), cliente);
            cliente.setId(rs.getInt("id"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public void update(Object obj) throws SQLException {
        cliente = (Cliente) obj;
        String sql = "UPDATE cliente SET nome = '" + cliente.getNome() +
                "', sexo = " + cliente.getSexo().ordinal() +
                ",  ativo=" + cliente.getAtivo() +
                ",  nome_fantasia_apelido = '" + cliente.getNomeFantasia_Apelido() +
                "', rg_ie = '" + cliente.getRgIe() +
                "', telefone = '" + cliente.getTelefone()  +
                "', telefone_alternativo = '" + cliente.getTelefoneAlternativo() +
                "', tipo = " + cliente.getTipo().ordinal() +
                ",  data_cadastro = '" + cliente.getDataCadastro() +
                "', data_ultima_alteracao = '"+ cliente.getDataUltAlteracao() +
                "', data_nascimento = '" + cliente.getDataNascimento() +
                "', email = '" + cliente.getEmail() +
                "', logradouro = '" + cliente.getLogradouro() +
                "', complemento = '" + cliente.getComplemento() +
                "', cep ='"+ cliente.getCep() +
                "', cidade_ID =" + cliente.getCidade().getId() +
                ", numero_residencial = " + cliente.getNumeroResidencial() +
                " WHERE id = " + cliente.getId() + " ;";
        this.st.executeUpdate(sql);
    }

    public List<Cliente> getAtivos() throws Exception {

        ResultSet rs = this.st.executeQuery("SELECT * FROM cliente where ativo = " + 1 + " ;");
        List<Cliente> clientes=new ArrayList<>();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setNomeFantasia_Apelido(rs.getString("nome_fantasia_apelido"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setLogradouro(rs.getString("logradouro"));
            cliente.setComplemento(rs.getString("complemento"));
            cliente.setCidade(new CidadeService().getCidadeByID(rs.getInt("cidade_ID")));
            cliente.setNumeroResidencial(rs.getString("numero_residencial"));
            cliente.setCep(rs.getString("cep"));
            cliente.setAtivo( rs.getBoolean("ativo"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setTelefoneAlternativo(rs.getString("telefone_alternativo"));
            cliente.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            cliente.setDataCadastro(rs.getDate("data_cadastro"));
            cliente.setDataNascimento(rs.getDate("data_nascimento"));
            cliente.setRgIe(rs.getString("rg_ie"));
            cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
            cliente.setEmail(rs.getString("email"));


            Integer sexo = rs.getInt("sexo");
            switch (sexo) {
                case 0 : cliente.setSexo(Sexo.MASCULINO);
                case 1 : cliente.setSexo(Sexo.FEMININO);
                case 2 : cliente.setSexo(Sexo.OUTROS);
            }

            Integer tipo = rs.getInt("tipo");
            switch (tipo) {
                case 0 : cliente.setTipo(TipoPessoa.FISICA);
                case 1 : cliente.setTipo(TipoPessoa.JURIDICA);
                case 2 : cliente.setTipo(TipoPessoa.ESTRANGEIRO);
            }

            clientes.add(cliente);
        }
        return clientes;
    }

    public Cliente getByID(Integer id) throws SQLException {
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM cliente WHERE ID = "+id+";");
        ResultSet rs = preparedStatement.executeQuery();
        Cliente cliente=new Cliente();
        while (rs.next()) {
            cliente = new Cliente();
            cliente.setAtivo( rs.getBoolean("ativo"));
            cliente.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            cliente.setDataCadastro(rs.getDate("data_cadastro"));
            getPessoaByID(cliente.getId(), cliente);
            cliente.setId(rs.getInt("id"));
        }
        return cliente;
    }

    public Cliente getPessoaByID(Integer id, Cliente cliente)throws SQLException{
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM pessoa WHERE id = '"+ id +"' ;");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setNomeFantasia_Apelido(rs.getString("nome_fantasia_apelido"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setLogradouro(rs.getString("logradouro"));
            cliente.setComplemento(rs.getString("complemento"));
            cliente.setCidade(new CidadeService().getCidadeByID(rs.getInt("cidade_ID")));
            cliente.setNumeroResidencial(rs.getString("numero_residencial"));
            cliente.setCep(rs.getString("cep"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setTelefoneAlternativo(rs.getString("telefone_alternativo"));
            cliente.setDataNascimento(rs.getDate("data_nascimento"));
            cliente.setRgIe(rs.getString("rg_ie"));
            cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
            cliente.setEmail(rs.getString("email"));


            Integer sexo = rs.getInt("sexo");
            switch (sexo) {
                case 0 : cliente.setSexo(Sexo.MASCULINO);
                case 1 : cliente.setSexo(Sexo.FEMININO);
                case 2 : cliente.setSexo(Sexo.OUTROS);
            }

            Integer tipo = rs.getInt("tipo");
            switch (tipo) {
                case 0 : cliente.setTipo(TipoPessoa.FISICA);
                case 1 : cliente.setTipo(TipoPessoa.JURIDICA);
                case 2 : cliente.setTipo(TipoPessoa.ESTRANGEIRO);
            }

        }
        return cliente;
    }

    public Cliente getByCpfCnpjExato(String cpf) throws Exception {
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT FROM cliente WHERE ID = (SELECT id FROM pessoa WHERE cpf_cnpj = '"+cpf+"' );");
        ResultSet rs = preparedStatement.executeQuery();
        Integer id = null;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        if (id == null)
            return null;
        return getByID(id);
    }

}
