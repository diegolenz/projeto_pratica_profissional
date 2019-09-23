package br.edu.udc.sistemas.pwm2018.infra;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DAO {

	protected Connection connection;

	public DAO() throws Exception {
		this.connection = DatabasePool.getInstance().getConnection();
	}

	public DAO(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void commit() throws Exception {
		this.connection.commit();
		DatabasePool.getInstance().releaseConnection(connection);
	}

	public void releaseConnection() throws Exception {
		DatabasePool.getInstance().releaseConnection(connection);
	}

	public void rollback() throws Exception {
		this.connection.rollback();
		DatabasePool.getInstance().releaseConnection(connection);
	}

	public void save(Object obj) throws Exception {
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = this.connection.createStatement();
			Integer id = (Integer) Reflection.getIdFieldValue(obj);
			if ((id != null) && (id > 0)) {
				String sql = Query.getSQLUpdate(obj);
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				String sql = Query.getSQLInsert(obj);
				System.out.println(sql);
				stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					Reflection.setIdFieldValue(obj, rst.getInt(1));
				}
			}
		} catch (Exception e) {
			try {
				this.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
			if (rst != null) {
				try {
					rst.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	public void remove(Object obj) throws Exception {
		Statement stmt = null;
		try {
			stmt = this.connection.createStatement();
			Integer id = (Integer) Reflection.getIdFieldValue(obj);
			if ((id != null) && (id > 0)) {
				String sql = Query.getSQLDelete(obj);
				System.out.println(sql);
				stmt.execute(sql);
			}
		} catch (Exception e) {
			try {
				this.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	public final Object[] find(Object obj) throws Exception {
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = this.connection.createStatement();
			String sql = Query.getSQLSelect(obj);
			System.out.println(sql);
			rst = stmt.executeQuery(sql);
			return Factory.createByResultSet(rst, obj.getClass());
		} catch (Exception e) {
			try {
				this.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
			if (rst != null) {
				try {
					rst.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	public Object findByPrimaryKey(Object obj) throws Exception {
		Integer id = (Integer) Reflection.getIdFieldValue(obj);
		if ((id != null) && (id > 0)) {
			Class<?> c = obj.getClass();
			Object objFilter = c.getConstructor().newInstance();
			Reflection.setIdFieldValue(objFilter, id);

			Object list[] = this.find(objFilter);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}

}