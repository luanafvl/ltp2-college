package model.dao;

import model.dao.impl.ClienteDaoJDBC;

public class DaoFactory {
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(null);
	}
}
