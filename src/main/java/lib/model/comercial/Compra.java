package lib.model.comercial;

import lib.model.financeiro.CondicaoPagamento.CondicaoPagamento;
import lib.model.interno.Funcionario;
import lib.model.pessoa.fornecedor.Fornecedor;

import java.util.Date;
import java.util.List;

public class Compra {


    private List<ItemProduto> ItensProdutos ;

    private Integer numeroNota;

    private String modeloNota;

    private Integer numSerieNota;

    private List<ContaPagar> constasPagar;

    private boolean ativo;

    private Fornecedor fornecedor;

    private CondicaoPagamento condicaoPagamento;

    private List<ContaPagar> contas;


    public List<ItemProduto> getItensProdutos() {
        return ItensProdutos;
    }

    public void setItensProdutos(List<ItemProduto> itensProdutos) {
        ItensProdutos = itensProdutos;
    }

    public Integer getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Integer numeroNota) {
        this.numeroNota = numeroNota;
    }

    public String getModeloNota() {
        return modeloNota;
    }

    public void setModeloNota(String modeloNota) {
        this.modeloNota = modeloNota;
    }

    public Integer getNumSerieNota() {
        return numSerieNota;
    }

    public void setNumSerieNota(Integer numSerieNota) {
        this.numSerieNota = numSerieNota;
    }

    public List<ContaPagar> getConstasPagar() {
        return constasPagar;
    }

    public void setConstasPagar(List<ContaPagar> constasPagar) {
        this.constasPagar = constasPagar;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDtEmisssao() {
        return dtEmisssao;
    }

    public void setDtEmisssao(Date dtEmisssao) {
        this.dtEmisssao = dtEmisssao;
    }

    public Date getDtChegada() {
        return dtChegada;
    }

    public void setDtChegada(Date dtChegada) {
        this.dtChegada = dtChegada;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    private Funcionario funcionario;

    private Date dtEmisssao;

    private Date dtChegada;

    public CondicaoPagamento getCondicaoPagamento() {
        return condicaoPagamento;
    }

    public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    public List<ContaPagar> getContas() {
        return contas;
    }

    public void setContas(List<ContaPagar> contas) {
        this.contas = contas;
    }
}
