package model.entities.dao;

import model.db.Database;

public class DaoFactory {
	private static DepartmentDao departments;

	public static DepartmentDao getDepartmentDao() {
		if (departments == null)
			departments = new DepartmentDao(Database.getConnection());

		return departments;
	}
}
