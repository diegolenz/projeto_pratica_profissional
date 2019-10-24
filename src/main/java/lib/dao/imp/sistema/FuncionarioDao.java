package lib.dao.imp.sistema;

import lib.dao.AbstractDao;
import lib.model.interno.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao  extends AbstractDao {

        private Funcionario operador;

        public FuncionarioDao() {
            this.operador = new Funcionario();
        }

        public void save(Object obj) throws Exception {
            Funcionario operador = (Funcionario) obj;
            String sql = "";
            sql = "";

            this.st.executeUpdate(sql);
        }

        public void deleteByID(Object id) throws Exception {
            String sql = "DELETE FROM funcionario WHERE id = " + id + " ;";
            this.st.executeUpdate(sql);
        }


        public List getAll() throws Exception {

            ResultSet rs = this.st.executeQuery("SELECT * FROM funcionario;");
            List<Funcionario> pessoas = new ArrayList<>();

            while (rs.next()) {

            }
            return pessoas;
        }






        public void update(Object obj) throws Exception {
            operador = (Funcionario) obj;
            String sql ="";
            this.st.executeUpdate(sql);
        }

        public List<Funcionario> getAllAtivos(String termo) throws Exception {

            String sql ="";
            if (termo.length() ==0)
                sql = "SELECT * FROM funcionario where   ativo = true ;" ;
            else if ((termo.matches("[0-9]")))
                sql = "SELECT * FROM funcionario where  id = "+termo+" and ativo = true ;" ;
            else
                sql = "SELECT * FROM funcionario where  nome LIKE '%"+termo+"%' and ativo = true ;" ;

            ResultSet rs = this.st.executeQuery(sql);
            List<Funcionario> pessoas=new ArrayList<>();
            while (rs.next()) {

            }
            return pessoas;
        }

        public Funcionario getByID(Integer id) throws SQLException {
            PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM funcionario WHERE ID = "+id+";");
            ResultSet rs = preparedStatement.executeQuery();
            Funcionario operador=new Funcionario();
            while (rs.next()) {

            }
            return operador;
        }

        public Funcionario getByCpfCnpjExato(String cpf) throws Exception {
            PreparedStatement preparedStatement=st.getConnection().prepareStatement("SELECT * FROM funcionario WHERE cpfcnpj = '"+cpf+"' ;");
            ResultSet rs = preparedStatement.executeQuery();
            Funcionario operador = null ;
            if (rs.next()) {



            }
            return operador;

        }




    }

