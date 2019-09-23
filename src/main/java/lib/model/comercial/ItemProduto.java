package lib.model.comercial;

import lib.model.produto.Produto;

public class ItemProduto extends Produto{

    private Double quantidade ;

    private Double descontoUnitario;

    private Double acrescimoUnitario;

    private Compra compra;

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double qtd) {
        this.quantidade = qtd;
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
        return (getValor() * getQuantidade()) + (getAcrescimoUnitario() * getQuantidade()) - (getDescontoUnitario() * getQuantidade()) ;
    }

    public Double getTotaisAcrescimos(){
        return getAcrescimoUnitario() * getQuantidade();
    }

    public Double getTotaisDescontos(){
        return getDescontoUnitario() * getQuantidade();
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public void buildItem(Produto p){
        this.setId(p.getId());
        this.setNome(p.getNome());
        this.setAtivo(p.getAtivo());
        this.setMarca(p.getMarca() != null ? p.getMarca(): null);
        this.setGrupo(p.getGrupo() != null ? p.getGrupo(): null);
        this.setDataCadastro(p.getDataCadastro());
        this.setDataUltimaAlteracao(p.getDataUltimaAlteracao());
        this.setValor(p.getValor());
        this.setCodigoBarras(p.getCodigoBarras());
        this.setQuantidadeEstoque(p.getQuantidadeEstoque());
        this.setQuantidadeMinima(p.getQuantidadeMinima());
    }


}
