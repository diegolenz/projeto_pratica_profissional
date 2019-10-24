/**
 * (C) 2015-2018 JL INFORMATICA LTDA ME
 * CNPJ 14.928.793/0001-18
 * www.jlinformatica.net.br
 * (45) 3559-1534
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.model.interno;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * Classe de entidade de grupo de operador
 * 
 * @author Diego
 * @since 1.3.1
 */

public class GrupoFuncionario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private List<PermissaoAcesso> permissoes;

    private Funcionario funcionario;

    public List<PermissaoAcesso> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoAcesso> permissoes) {
        this.permissoes = permissoes;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public GrupoFuncionario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.getId()).toHashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        GrupoFuncionario other = (GrupoFuncionario) obj;
        if (this.getId() == null || other.getId() == null) {
            return super.equals(obj);
        }
        
        return this.getId().equals(other.getId());
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
