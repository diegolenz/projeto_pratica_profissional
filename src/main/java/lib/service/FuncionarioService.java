package lib.service;

import lib.dao.imp.sistema.FuncionarioDao;
import lib.model.interno.Funcionario;
import org.springframework.util.Assert;

import java.util.List;

public class FuncionarioService {

        /*-------------------------------------------------------------------
         *				 		     ATTRIBUTES
         *-------------------------------------------------------------------*/
        /**
         * Password encoder
         */

        private FuncionarioDao operadorDao;


        /*-------------------------------------------------------------------
         *				 		     CONSTRUCTOR
         *-------------------------------------------------------------------*/

        public FuncionarioService() {
            this.operadorDao = new FuncionarioDao();
        }

        /*-------------------------------------------------------------------
         *				 		     SERVICES
         *-------------------------------------------------------------------*/

        public void save(Object object)  throws Exception {
            Funcionario operador = (Funcionario) object;
            Assert.notNull(operador, "Operador não pode estar nula");
            Assert.notNull(operador.getNome(), "Nome é obrigatório");

            operadorDao.save(operador);
        }




        public void update(Funcionario operador) throws Exception{
            Assert.notNull(operador, "Operador não pode estar nula");
            Assert.notNull(operador.getNome(), "Nome é obrigatório");

            operadorDao.update(operador);
        }

        public List getAll() throws Exception {
            return operadorDao.getAll();
        }

        public List getAllOperadoresAtivos(String termo) throws Exception {
            return operadorDao.getAllAtivos(termo);
        }


        public Funcionario getByID(Integer id) throws Exception {
            Assert.notNull(id, "ID passado como parametro não pode estar nulo");
            Funcionario operador = operadorDao.getByID(id);
            Assert.notNull(operador, "Não foi encontrado nenhuma operador com esse código");
            return operador;
        }

        public void deleteByID(Funcionario operador) throws Exception {
            operadorDao.deleteByID(operador.getId());
           // enderecoService.deleteByID(operador.getEndereco().getId());
        }


        public Funcionario getByCpfCnpjExato(String cpf)  throws Exception {
            return operadorDao.getByCpfCnpjExato(cpf);
        }



    }



