package lib.dao.imp.servico;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lib.model.grupo.Grupo;

import lib.model.servico.Servico;
import lib.service.GrupoService;



import lib.dao.AbstractDao;

public class ServicoDao<T> extends AbstractDao<T>{


    public void save(Servico servico) throws Exception {
        String sql = "INSERT INTO servico (nome, ativo, valor, dt_ult_alteracao, dt_cadastro, grupo_id) values ('"+servico.getNome()+"', "+ servico.isAtivo() +
                ", "+ servico.getValor() +", '"+servico.getDt_ult_alteracao() +"', '"+servico.getDt_cadastro() +"', "+ servico.getGrupo().getId() +
                " ) ; ";
        this.st.executeUpdate(sql);
    }

    public void update(Servico servico) throws Exception {
        String sql = "update servico set nome = '"+ servico.getNome() +"', ativo = "+ servico.isAtivo() +", dt_ult_alteracao = '"+servico.getDt_ult_alteracao()  +
                "', dt_cadastro = '"+ servico.getDt_cadastro() +"'" +
                ", grupo_id = "+servico.getGrupo().getId()+" where id = "+ servico.getId() + " ;";
        this.st.executeUpdate(sql);
    }

    public List getAll(String termo) throws Exception {
        String sql ="";
        if (termo.length() == 0)
            sql = "SELECT * FROM servico ;";
        else if ((!termo.matches("[0-9]")))
            sql = "Select * from servico where id = "+ termo +";";
        else
            sql = "SELECT * FROM servico WHERE nome ="+ termo +";";

        ResultSet rs = this.st.executeQuery(sql);
        List servicos = new ArrayList();
        while (rs.next()){
            Servico servico=new Servico();
            servico.setId(rs.getInt("id"));
            servico.setAtivo(rs.getBoolean("ativo"));
            servico.setNome(rs.getString("nome"));
            servico.setGrupo((Grupo) new GrupoService().getByID(rs.getInt("grupo_id")));
            servico.setValor(rs.getDouble("valor"));
            servico.setDt_ult_alteracao(rs.getDate("dt_ult_alteracao"));
            servico.setDt_cadastro(rs.getDate("dt_cadastro"));
            servicos.add(servico);
        }
        return servicos;
    }



    public List getAllAtivos(String termo)throws Exception{
        String sql = "";
        if (termo.length() == 0)
            sql = "SELECT * FROM servico where ativo = true ;";
        else if ((!termo.matches("[0-9]")))
            sql = "Select * from servico where id = "+ termo +" and ativo = true ;";
        else
            sql = "SELECT * FROM servico WHERE nome like '%"+ termo +"%' and ativo = true";
        ResultSet rs = this.st.executeQuery(sql);
        List servicos = new ArrayList();
        while (rs.next()){
            Servico servico=new Servico();
            servico.setId(rs.getInt("id"));
            servico.setAtivo(rs.getBoolean("ativo"));
            servico.setNome(rs.getString("nome"));
            servico.setGrupo((Grupo) new GrupoService().getByID(rs.getInt("grupo_id")));
            servico.setValor(rs.getDouble("valor"));
            servico.setDt_ult_alteracao(rs.getDate("dt_ult_alteracao"));
            servico.setDt_cadastro(rs.getDate("dt_cadastro"));
            servicos.add(servico);
        }
        return servicos;
    }

    public Object getByID(Integer id)throws Exception{
        String sql = "Select from servico where id ="+id+" ;";
        ResultSet rs = this.st.executeQuery(sql);
        Servico servico=null;
        while (rs.next()){
            servico=new Servico();
            servico.setId(rs.getInt("id"));
            servico.setAtivo(rs.getBoolean("ativo"));
            servico.setNome(rs.getString("nome"));
            servico.setGrupo((Grupo) new GrupoService().getByID(rs.getInt("grupo_id")));
            servico.setValor(rs.getDouble("valor"));
            servico.setDt_ult_alteracao(rs.getDate("dt_ult_alteracao"));
            servico.setDt_ult_alteracao(rs.getDate("dt_cadastro"));
        }
        return servico;
    }

    public void deleteByID(Integer id)throws Exception{
        String sql = "DELETE  FROM servico WHERE id = "+id+" ;";
        this.st.executeUpdate(sql);
    }




}
