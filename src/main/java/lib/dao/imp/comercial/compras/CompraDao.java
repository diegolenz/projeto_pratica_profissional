package lib.dao.imp.comercial.compras;

import lib.dao.AbstractDao;
import lib.dao.imp.financeiro.ParcelaDAO;
import lib.dao.imp.financeiro.formaPagamentoDAO.FormaPagamentoDAO;
import lib.model.comercial.Compra;
import lib.model.comercial.ContaPagar;
import lib.model.comercial.ItemProduto;
import lib.model.financeiro.formaPagamento.FormaPagamento;
import lib.model.financeiro.parcela.Parcela;
import lib.model.interno.Funcionario;
import lib.service.FornecedorService;
import lib.service.FuncionarioService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraDao extends AbstractDao {

    public void saveItens(List<ItemProduto> itensProduto){
        for (ItemProduto itemProduto : itensProduto) {
            String sql = "INSERT INTO item_produto (quantidade, desconto_unitario, acrescimo_unitario, valor_total, produto_id, " +
                    "serie_compra, " +
                    "numero_compra," +
                    "modelo_compra)" +
                    " VALUES (" +
                    ", " + itemProduto.getQuantidade() +
                    ", " + itemProduto.getDescontoUnitario() +
                    ", " + itemProduto.getAcrescimoUnitario() +
                    ", " + itemProduto.getValorTotal() +
                    ", " + itemProduto.getId() +
                    ", " + itemProduto.getCompra().getNumSerieNota() +
                    ", " + itemProduto.getCompra().getNumeroNota() +
                    ", " + itemProduto.getCompra().getModeloNota()
                    + " );";
        }
    }


    public void save(Compra compra) throws Exception {
        String sql = "INSERT INTO compra (" +
                " numero," +
                " modelo," +
                " serie, " +
                " data_chegada," +
                " data_emissao," +
                " fornecedor_id," +
             //   " funcionario_id," +
                " condicao_pagamento_id" +
                ") values ("
                + compra.getNumeroNota() + ", '"
                + compra.getModeloNota() + "', "
                + compra.getNumSerieNota() +", '"
                + compra.getDtChegada() + "', '"
                + compra.getDtEmisssao() + "', "
                + compra.getFornecedor().getId() + ", "
              //  + compra.getFuncionario().getId() + ", "
                + compra.getCondicaoPagamento().getId()
        + " ) ; ";
        this.st.executeUpdate(sql);
        this.saveContas(compra.getContas());
        this.saveItens(compra.getItensProdutos());
    }

    public void saveContas(List<ContaPagar> contas) throws  Exception{
        for (ContaPagar conta : contas) {
            String sql = "INSERT INTO contas (id, modelo_compra, serie_compra, numero_compra , valor, dataLancamento, dataVencimento, formaPagamento_id) "
                    + "values (" +
                    conta.getId() + ", " +
                    conta.getCompra().getModeloNota() + ", " +
                    conta.getCompra().getNumSerieNota() + ", " +
                    conta.getCompra().getModeloNota() + ", " +
                    conta.getValor() + ", " +
                    "'" + conta.getDataLancamento() + "', " +
                    "' "+conta.getDataVencimento() + "' " +
                    conta.getStatusConta().ordinal() + ", " +
                    ");";
            this.st.execute(sql);
        }
    }

 /*   public void update(Compra compra) throws  Exception{
        String sql = "UPDATE compra SET " +
                "  numero_serie = '"+ compra.getNumSerieNota() +
                ", numero = " + compra.getNumeroNota() +
                ", modelo = '" + compra.getModeloNota() + "' " +
                ", ativo = "+ compra.isAtivo() +
                ", fornecedor_id = " + compra.getFornecedor().getId() +
                ", funcionario_id = " + compra.getFuncionario().getId() +
                ", data_chegada = " + compra.getDtChegada() +
                "' data_emissao = " + compra.getDtEmisssao() +
                " where numero = "+ compra.getNumeroNota() +
                " AND numero_serie =" + compra.getNumSerieNota() +
                " AND modelo = '" + compra.getModeloNota() + "' ;" ;
        this.st.executeUpdate(sql);
    } */

    public List<ContaPagar> getAllContasByCompra(String modelo, Integer numero, Integer serie)throws Exception{
        String sql = "SELECT * FROM conta WHERE modelo_compra == " + modelo + " numero_compra = " + numero + " serie_compra =" + serie +";";
        ResultSet rs = this.st.executeQuery(sql);
        List<ContaPagar> contas = new ArrayList<>();
        while (rs.next()){
            ContaPagar contaPagar = new ContaPagar();
            contaPagar.setParcela(new ParcelaDAO().getByID(rs.getInt("parcela_id")));
            contaPagar.setDataVencimento(rs.getDate("data_vencimento"));
            contaPagar.setDataLancamento(rs.getDate("data_lancamento"));
            contaPagar.setCompra((Compra)this.getByID(rs.getInt("compra_id")));
            contaPagar.setFormaPagamento((FormaPagamento) new FormaPagamentoDAO().getByID(rs.getInt("forma_pagamento_id")));
            contaPagar.setValor(rs.getDouble("valor"));
            contaPagar.setValorRecebido(rs.getDouble("valor_recebido"));
            contaPagar.setPaga(rs.getBoolean("paga"));
            contas.add(contaPagar);
        }
        return contas;
    }

    public List getAll(String termoBusca)throws Exception {
        String sql = "";
        if (termoBusca.length() == 0)
            sql="SELECT * FROM compra ;";
        else if ((!termoBusca.matches("[0-9]")))
            sql = "Select * from compra where id = "+ termoBusca +";";
        else
            sql = "SELECT * FROM compra WHERE nome = "+ termoBusca +";";

        ResultSet rs = this.st.executeQuery(sql);
        List compras = new ArrayList();
        while (rs.next()){
            Compra compra=new Compra();
            compra.setNumeroNota(rs.getInt("numero"));
            compra.setAtivo(rs.getBoolean("ativo"));
            compra.setModeloNota(rs.getString("modelo"));
            compra.setNumSerieNota(rs.getInt("serie"));
            compra.setDtEmisssao(rs.getDate("data_chegada"));
            compra.setDtEmisssao(rs.getDate("data_emissao"));
            compra.setFornecedor(new FornecedorService().getByID(rs.getInt("fornecedor_id")));
           // compra.setFuncionario(new FuncionarioService().getByID(rs.getInt("funcionario_id")));
            compra.setConstasPagar(this.getAllContasByCompra(compra.getModeloNota(), compra.getNumeroNota(),compra.getNumSerieNota()));
          //  compra.setFuncionario(new );
            compras.add(compra);
        }
        return compras;
    }

    public List getAllAtivos() throws Exception{
        String sql = "Select * from compras where ativo = "+1+" ;";
        ResultSet rs = this.st.executeQuery(sql);
        List compras = new ArrayList();
        while (rs.next()){
            Compra compra=new Compra();
            compra.setNumeroNota(rs.getInt("numeroa"));
            compra.setAtivo(rs.getBoolean("ativo"));
            compra.setModeloNota(rs.getString("modelo"));
            compra.setNumSerieNota(rs.getInt("serie"));
            compra.setDtEmisssao(rs.getDate("data_chegada"));
            compra.setDtEmisssao(rs.getDate("data_emissao"));
            compra.setFornecedor(new FornecedorService().getByID(rs.getInt("fornecedor_id")));
            //  compra.setFuncionario(new );
            compras.add(compra);
        }
        return compras;
    }

    public Object getByID(Integer id) throws Exception{
        String sql = "Select * from marca where id ="+id+" ;";
        PreparedStatement preparedStatement=st.getConnection().prepareStatement(sql);
        ResultSet rs =  preparedStatement.executeQuery();
        Compra compra=new Compra();
        while (rs.next()){
            compra.setNumeroNota(rs.getInt("numero_nota"));
            compra.setModeloNota(rs.getString("modelo_nota"));
            compra.setNumSerieNota(rs.getInt("serie_nota"));
            compra.setDtChegada(rs.getDate("data_chegada"));
            compra.setDtEmisssao(rs.getDate("data_emissao"));
            //compra.setFuncionario(new  rs.getInt("funcionario_id"));
           // compra.setConstasPagar(rs.getc);
            compra.setFornecedor(new FornecedorService().getByID(rs.getInt("fornecedor_id")));
           // compra.setItensProdutos(new );
          //  compra.setItensServicos();
        }
        return compra;
    }

    public void deleteByID(Integer num, Integer serie, String modelo) throws Exception{
        String sql = "DELETE FROM marca WHERE id = "+num+" ;";
        this.st.executeUpdate(sql);
    }



}
