package lib.model.financeiro.contas;

import lib.model.comercial.Compra;
import lib.model.financeiro.StatusConta;
import lib.model.financeiro.formaPagamento.FormaPagamento;
import lib.model.financeiro.parcela.Parcela;
import lib.model.pessoa.fornecedor.Fornecedor;

import java.util.Date;

public class ContaPagar {

    private Integer id;

    private Compra compra;

    private String descricao;

    private Fornecedor recebedor;

    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Fornecedor getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(Fornecedor recebedor) {
        this.recebedor = recebedor;
    }

    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
// private Parcela parcela;

    private Double valor;

    private Date dataLancamento;

    private Date dataVencimento;

    private FormaPagamento formaPagamento;

    private Double desconto;

    private Double juros;

    private Double multa;

    private boolean paga;

    private Date dataPagamento;
    private Double valorPago;

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public StatusConta getStatusConta() {
        Date dateAtual = new Date();
        if (!paga) {
            if ( dataVencimento.compareTo(dateAtual) > 0)
                return StatusConta.PENDENTE;
            else return StatusConta.ATRASADO;
        } else {
            if (paga && dataVencimento.compareTo(dataPagamento) > 0 )
                return StatusConta.QUITADA;
            else return StatusConta.PAGA_COM_ATRASO;
        }

    }

}
