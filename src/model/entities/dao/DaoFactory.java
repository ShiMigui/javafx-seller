package model.entities.dao;

import model.db.Database;

public final class DaoFactory {
    private static DepartmentDao departments;

    private DaoFactory() {
    }

    public static DepartmentDao getDepartmentDao() {
	if (departments == null) 
	    departments = new DepartmentDao(Database.getConnection());

	return departments;
    }
}
