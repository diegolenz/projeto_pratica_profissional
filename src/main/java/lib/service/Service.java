package lib.service;

import lib.dao.AbstractDao;

import java.sql.SQLException;

public class Service {
    private AbstractDao abstractDao;

    public Service(){
        abstractDao = new AbstractDao();
    }

    public void setAutoCommit(Boolean is)throws SQLException{
        this.abstractDao.setAutoCommit(is);
    }

    public void commit()throws SQLException {
        abstractDao.commit();
    }

    public void clear()throws SQLException{
        abstractDao.clear();
    }
}
