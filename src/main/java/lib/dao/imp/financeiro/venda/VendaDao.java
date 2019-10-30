package lib.dao.imp.financeiro.venda;

import lib.dao.AbstractDao;
import lib.model.comercial.Venda;
import lib.model.financeiro.contas.ContaPagar;
import lib.model.comercial.ItemProduto;
import lib.model.comercial.frete.TipoFrete;
import lib.model.financeiro.contas.ContaReceber;
import lib.model.produto.Produto;
import lib.service.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDao extends AbstractDao {

    public void saveItens(List<ItemProduto> itensProduto) throws SQLException {
        for (ItemProduto itemProduto : itensProduto) {
            String sql = "INSERT INTO item_produto (quantidade, valor_unitario, desconto_unitario, acrescimo_unitario, valor_rateio, valor_total, produto_id, " +
                    "serie_venda, " +
                    "numero_venda," +
                    "modelo_venda)" +
                    " VALUES ("
                    + itemProduto.getQuantidade() +
                    ", " + itemProduto.getValorUnitario() +
                    ", " + itemProduto.getDescontoUnitario() +
                    ", " + itemProduto.getAcrescimoUnitario() +
                    ", " + itemProduto.getValorRateio() +
                    ", " + itemProduto.getValorTotal() +
                    ", " + itemProduto.getId() +
                    ", " + itemProduto.getVenda().getNumSerieNota() +
                    ", " + itemProduto.getVenda().getNumeroNota() +
                    ", '" + itemProduto.getVenda().getModeloNota()
                    + "' );";
            this.st.execute(sql);
        }
    }

    public void save(Venda venda) throws SQLException {
        String sql = "INSERT INTO venda (" +
                " numero," +
                " modelo," +
                " serie, " +
                " data_chegada," +
                " data_emissao," +
                " fornecedor_id," +
                //   " funcionario_id," +
                " valor_frete, " +
                " valor_seguro," +
                " outras_despesas, " +
                " tipo_frete, " +
                " ativo, " +
                " condicao_pagamento_id" +
                ") values ("
                + venda.getNumeroNota() + ", '"
                + venda.getModeloNota() + "', "
                + venda.getNumSerieNota() + ", '"
                + venda.getDtChegada() + "', '"
                + venda.getDtEmisssao() + "', "
                + venda.getCliente().getId() + ", "
                //  + venda.getFuncionario().getId() + ", "
                + venda.getValorFrete() + ", "
                + venda.getValorSeguro() + ", "
                + venda.getOutrasDespesas() + ", "
                + venda.getTipoFrete().ordinal() + ", "
                + venda.isAtivo() + ", " +
                +venda.getCondicaoPagamento().getId()
                + " ) ; ";

        this.st.getConnection().setAutoCommit(false);
        this.st.executeUpdate(sql);
        this.saveContas(venda.getContas());
        this.saveItens(venda.getItensProdutos());

        this.st.getConnection().commit();
        this.st.getConnection().setAutoCommit(true);

    }

    public void cancelar(Venda venda) throws SQLException {
        String sql = "update venda set ativo = false, motivo_cancelamento = '" + venda.getMotivoCancelamento() + "' where modelo = '" + venda.getModeloNota() + "' and numero = " + venda.getNumeroNota() + " and serie =" + venda.getNumSerieNota() + " ;";
        this.st.execute(sql);
    }

    public void commit() throws SQLException {
        this.st.getConnection().commit();
        this.st.clearBatch();
        this.st.clearWarnings();
    }

    public void saveContas(List<ContaReceber> contas) throws SQLException {
        for (ContaReceber conta : contas) {
            String sql = "INSERT INTO conta_pagar (modelo_venda , serie_venda, numero_venda, valor, data_Lancamento, data_Vencimento, forma_pagamento_id) "
                    + "values ('" +
                    conta.getVenda().getModeloNota() + "', " +
                    conta.getVenda().getNumSerieNota() + ", " +
                    conta.getVenda().getNumeroNota() + ", " +
                    conta.getValor() + ", " +
                    "'" + conta.getDataLancamento() + "', " +
                    "' " + conta.getDataVencimento() + "', " +
                    " " + conta.getFormaPagamento().getId() + " " +
                    //  conta.getStatusConta().ordinal() + ", " +
                    ");";
            this.st.execute(sql);
        }
        //return "Salvo com sucesso";
    }

    public List<ContaReceber> getAllContasByVenda(Venda venda) throws Exception {
        String sql = "SELECT * FROM conta_venda WHERE modelo_venda = '" + venda.getModeloNota() + "' and numero_venda = " + venda.getNumeroNota() + " and serie_venda =" + venda.getNumSerieNota() + ";";
        PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        List<ContaReceber> contas = new ArrayList<>();
        while (rs.next()) {
            ContaReceber contaPagar = new ContaReceber();
            // contaPagar.setParcela(new ParcelaDAO().getByID(rs.getInt("parcela_id")));
            contaPagar.setDataVencimento(rs.getDate("data_vencimento"));
            contaPagar.setDataLancamento(rs.getDate("data_lancamento"));
            contaPagar.setVenda(venda);
            // contaPagar.setFormaPagamento((FormaPagamento) new FormaPagamentoDAO().getByID(rs.getInt("forma_pagamento_id")));
            contaPagar.setValor(rs.getDouble("valor"));
            //contaPagar.setValorRecebido(rs.getDouble("valor_recebido"));
            contaPagar.setPaga(rs.getBoolean("paga"));
            contaPagar.setFormaPagamento(new FormaPagamentoService().getByID(rs.getInt("forma_pagamento_id")));
            contas.add(contaPagar);
        }
        return contas;
    }

    public List<ItemProduto> getAllItensByVenda(Venda venda) throws Exception {
        String sql = "SELECT * FROM item_produto WHERE modelo_venda = '" + venda.getModeloNota() + "' and numero_venda = " + venda.getNumeroNota() + " and serie_venda =" + venda.getNumSerieNota() + ";";
        PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        List<ItemProduto> itens = new ArrayList<>();
        while (rs.next()) {
            ItemProduto itemProduto = new ItemProduto();
            itemProduto.setId(rs.getInt("id"));
            itemProduto.setValorRateio(rs.getDouble("valor_rateio"));
            itemProduto.setValorTotal(rs.getDouble("valor_total"));
            itemProduto.setValorUnitario(rs.getDouble("valor_unitario"));
            itemProduto.setAcrescimoUnitario(rs.getDouble("acrescimo_unitario"));
            itemProduto.setDescontoUnitario(rs.getDouble("desconto_unitario"));
            itemProduto.setQuantidade(rs.getDouble("quantidade"));
            itemProduto.setVenda(venda);
            itemProduto.buildItem((Produto) new ProdutoService().getByID(rs.getInt("produto_id")));
            itens.add(itemProduto);
        }
        return itens;
    }

    public List getAll(String termoBusca) throws Exception {
        String sql = "";
        if (termoBusca.length() == 0)
            sql = "SELECT * FROM venda ;";
        else if ((!termoBusca.matches("[0-9]")))
            sql = "Select * from venda where id = " + termoBusca + ";";
        else
            sql = "SELECT * FROM venda WHERE nome = " + termoBusca + ";";

        ResultSet rs = this.st.executeQuery(sql);
        List vendas = new ArrayList();
        while (rs.next()) {
            Venda venda = new Venda();
            venda.setNumeroNota(rs.getInt("numero"));
            venda.setModeloNota(rs.getString("modelo"));
            venda.setNumSerieNota(rs.getInt("serie"));
            venda.setAtivo(rs.getBoolean("ativo"));
            venda.setOutrasDespesas(rs.getDouble("outras_despesas"));
            venda.setValorSeguro(rs.getDouble("valor_seguro"));
            venda.setValorFrete(rs.getDouble("valor_frete"));
            venda.setCondicaoPagamento(new CondicaoPagamentoService().getByID(rs.getInt("condicao_pagamento_id")));
            venda.setTipoFrete(TipoFrete.getByOrdinal(rs.getInt("tipo_frete")));
            venda.setDtChegada(rs.getDate("data_chegada"));
            venda.setDtEmisssao(rs.getDate("data_emissao"));
            venda.setCliente(new ClienteService().getByID(rs.getInt("cliente_id")));
            // venda.setFuncionario(new FuncionarioService().getByID(rs.getInt("funcionario_id")));
            venda.setContas(this.getAllContasByVenda(venda));
            venda.setItensProdutos(this.getAllItensByVenda(venda));
            //  venda.setFuncionario(new );
            vendas.add(venda);
        }
        return vendas;
    }

    public List getAllAtivos() throws Exception {
        String sql = "Select * from vendas where ativo = " + 1 + " ;";
        ResultSet rs = this.st.executeQuery(sql);
        List vendas = new ArrayList();
        while (rs.next()) {
            Venda venda = new Venda();
            venda.setNumeroNota(rs.getInt("numeroa"));
            venda.setAtivo(rs.getBoolean("ativo"));
            venda.setModeloNota(rs.getString("modelo"));
            venda.setNumSerieNota(rs.getInt("serie"));
            venda.setDtEmisssao(rs.getDate("data_chegada"));
            venda.setDtEmisssao(rs.getDate("data_emissao"));
            venda.setCliente(new ClienteService().getByID(rs.getInt("cliente_id")));

            //  venda.setFuncionario(new );
            vendas.add(venda);
        }
        return vendas;
    }

    public Object getByID(String modelo, Integer numero, Integer serie, Integer clienteId) throws Exception {
        String sql = "Select * from venda where numero = " + numero + " and serie = " + serie + " and modelo = '" + modelo + "' and " +
                "cliente_id = "+clienteId+" ;";
        PreparedStatement preparedStatement = st.getConnection().prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        Venda venda = null;
        while (rs.next()) {
            venda = new Venda();
            venda.setNumeroNota(rs.getInt("numero"));
            venda.setModeloNota(rs.getString("modelo"));
            venda.setNumSerieNota(rs.getInt("serie"));
            venda.setDtChegada(rs.getDate("data_chegada"));
            venda.setDtEmisssao(rs.getDate("data_emissao"));
            venda.setOutrasDespesas(rs.getDouble("outras_despesas"));
            venda.setValorSeguro(rs.getDouble("valor_seguro"));
            venda.setValorFrete(rs.getDouble("valor_frete"));
          //  venda.setCliente(new ClienteService().getByID(rs.getInt("funcionario_id")));
            venda.setContas(getAllContasByVenda(venda));
            venda.setCliente(new ClienteService().getByID(rs.getInt("cliente_id")));
             venda.setItensProdutos(getAllItensByVenda(venda));
        }
        return venda;
    }

}
