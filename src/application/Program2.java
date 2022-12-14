package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		try {
			
			System.out.printf("\n TESTE 1 ==== INSERT Department ");
			Department dp = new Department(null, "aaA");
			DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
			departmentDao.insert(dp);
			
			System.out.printf("\n TESTE 2 ==== UPDATE Department ");
			dp = new Department(7, "Breno");
			departmentDao.update(dp);
			
			System.out.printf("\n TESTE 3 ==== DELETE Department ");
			int id = 7;
			departmentDao.deleteById(id);
			
			System.out.println("\n TESTE 4 ==== find All Department");
			
			
			List<Department> listaDepartamentos = new ArrayList<Department>();
			listaDepartamentos = departmentDao.findAll();
			
			/*for(Department d : listaDepartamentos) {
				System.out.println(d.toString());
			}*/
			
			
			listaDepartamentos.forEach(System.out::println);
			
			
			System.out.println("\n TESTE 5 ==== find by id department");
			Department dpt = departmentDao.findById(1);
			
			System.out.println(dpt.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	

}
