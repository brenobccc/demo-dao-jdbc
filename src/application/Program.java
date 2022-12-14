package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		// Department	obj = new Department(1,"Books");
		// Seller seller = new Seller(21,"Bob","bob@gmail.com",new Date(),3000.0,obj);
		 try {
			 SellerDao sellerDao = DaoFactory.createSellerDao();
			 System.out.println("=== TEST 1: seller findById =====");
			 Seller seller = sellerDao.findById(3);
			 System.out.print(seller.toString());
			 
			 System.out.println("\n=== TEST 2: seller findByDepartment =====");
			 List<Seller> list = sellerDao.findByDepartment(new Department(2,null));
			 
			 for(Seller obj : list) {
				 System.out.println(obj);
			 }
			 
			 System.out.println("\n=== TEST 3: seller findAll =====");
			 list = sellerDao.findAll();
			 
			 for(Seller obj : list) {
				 System.out.println(obj);
			 }
			 
			 
			 System.out.println("\n=== TEST 4: seller insert =====");
			Seller newSeller = new Seller(null, "Greg","greg@gmail.com",new Date(), 4000.0,new Department(2,null));
			
			sellerDao.insert(newSeller);
			System.out.println("Inserted! New id = "+newSeller.getId());
			
			 
			System.out.println("\n=== TEST  5 : seller update =====");
			seller = sellerDao.findById(1);
			
			seller.setName("Martha Waine");
			sellerDao.update(seller);
			System.out.println("Update completed");
			
			System.out.println("\n=== TEST 6: seller delete ====");
			
			System.out.println("Enter id for delete test: ");
			
			int id = sc.nextInt();
			sellerDao.deleteById(id);
			//System.out.println("Delete completed");
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 sc.close();
		 }
		 
		 //System.out.println(seller);
	}
}
