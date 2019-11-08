package lib.dao.imp.sistema;

import lib.dao.AbstractDao;
import lib.model.interno.Funcionario;
import lib.model.interno.GrupoFuncionario;
import lib.model.pessoa.Sexo;
import lib.model.pessoa.TipoPessoa;
import lib.service.CidadeService;
import lib.service.GrupoFuncionarioService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao extends AbstractDao {

    private Funcionario operador;

    public FuncionarioDao() {
        this.operador = new Funcionario();
    }

    public void save(Object obj) throws SQLException {
        Funcionario funcionario = (Funcionario) obj;
        this.st.getConnection().setAutoCommit(false);

        String date = "";
        if (funcionario.getDataNascimento() !=null)
            date =  funcionario.getDataNascimento().toString() ;
        // Insere informações da pessoa
        String sql = "INSERT INTO pessoa (" +
                " nome," +
                " cpf_cnpj," ;
        if (funcionario.getDataNascimento() != null)
            sql += " data_nascimento," ;
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
                "'" +    funcionario.getNome() +
                "','" +  funcionario.getCpfCnpj() + "',";
        if (funcionario.getDataNascimento() != null)
            sql+= " '" + date + "',";

        sql += " '" + funcionario.getEmail() +
                "', " +  funcionario.getSexo().ordinal() +
                ", '" +  funcionario.getNomeFantasia_Apelido() +
                "', '" + funcionario.getRgIe() +
                "', '" + funcionario.getTelefone() +
                "', '" + funcionario.getTelefoneAlternativo() +
                "', " +  funcionario.getTipo().ordinal() +
                ", " +   funcionario.getCidade().getId() +
                ", '" +  funcionario.getBairro() +
                "', '" + funcionario.getLogradouro() +
                "', '" + funcionario.getComplemento() +
                "', '" +  funcionario.getNumeroResidencial() +
                "', '" +  funcionario.getCep() +
                "' ); ";

        this.st.executeUpdate(sql);
        //Insere funcionario referenciando a ultima pessoa inserida no banco
        String sqlFuncionario =
                "INSERT INTO funcionario (ativo, data_cadastro, data_ultima_alteracao,  data_admissao, ";
        if (funcionario.getDataDemissao() != null)
        sqlFuncionario += "data_demissao ," ;

        sqlFuncionario+= " usuario, senha, pessoa_id) values ("+
                        funcionario.isAtivo() + ", '" +
                        funcionario.getDataCadastro() + "','" +
                        funcionario.getDataUltAlteracao() +"', '" +
                        funcionario.getDataAdmissao() + "', '";
        if (funcionario.getDataDemissao()!= null)
            sqlFuncionario+=funcionario.getDataDemissao() + "', '" ;

        sqlFuncionario+=
                        funcionario.getUsuario() + "',  '" +
                        funcionario.getSenha() + "', " +
                        getUltimoIDPessoa() +
                        " );";
        this.st.executeUpdate(sqlFuncionario);
        this.saveGrupo(funcionario.getGrupos(), getUltimoIDFuncionario());

        this.st.getConnection().commit();
        this.st.getConnection().setAutoCommit(true);
    }

    public Integer getUltimoIDPessoa()throws SQLException {
        ResultSet resultSet = this.st.executeQuery("Select id from pessoa order by ID desc limit 1");
        Integer id=null;
        while (resultSet.next())
            id = resultSet.getInt("id");
        return id;
    }

    public Integer getUltimoIDFuncionario()throws SQLException {
        ResultSet resultSet = this.st.executeQuery("Select id from funcionario order by ID desc limit 1");
        Integer id=null;
        while (resultSet.next())
            id = resultSet.getInt("id");
        return id;
    }

    public void deleteByID(Object id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id = " + id + " ;";
        this.st.executeUpdate(sql);
    }

    public void deleteGrupos(List<GrupoFuncionario> condicoes, Integer fornecedorId) throws SQLException {
        String sql = "";
        for (GrupoFuncionario condicaoPagamento : condicoes) {
            sql = "DELETE FROM grupo_funcionario WHERE grupo_id = " + condicaoPagamento.getId() + " and funcionario_id = " + fornecedorId + " ;";
            this.st.execute(sql);
        }
    }

    public void saveGrupo(List<GrupoFuncionario> condicoes, Integer id) throws SQLException {
        st.getConnection().setAutoCommit(false);
        for (GrupoFuncionario condicaoPagamento : condicoes) {
            String sql = "INSERT INTO relacao_grupo_funcionario ( funcionario_id, grupo_id) values (" +
                    "" + id + ", " + condicaoPagamento.getId() + " );";
            st.execute(sql);
        }
        this.st.getConnection().commit();
        this.st.getConnection().setAutoCommit(true);
    }


    public List getAll(String termos) throws SQLException {

        ResultSet rs = this.st.executeQuery("SELECT * FROM funcionario;");
        List<Funcionario> pessoas = new ArrayList<>();

        while (rs.next()) {
            pessoas.add(getByID(rs.getInt("id")));
        }
        return pessoas;
    }

    public void update(Object obj) throws SQLException {
        Funcionario funcionario = (Funcionario) obj;
        String sql = "UPDATE pessoa SET nome = '" + funcionario.getNome() +
                "', sexo = " + funcionario.getSexo().ordinal() +
                ",  nome_fantasia_apelido = '" + funcionario.getNomeFantasia_Apelido() +
                "', cpf_cnpj = '" + funcionario.getCpfCnpj() +
                "', rg_ie = '" + funcionario.getRgIe() +
                "', telefone = '" + funcionario.getTelefone()  +
                "', telefone_alternativo = '" + funcionario.getTelefoneAlternativo() +
                "', tipo = " + funcionario.getTipo().ordinal() +
                ",";
        if (funcionario.getDataNascimento() != null){
            sql += "  data_nascimento = '" + funcionario.getDataNascimento() + "', ";
        }
        sql +=
                " email = '" + funcionario.getEmail() +
                        "', logradouro = '" + funcionario.getLogradouro() +
                        "', complemento = '" + funcionario.getComplemento() +
                        "', cep ='"+ funcionario.getCep() +
                        "', cidade_ID = " + funcionario.getCidade().getId() +
                        ", numero_residencial = '" + funcionario.getNumeroResidencial() +
                        "'    WHERE id = " + funcionario.getId() + " ;";
        this.st.executeUpdate(sql);
        sql = "UPDATE funcionario SET ativo = "+funcionario.isAtivo() +
                ", usuario='" +funcionario.getUsuario()+
                "', senha='"+ funcionario.getSenha()+
                "', data_admissao ='" +funcionario.getDataAdmissao()+
                "',  data_cadastro = '" + funcionario.getDataCadastro() ;
        if (funcionario.getDataDemissao() != null){
            sql += "', data_demissao = ' " + funcionario.getDataDemissao()  ;
        }
        sql += "', data_ultima_alteracao = '"+ funcionario.getDataUltAlteracao() + "' where id = " + funcionario.getId() +" ;";
        this.st.executeUpdate(sql);
    }

    public List<Funcionario> getAllAtivos(String termo) throws SQLException {

        String sql = "";
        if (termo.length() == 0)
            sql = "SELECT * FROM funcionario where   ativo = true ;";
        else if ((termo.matches("[0-9]")))
            sql = "SELECT * FROM funcionario where  id = " + termo + " and ativo = true ;";
        else
            sql = "SELECT * FROM funcionario where  nome LIKE '%" + termo + "%' and ativo = true ;";

        ResultSet rs = this.st.executeQuery(sql);
        List<Funcionario> pessoas = new ArrayList<>();
        while (rs.next()) {

        }
        return pessoas;
    }

    public Funcionario getByID(Integer id) throws SQLException {
        PreparedStatement preparedStatement = st.getConnection().prepareStatement("SELECT * FROM funcionario WHERE ID = " + id + ";");
        ResultSet rs = preparedStatement.executeQuery();
        Funcionario operador = null;
        while (rs.next()) {
            operador = new Funcionario();

            operador.setAtivo(rs.getBoolean("ativo"));
            operador.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            operador.setDataCadastro(rs.getDate("data_cadastro"));
            operador.setUsuario(rs.getString("usuario"));
            operador.setSenha(rs.getString("senha"));
            operador.setDataDemissao(rs.getDate("data_demissao"));
            operador.setDataAdmissao(rs.getDate("data_admissao"));

            getPessoaByID(rs.getInt("pessoa_id"), operador);
            operador.setId(rs.getInt("id"));
            operador.setGrupos(getGrupos(operador.getId()));
        }
        return operador;
    }

    private List<GrupoFuncionario> getGrupos(Integer id)throws SQLException{
        PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM relacao_grupo_funcionario WHERE funcionario_id = "+id+";");
        ResultSet rs = preparedStatement.executeQuery();
        List<GrupoFuncionario> condicaoPagamentos = new ArrayList<>();
        while (rs.next()){
            condicaoPagamentos.add(new GrupoFuncionarioService().getById(rs.getInt("grupo_id")));
        }
        return condicaoPagamentos;
    }

    public Funcionario getPessoaByID(Integer id, Funcionario funcionario) throws SQLException {
        PreparedStatement preparedStatement = st.getConnection().prepareStatement("SELECT * FROM pessoa WHERE id = '" + id + "' ;");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            funcionario.setId(rs.getInt("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setNomeFantasia_Apelido(rs.getString("nome_fantasia_apelido"));
            funcionario.setBairro(rs.getString("bairro"));
            funcionario.setLogradouro(rs.getString("logradouro"));
            funcionario.setComplemento(rs.getString("complemento"));
            funcionario.setCidade(new CidadeService().getCidadeByID(rs.getInt("cidade_ID")));
            funcionario.setNumeroResidencial(rs.getString("numero_residencial"));
            funcionario.setCep(rs.getString("cep"));
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setTelefoneAlternativo(rs.getString("telefone_alternativo"));
            funcionario.setDataNascimento(rs.getDate("data_nascimento"));
            funcionario.setRgIe(rs.getString("rg_ie"));
            funcionario.setCpfCnpj(rs.getString("cpf_cnpj"));
            funcionario.setEmail(rs.getString("email"));

            Integer sexo = rs.getInt("sexo");
            switch (sexo) {
                case 0:
                    funcionario.setSexo(Sexo.MASCULINO);
                    break;
                case 1:
                    funcionario.setSexo(Sexo.FEMININO);
                    break;
                case 2:
                    funcionario.setSexo(Sexo.OUTROS);
            }

            Integer tipo = rs.getInt("tipo");
            switch (tipo) {
                case 0:
                    funcionario.setTipo(TipoPessoa.FISICA);
                    break;
                case 1:
                    funcionario.setTipo(TipoPessoa.JURIDICA);
                    break;
                case 2:
                    funcionario.setTipo(TipoPessoa.ESTRANGEIRO);
            }

        }
        return funcionario;
    }

    public Funcionario getByCpfCnpjExato(String cpf) throws SQLException {
        PreparedStatement preparedStatement = st.getConnection().prepareStatement("SELECT * FROM funcionario WHERE cpfcnpj = '" + cpf + "' ;");
        ResultSet rs = preparedStatement.executeQuery();
        Funcionario operador = null;
        if (rs.next()) {


        }
        return operador;

    }


}

