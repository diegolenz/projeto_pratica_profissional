package lib.dao;

import javax.swing.*;


import util.JpaUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;


public class AbstractDao<T> {

    private boolean autoCommit = true;
    private AbstractDao entityManagerHolder;
    public static Statement st;
    protected Connection con;

    public void Conexão() {

        try{

            String url ="jdbc:postgresql://localhost:5432/gestao";
            String usuario="postgres";
            String senha = "postgres";

            Class.forName("org.postgresql.Driver");

            con=null;
            con= DriverManager.getConnection(url,usuario,senha);
            this.st = con.createStatement();
            System.out.println("Conexão realizada com sucesso.");


        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar com o banco de dados : " + e);
        }

    }


    protected String corrigirTermosBusca(String termos) {
        return "%" + termos.replace(" ", "%") + "%";
    }
}





