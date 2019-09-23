package br.edu.udc.sistemas.pwm2018.infra;

public abstract class Session {

	private DAO dao;
	private Class <?> daoClass;
	
	public Session(Class <?> daoClass) {
		this.daoClass = daoClass;
		this.dao = null;
	}
	
	protected DAO getDAOInstance() throws Exception {
		if (this.dao == null) {
			this.dao = (DAO) this.daoClass.getConstructor().newInstance();
		}
		return this.dao;
	}

	public void save(Object obj) throws Exception {
		this.save(obj, true);
	}

	public void remove(Integer id) throws Exception {
		this.remove(id, true);
	}

	public void remove(Object obj) throws Exception {
		this.remove(obj, true);
	}

	public abstract void save(Object obj, Boolean bCommit) throws Exception;

	public abstract void remove(Integer id, Boolean bCommit) throws Exception;

	public abstract void remove(Object obj, Boolean bCommit) throws Exception;

	public abstract Object[] find(Object obj) throws Exception;

	public abstract Object detail(Integer id) throws Exception;

	public abstract Object detail(Object obj) throws Exception;
}