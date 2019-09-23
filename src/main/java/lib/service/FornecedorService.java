package lib.service;

import lib.dao.imp.pessoa.FornecedorDao;
import lib.model.pessoa.Pessoa;
import lib.model.pessoa.TipoPessoa;
import lib.model.pessoa.fornecedor.Fornecedor;
import org.springframework.util.Assert;
import util.CpfCnpjValidator;

import java.util.Date;
import java.util.List;

public class FornecedorService {
    /*-------------------------------------------------------------------
     *				 		     ATTRIBUTES
     *-------------------------------------------------------------------*/
    /**
     * Password encoder
     */

    private FornecedorDao fornecedorDAO;


    /*-------------------------------------------------------------------
     *				 		     CONSTRUCTOR
     *-------------------------------------------------------------------*/

    public FornecedorService() {
        this.fornecedorDAO = new FornecedorDao();
    }

    /*-------------------------------------------------------------------
     *				 		     SERVICES
     *-------------------------------------------------------------------*/

    public void save(Object object)  throws Exception {
        Fornecedor pessoa=(Fornecedor) object;
        Assert.notNull(pessoa, "Pessoa não pode estar nula");
        Assert.notNull(pessoa.getNome(), "Nome é obrigatório");
       // Assert.isTrue(pessoa.getTelefone().length()> 7, "Telefone de contato deve ter ao minimo 8 numeros");
        if (pessoa.getDataNascimento() != null)
            Assert.isTrue(pessoa.getDataNascimento().compareTo(new Date()) < 0, "Data de nascimento/fundação deve ser menor que a data de hoje");
        Assert.notNull(pessoa.getCidade(), "Cidade é obrigatória");
        Assert.notNull(pessoa.getBairro(), "Bairro é obrigatório");
        Assert.notNull(pessoa.getNumeroResidencial(),"Numero é obrigatório");
        if (!pessoa.getTipo().equals(TipoPessoa.ESTRANGEIRO)) {
            Assert.notNull(pessoa.getCep(), "CEP é obrigatório");	Assert.notNull(pessoa.getCpfCnpj(),"CPF/CNPJ é obrigatório");
            if (pessoa.getTipo().isPessoaFisica())
                Assert.isTrue(CpfCnpjValidator.isCpfValido(pessoa.getCpfCnpj()), "CPF não é valido");
            else
                Assert.isTrue(CpfCnpjValidator.isCnpjValido(pessoa.getCpfCnpj()), "Cnpj não é valido");
        }
        Assert.notNull(pessoa.getNumeroResidencial(),"Numero é obrigatório");
        Pessoa pessoaRetorno = this.getByCpfCnpjExato(pessoa.getCpfCnpj());
        if (pessoa.getId() != null) {
            if (pessoaRetorno != null)
                if (pessoa.getId() != pessoaRetorno.getId())
                    Assert.notNull(pessoaRetorno, "Cpf já cadastrado !");
        } else if (pessoaRetorno != null)
            Assert.notNull(pessoaRetorno, "Cpf já cadastrado !");

        fornecedorDAO.save(pessoa);
    }

    public List<Fornecedor> getFornecedores(String termo) throws Exception {
        fornecedorDAO = new FornecedorDao();
        return fornecedorDAO.getAllFornecedores(termo);
    }

    public void update(Pessoa pessoa) throws Exception {
        Assert.notNull(pessoa, "Pessoa não pode estar nula");
        Assert.notNull(pessoa.getNome(), "Nome é obrigatório");
        Assert.isTrue(pessoa.getTelefone().length()> 7, "Telefone de contato deve ter ao minimo 8 numeros");
        Assert.isTrue(pessoa.getDataNascimento().compareTo(new Date()) < 0, "Data de nascimento/fundação deve ser menor que a data de hoje");
        if (pessoa.getTipo().equals(TipoPessoa.FISICA))
            Assert.isTrue(pessoa.getRgIe().length()>6, "Rg é um campo obrigatório e deve conter ao minimo 7 caracteres");
        Assert.notNull(pessoa.getCidade(), "Cidade é obrigatória");
        Assert.notNull(pessoa.getBairro(), "Bairro é obrigatório");
        Assert.notNull(pessoa.getNumeroResidencial(),"Numero é obrigatório");
        if (!pessoa.getTipo().equals(TipoPessoa.ESTRANGEIRO)) {
            Assert.notNull(pessoa.getCep(), "CEP é obrigatório");	Assert.notNull(pessoa.getCpfCnpj(),"CPF/CNPJ é obrigatório");
            Assert.isTrue(CpfCnpjValidator.isCpfValido(pessoa.getCpfCnpj()), "CPF não é valido");
            //Assert.notNull(pessoa.getDataNascimento(),"Data de nascimento/criação é obrigatório");

        }
        Assert.notNull(pessoa.getNumeroResidencial(),"Numero é obrigatório");
        Pessoa pessoaRetorno = this.getByCpfCnpjExato(pessoa.getCpfCnpj());
        if (pessoa.getId() != null) {
            if (pessoaRetorno != null)
                if (pessoa.getId() != pessoaRetorno.getId())
                    Assert.notNull(pessoaRetorno, "Cpf já cadastrado !");
        } else if (pessoaRetorno != null)
            Assert.notNull(pessoaRetorno, "Cpf já cadastrado !");

        fornecedorDAO.update(pessoa);
    }

    public List getAll(String termo) throws Exception {
        return fornecedorDAO.getAllFornecedores(termo);
    }

    public List getAllFornecedoresAtivos(String termo) throws Exception {
        return fornecedorDAO.getAllFornecedores(termo);
    }

    public List getAllFornecedores(String termo) throws Exception {
        return fornecedorDAO.getAllFornecedores(termo);
    }


    public Fornecedor getByID(Integer id) throws Exception {
        Assert.notNull(id, "ID passado como parametro não pode estar nulo");
        Fornecedor pessoa = fornecedorDAO.getByID(id);
        Assert.notNull(pessoa, "Não foi encontrado nenhuma pessoa com esse código");
        return pessoa;
    }

    public void deleteByID(Pessoa pessoa) throws Exception {
        fornecedorDAO.deleteByID(pessoa.getId());
    }


    public Fornecedor getByCpfCnpjExato(String cpf)  throws Exception {
        return fornecedorDAO.getByCpfCnpjExato(cpf);
    }
}
