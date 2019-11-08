package lib.dao.imp.pessoa;

import lib.dao.AbstractDao;
import lib.model.financeiro.CondicaoPagamento.CondicaoPagamento;
import lib.model.pessoa.Sexo;
import lib.model.pessoa.TipoPessoa;
import lib.model.pessoa.cliente.Cliente;
import lib.service.CidadeService;
import lib.service.CondicaoPagamentoService;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends AbstractDao {


    private Cliente cliente;

    public ClienteDAO() {
        this.cliente = new Cliente();
    }

    public Integer getUltimoIdCliente() throws SQLException {
        PreparedStatement preparedStatement = st.getConnection().prepareStatement("Select id from cliente order by ID desc limit 1");
        ResultSet rs = preparedStatement.executeQuery();
        Integer id = null;
        while (rs.next())
            id = rs.getInt("id");
        return id;
    }

    public void save(Object obj) throws SQLException {
        Cliente cliente = (Cliente) obj;
        String sql = "";
        sql = "INSERT INTO cliente (" +
                " nome," +
                " cpf_cnpj,";
        if (cliente.getDataNascimento() != null) {
            sql += "data_nascimento, ";
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
                " cep," +
                " ativo, " +
                " data_cadastro," +
                " data_ultima_alteracao" +
                ") values (" +
                "'" + cliente.getNome() +
                "','" + cliente.getCpfCnpj();
        if (cliente.getDataNascimento() != null) {
            sql += "', '" + cliente.getDataNascimento();
        }
        sql += "', '" + cliente.getEmail() +
                "', " + cliente.getSexo().ordinal() +
                ", '" + cliente.getNomeFantasia_Apelido() +
                "', '" + cliente.getRgIe() +
                "', '" + cliente.getTelefone() +
                "', '" + cliente.getTelefoneAlternativo() +
                "', " + cliente.getTipo().ordinal() +
                ", " + cliente.getCidade().getId() +
                ", '" + cliente.getBairro() +
                "', '" + cliente.getLogradouro() +
                "', '" + cliente.getComplemento() +
                "', '" + cliente.getNumeroResidencial() +
                "', '" + cliente.getCep() +
                "', " + cliente.getAtivo() +
                ", '" + cliente.getDataCadastro() +
                "', '" + cliente.getDataUltAlteracao() +
                "');";

        this.st.executeUpdate(sql);
        this.saveCondicoesPagamento(cliente.getCondicaoPagamentos(), getUltimoIdCliente());
    }

    public void saveCondicoesPagamento(List<CondicaoPagamento> condicoes, Integer id) throws SQLException {
        for (CondicaoPagamento condicaoPagamento : condicoes) {
            String sql = "INSERT INTO condicao_pagamento_cliente ( cliente_id, condicao_id) values (" +
                    "" + id + ", " + condicaoPagamento.getId() + " );";
            st.execute(sql);
        }
    }

    public void deleteCondicoes(List<CondicaoPagamento> condicoes, Integer fornecedorId) throws SQLException {
        String sql = "";
        for (CondicaoPagamento condicaoPagamento : condicoes) {
            sql = "DELETE FROM condicao_pagamento_fornecedor WHERE condicao_id = " + condicaoPagamento.getId() + " and fornecedor_id = " + fornecedorId + " ;";
            this.st.execute(sql);
        }
    }

    public void deleteByID(Object id) throws SQLException {
        this.st.getConnection().setAutoCommit(false);
        String sqlDeletaCondicoes = "delete from condicao_pagamento_cliente where cliente_id = " + id + " ;";
        String sql = "DELETE FROM cliente WHERE id = " + id + " ;";
        this.st.executeUpdate(sqlDeletaCondicoes);
        this.st.executeUpdate(sql);
        this.st.getConnection().commit();
        this.st.getConnection().setAutoCommit(true);
    }

    public List getAllClientes(String termoBusca) throws SQLException {
        String sql = "";
        if (termoBusca.length() == 0)
            sql = "SELECT * FROM cliente where ativo = true ;";
        else if ((termoBusca.matches("[0-9]")))
            sql = "SELECT * FROM cliente where  id = " + termoBusca + " and ativo = true ;";
        else
            sql = "select * from cliente where nome like '%" + termoBusca + "%' );";

        ResultSet rs = this.st.executeQuery(sql);
        List<Cliente> clientes = new ArrayList<>();

        while (rs.next()) {
            clientes.add(getByID(rs.getInt("id")));
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
                "', telefone = '" + cliente.getTelefone() +
                "', telefone_alternativo = '" + cliente.getTelefoneAlternativo() +
                "', tipo = " + cliente.getTipo().ordinal() +
                ",  data_cadastro = '" + cliente.getDataCadastro() +
                "', data_ultima_alteracao = '" + cliente.getDataUltAlteracao();
        if (cliente.getDataNascimento() != null)
            sql += "', data_nascimento = '" + cliente.getDataNascimento();
        sql += "', email = '" + cliente.getEmail() +
                "', logradouro = '" + cliente.getLogradouro() +
                "', complemento = '" + cliente.getComplemento() +
                "', cep ='" + cliente.getCep() +
                "', cidade_ID =" + cliente.getCidade().getId() +
                ", numero_residencial = '" + cliente.getNumeroResidencial() +
                "' WHERE id = " + cliente.getId() + " ;";
        this.st.executeUpdate(sql);
    }

    public Cliente getByID(Integer id) throws SQLException {
        PreparedStatement preparedStatement = st.getConnection().prepareStatement("SELECT * FROM cliente WHERE ID = " + id + ";");
        ResultSet rs = preparedStatement.executeQuery();
        Cliente cliente = null;
        while (rs.next()) {
            cliente = new Cliente();
            cliente.setAtivo(rs.getBoolean("ativo"));
            cliente.setDataUltAlteracao(rs.getDate("data_ultima_alteracao"));
            cliente.setDataCadastro(rs.getDate("data_cadastro"));
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
                case 0:
                    cliente.setSexo(Sexo.MASCULINO);
                case 1:
                    cliente.setSexo(Sexo.FEMININO);
                case 2:
                    cliente.setSexo(Sexo.OUTROS);
            }

            Integer tipo = rs.getInt("tipo");
            switch (tipo) {
                case 0:
                    cliente.setTipo(TipoPessoa.FISICA);
                case 1:
                    cliente.setTipo(TipoPessoa.JURIDICA);
                case 2:
                    cliente.setTipo(TipoPessoa.ESTRANGEIRO);
            }
            cliente.setCondicaoPagamentos(getCondicaoByFornecedor(cliente.getId()));
        }
        return cliente;
    }

    private List<CondicaoPagamento> getCondicaoByFornecedor(Integer id) throws SQLException {
        PreparedStatement preparedStatement = st.getConnection().prepareStatement("SELECT * FROM condicao_pagamento_cliente WHERE cliente_id = " + id + ";");
        ResultSet rs = preparedStatement.executeQuery();
        List<CondicaoPagamento> condicaoPagamentos = new ArrayList<>();
        while (rs.next()) {
            condicaoPagamentos.add(new CondicaoPagamentoService().getByID(rs.getInt("condicao_id")));
        }
        return condicaoPagamentos;
    }

    public Cliente getByCpfCnpjExato(String cpf) throws SQLException {
        PreparedStatement preparedStatement = st.getConnection().prepareStatement("SELECT id FROM cliente WHERE cpf_cnpj = '" + cpf + "' ;");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return getByID(rs.getInt("id"));
        }
        return null;
    }

}
