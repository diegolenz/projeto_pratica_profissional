package lib.model.comercial;

import lib.model.servico.Servico;

public class ItemServico extends Servico{

    private Integer qtd ;

    private Double descontoUnitario;

    private Double acrescimoUnitario;

    private Double valorTotal;

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getDescontoUnitario() {
        return descontoUnitario;
    }

    public void setDescontoUnitario(Double descontoUnitario) {
        this.descontoUnitario = descontoUnitario;
    }

    public Double getAcrescimoUnitario() {
        return acrescimoUnitario;
    }

    public void setAcrescimoUnitario(Double acrescimoUnitario) {
        this.acrescimoUnitario = acrescimoUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
