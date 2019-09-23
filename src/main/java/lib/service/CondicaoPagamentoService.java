package lib.service;

import lib.dao.imp.financeiro.condicaoPagamentoDAO.CondicaoPagamentoDAO;
import lib.model.endereco.estado.Estado;
import lib.model.financeiro.CondicaoPagamento.CondicaoPagamento;
import lib.model.financeiro.parcela.Parcela;
import org.springframework.util.Assert;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CondicaoPagamentoService {

    CondicaoPagamentoDAO condicaoPagamentoDAO;
    ParcelaService parcelaService;

    public void save(CondicaoPagamento condicaoPagamento) throws Exception{
        Assert.notNull(condicaoPagamento, "estado não pode estar nulo");
        Assert.notNull(condicaoPagamento.getNome(), "Campo Nome precisa ser preenchido");
        Assert.isTrue(condicaoPagamento.getNome().length() > 1, "Campo nome precisa ao menos 2 caracteres sem contabilizar espaços");
        Assert.notNull(condicaoPagamento.getNome(), "Selecione um país");
        Assert.isTrue(!condicaoPagamento.getNome().trim().isEmpty(), "Nome da condição de pagamento não pode ser nula");
        List<Parcela> parcelas = condicaoPagamento.getParcelas();
        Assert.isTrue(parcelas.stream().mapToDouble(Parcela::getPorcentagem).sum() == 100, "A porcentagem " +
                "somadas das parcelas adicionadas deve ser 100 %");

            parcelaService = new ParcelaService();
            for (Parcela parcela : condicaoPagamento.getParcelas())
                    parcelaService.save(parcela);
        condicaoPagamentoDAO.save(condicaoPagamento);
    }

    public void update(CondicaoPagamento condicaoPagamento) throws Exception{
        Assert.notNull(condicaoPagamento, "Condição de pagamento não pode estar nulo");
        Assert.notNull(condicaoPagamento.getId(), "Código não pode estar nulo");
        Assert.notNull(condicaoPagamento.getNome(), "Campo Nome precisa ser preenchido");
        Assert.isTrue(condicaoPagamento.getNome().length()>2, "Nome da condição é obrigatório e deve conter ao menos 2 caracteres");
        if ( (!condicaoPagamento.getParcelas().isEmpty())) {
            parcelaService = new ParcelaService();
            for (Parcela parcela : condicaoPagamento.getParcelas())
                parcelaService.save(parcela);
        }
        condicaoPagamentoDAO.update(condicaoPagamento);
    }

    public List getAll(String termo) throws Exception {
        return condicaoPagamentoDAO.getAll( termo);
    }

    public List getAllAtivos(String termo) throws Exception {
        List list = condicaoPagamentoDAO.getAllAtivos(termo);
      //  Assert.isTrue(list.size() > 0, "Nenhum resultado encontrado");
        return list;
    }


    public CondicaoPagamento getByID(Integer id) throws Exception {
        Assert.notNull(id, "ID passado como parametro não pode estar nulo");
        CondicaoPagamento condicaoPagamento = condicaoPagamentoDAO.getByID(id);
        Assert.notNull(condicaoPagamento, "Não foi encontrado nenhum estado com esse código");
        return  condicaoPagamento;
    }

    public CondicaoPagamento getLast()throws Exception {
        return condicaoPagamentoDAO.getLast();
    }

    public CondicaoPagamento getByNome(String nome)throws SQLException {
        return condicaoPagamentoDAO.getByNome(nome);
    }

    public void deleteByID(Integer id) throws Exception {
        condicaoPagamentoDAO.deleteByID(id);
    }

    public void mudarStatus(Estado estado){


    }

    public CondicaoPagamentoService() {
        this.condicaoPagamentoDAO = new CondicaoPagamentoDAO();
    }
}
