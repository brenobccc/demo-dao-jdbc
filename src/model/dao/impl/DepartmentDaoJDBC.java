package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement(
					"INSERT INTO department "
					+ "(Name) "
					+ "VALUES "
					+ "(?) ",Statement.RETURN_GENERATED_KEYS);
			st.setString(1,obj.getName());
			
			//st.executeUpdate();
			

		    int rowsAffected = st.executeUpdate();
		    
		    if(rowsAffected > 0) {
		    	ResultSet rs = st.getGeneratedKeys();
		    	if(rs.next()) {
		    		int id = rs.getInt(1);
		    		obj.setId(id);
		    	}
		    	DB.closeResultSet(rs);
		    	System.out.println("Incluído com sucesso");
		    }
		    else {
		    	throw new DbException("Error inesperado! Nenhuma linha foi alterada! ");
		    }
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE department SET Name = ? "
					+ "WHERE Id = ? ",Statement.RETURN_GENERATED_KEYS);
				
			st.setString(1, obj.getName()); ;
		    st.setInt(2, obj.getId());
		    
		    int rows = st.executeUpdate();
		   	
		    if(rows > 0) {
		    	System.out.println("Atualizado com sucesso");
		    } 
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM department where Id = ? ",Statement.RETURN_GENERATED_KEYS);
				
			st.setInt(1,id);
			//st.setString(1, obj.getName()); ;
		    //st.setInt(8, obj.getId());
		    
		    int rows = st.executeUpdate();
		   	
		    if(rows > 0) {
		    	System.out.println("Deletado com sucesso");
		    } 
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department "
					+ "WHERE Id = ? ", Statement.RETURN_GENERATED_KEYS);
		
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
	
			if(rs!=null) {
				rs.next();
				int identify = rs.getInt("Id");
				String nome = rs.getString("Name");	
				rs.close();
				return new Department(identify, nome);
			}
			
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(""
					+ "SELECT * FROM department");
			
			ResultSet rs = st.executeQuery();
			
			ArrayList<Department> listaDepartamentos = new ArrayList<Department>();

			while(rs.next()) {
					String nome = rs.getString("Name");
					int id = rs.getInt("Id");
					listaDepartamentos.add(new Department(id,nome));
			}
			DB.closeResultSet(rs);
			
			return listaDepartamentos;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
	
	}

}
