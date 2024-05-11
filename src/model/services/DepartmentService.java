package model.services;

import model.entities.Department;
import model.entities.dao.DaoFactory;
import model.entities.dao.DepartmentDao;
import model.interfaces.IEntityDao;
import model.interfaces.IEntityService;

public class DepartmentService implements IEntityService<Department> {
	private static DepartmentService instance;
	private DepartmentDao dao;

	private DepartmentService(DepartmentDao dao) {
		this.dao = dao;
	}

	public static DepartmentService getInstance() {
		if (instance == null)
			instance = new DepartmentService(DaoFactory.getDepartmentDao());
		return instance;
	}

	@Override
	public IEntityDao<Department> getEntityDao() {
		return dao;
	}
}
