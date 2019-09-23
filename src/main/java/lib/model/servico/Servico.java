package lib.model.servico;

import lib.model.grupo.Grupo;


import java.util.Date;

public class Servico {

    Integer id;

    private String nome;

    private Date dt_ult_alteracao;

    private Date dt_cadastro;

    private Double Valor;

    private Integer qtdVend;

    private boolean ativo;

    private Grupo grupo;

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public Integer getQtdVend() {
        return qtdVend;
    }

    public void setQtdVend(Integer qtdVend) {
        this.qtdVend = qtdVend;
    }

    public Date getDt_ult_alteracao() {
        return dt_ult_alteracao;
    }

    public void setDt_ult_alteracao(Date dt_ult_alteracao) {
        this.dt_ult_alteracao = dt_ult_alteracao;
    }

    public Date getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(Date dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }
}
