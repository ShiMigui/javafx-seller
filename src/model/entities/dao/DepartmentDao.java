package model.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.db.Database;
import model.db.DatabaseIntegrityException;
import model.entities.Department;
import model.interfaces.IEntityDao;

public class DepartmentDao implements IEntityDao<Department> {
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;

	protected DepartmentDao(Connection con) {
		this.con = con;
	}

	@Override
	public Department convert(ResultSet rs) {
		try {
			return new Department(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			throw new DatabaseIntegrityException("Cannot convert ResultSet to database!");
		}
	}

	@Override
	public Department findById(Integer id) {
		try {
			st = con.prepareStatement("SELECT nm_department FROM DEPARTMENT WHERE cd_department = ?;");
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
		try {
			st = con.prepareStatement("INSERT INTO DEPARTMENT (nm_department) VALUES (?);",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			if (st.executeUpdate() > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next())
					obj.setId(rs.getInt(1));
			} else
				throw new DatabaseIntegrityException("Unexpected error: No rows affected!");
		} catch (SQLException e) {
			throw new DatabaseIntegrityException("Cannot insert Department: " + e.getMessage());
		} finally {
			Database.close(st, rs);
		}
	}

	@Override
	public void update(Department obj) {
		try {
			if (obj.getId() == null)
				throw new DatabaseIntegrityException("Department id cannot be null!");

			st = con.prepareStatement("UPDATE DEPARTMENT SET nm_department = ? WHERE cd_department = ?;");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			if (st.executeUpdate() == 0)
				throw new DatabaseIntegrityException("Unexpected error: No rows affected!");
		} catch (SQLException e) {
			throw new DatabaseIntegrityException("Cannot update Department(" + obj.getId() + "): " + e.getMessage());
		} finally {
			Database.close(st, rs);
		}
	}

	@Override
	public List<Department> findAll() {
		try {
			List<Department> list = new ArrayList<>();
			st = con.prepareStatement("SELECT cd_department, nm_department FROM DEPARTMENT ORDER BY cd_department;");
			rs = st.executeQuery();

			while(rs.next())
				list.add(convert(rs));
			
			return list;
		} catch (SQLException e) {
			throw new DatabaseIntegrityException("Cannot select add Departments: " + e.getMessage());
		} finally {
			Database.close(st, rs);
		}
	}

}
