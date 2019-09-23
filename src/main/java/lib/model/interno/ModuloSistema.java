/**
 * (C) 2015-2018 JL INFORMATICA LTDA ME
 * CNPJ 14.928.793/0001-18
 * www.jlinformatica.net.br
 * (45) 3559-1534
 */
package lib.model.interno;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enumerçao de controle de módulos e permissoes do sistema
 *
 * @author Lucas Dillmann
 * @since 1.0
 */
public enum ModuloSistema implements Serializable {

    //produtos
    PRODUTOS_SERVICOS(CategoriaModuloSistema.PRODUTOS, "Produtos", true, true),

    //orcamentos
    ORCAMENTO(CategoriaModuloSistema.ORCAMENTOS, "Orçamentos", true,true),

    //PESSOAS
    PESSOAS(CategoriaModuloSistema.PESSOAS, "Pessoas", true, true),

    //VENDAS_COMPRAS
    FINANCEIRO(CategoriaModuloSistema.FINANCIRO, "Financeiro", true, true),

    //atendimento
    ATENDIMENTO_SUPERVISIONAR_ALTERACAO_TIPO_TITULO(CategoriaModuloSistema.SISTEMA, "Supervisionar alteração de tipo de título", false, true),
    ATENDIMENTO_SUPERVISIONAR_ALTERACAO_VALOR_TRANSFERENCIA_TITULO(CategoriaModuloSistema.SISTEMA, "Supervisionar alteração do valor de transferência de título", false, true),
    ATENDIMENTO_SUPERVISIONAR_ALTERACAO_VALOR_CANCELAMENTO_TITULO(CategoriaModuloSistema.SISTEMA, "Supervisionar alteração do valor de cancelamento de título", false, true),
    ATENDIMENTO_SUPERVISIONAR_CANCELAMENTO_CONTAS_RECEBER(CategoriaModuloSistema.SISTEMA, "Supervisionar cancelamento de contas a receber", false, true),




    // Sistema
    SISTEMA_GRUPO_OPERADORES(CategoriaModuloSistema.SISTEMA, "Grupos de operadores", true, true),
    SISTEMA_OPERADORES(CategoriaModuloSistema.SISTEMA, "Operadores", true, true),



    // ENDEREÇOS
    ENDERECO_ESTADO(CategoriaModuloSistema.ENDERECO, "Estado", true, true),
    ENDERECO_BAIRRO(CategoriaModuloSistema.ENDERECO, "Bairro", true, true),
    ENDERECO_CIDADE(CategoriaModuloSistema.ENDERECO, "Cidade", true, true);

    private final CategoriaModuloSistema categoria;
    private final String descricao;
    private final boolean permiteModoSomenteLeitura;
    private final boolean permiteModoLeituraGravacao;

    ModuloSistema(CategoriaModuloSistema categoria, String descricao, boolean permiteModoSomenteLeitura, boolean permiteModoLeituraGravacao) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.permiteModoSomenteLeitura = permiteModoSomenteLeitura;
        this.permiteModoLeituraGravacao = permiteModoLeituraGravacao;
    }

    public boolean isVirtual() {
        return categoria == CategoriaModuloSistema.VIRTUAL;
    }

    public CategoriaModuloSistema getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return categoria.getDescricao() + " - " + descricao;
    }

    public String getDescricaoSimplificada() {
        return descricao;
    }

    public boolean isModoSomenteLeituraPermitido() {
        return permiteModoSomenteLeitura;
    }

    public boolean isModoLeituraGravacaoPermitido() {
        return permiteModoLeituraGravacao;
    }

    public static final List<ModuloSistema> getAll() {
        return Arrays.stream(values())
                .filter(modulo -> !modulo.isVirtual())
                .sorted(Comparator.comparing(ModuloSistema::getDescricao))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getDescricao();
    }

}
