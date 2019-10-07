package lib.model.comercial;

import lib.model.financeiro.StatusConta;
import lib.model.financeiro.formaPagamento.FormaPagamento;
import lib.model.financeiro.parcela.Parcela;

import java.util.Date;

public class ContaPagar {

    private Integer id;

    private Compra compra;

    private Parcela parcela;

    private Double valor;

    private Date dataLancamento;

    private Date dataVencimento;

    private FormaPagamento formaPagamento;

    private boolean paga;

    private Date dataPagamento;
    private Double valorRecebido;

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

    public Double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
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

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
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
