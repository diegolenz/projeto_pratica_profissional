/**
 * (C) 2015-2018 JL INFORMATICA LTDA ME
 * CNPJ 14.928.793/0001-18
 * www.jlinformatica.net.br
 * (45) 3559-1534
 */

package lib.model.interno;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class TipoOperador implements Serializable {

    private static final Long serialVersionUID = 1L;


    private Long id;

    private String descricao;

    private String observacoesAdicionais;

    private List<PermissaoAcesso> permissoes;

    public TipoOperador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<PermissaoAcesso> getPermissoes() {
        if(permissoes == null) permissoes = new ArrayList<>();
        return permissoes;
    }

    public void setPermissoes(List<PermissaoAcesso> permissoes) {
        this.permissoes = permissoes;
    }

    public String getObservacoesAdicionais() {
        return observacoesAdicionais;
    }

    public void setObservacoesAdicionais(String observacoesAdicionais) {
        this.observacoesAdicionais = observacoesAdicionais;
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

        TipoOperador other = (TipoOperador) obj;
        if (this.getId() == null || other.getId() == null) {
            return super.equals(obj);
        }

        return this.getId().equals(other.getId());

    }

    @Override
    public String toString() {
        return descricao;
    }

}
