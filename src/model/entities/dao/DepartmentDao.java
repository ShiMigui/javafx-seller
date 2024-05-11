package model.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.db.Database;
import model.db.DatabaseIntegrityException;
import model.entities.Department;
import model.interfaces.IEntityDao;

public class DepartmentDao implements IEntityDao<Department> {
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;

	public DepartmentDao(Connection con) {
		this.con = con;
	}

	@Override
	public Department convert(ResultSet rs) {
		return null;
	}

	@Override
	public Department finById(Integer id) {
		try {
			st = con.prepareStatement("SELECT nm_department FROM DEPARTMENT WHERE cd_department = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next())
				return new Department(id, rs.getString(1));
			else
				throw new DatabaseIntegrityException("Unexpected error: The Result has no rows!");
		} catch (SQLException e) {
			throw new DatabaseIntegrityException("Cannot select Department(" + id + "): " + e.getMessage());
		} finally {
			Database.close(st, rs);
		}
	}

	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
