package lib.model.financeiro;

public enum StatusConta {

    ATRASADO("ATRASADO"),
    QUITADA("QUITADA"),
    PENDENTE("PENDENTE"),
    PAGA_COM_ATRASO("Paga com atraso");

    private String descricao;

    StatusConta(String descricao) {
        this.descricao=descricao;
    }
}
