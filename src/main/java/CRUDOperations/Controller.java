package CRUDOperations;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//use persistant object we can create,update,delete 
public class Controller 
{
   public static void main(String args[])
   {
	   Controller controller = new Controller();
	   Scanner sc = new Scanner(System.in);
		int  con = 0;
		 while (con != 0);
		 {
		System.out.println("1.addfaculty");
		System.out.println("2.displayfacultybyid");
		System.out.println("3.updatefaculty");
		System.out.println("4.deletefaculty");
		System.out.println("5.exit");
		
		
		con=sc.nextInt();
	
		switch (con) 
		{
		case 1 :
			controller.addfaculty();	
			break;
			
		case 2 :
			controller.displayfacultybyid();
			break;
		case 3:
			controller.updatefaculty();
		    break;
		
		case 4:
			controller.deletefaculty();
			
		    break;	
		default:
			System.out.println("exit");
		
			
		 }
		 }

    sc.close();
		
		
		
	}
			  
	   
	   //controller.addfaculty();
	   //controller.displayfacultybyid();
	   //controller.updatefaculty();
	   //controller.deletefaculty();
	   
  
   public void addfaculty()
   {
	   Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   Transaction t = session.beginTransaction();//based on operations 
	   Scanner sc = new Scanner(System.in);
	   
	   Faculty faculty = new Faculty();
	   
	   int fid=sc.nextInt();
	   String fname=sc.nextLine();
	   String fgender=sc.nextLine();
	   String fdepartment=sc.nextLine();
	   Double fsalary=sc.nextDouble();
	   String fcontactno=sc.nextLine();
			   
	   faculty.setId(103);
	   faculty.setName("pavani");
	   faculty.setGender("Female");
	   faculty.setDepartment("CSE");
	   faculty.setSalary(12220.0);
	   faculty.setContactno("9991234592");
	   
	   session.persist(faculty);
	   t.commit();
	   
	   System.out.println("faculty added succesfully");
	   
	   session.close();
	   sf.close();//this is session pattern
	   sc.close();
   }
   //display or find faculty based on ID Column
   public void displayfacultybyid()
   {
	   
	   Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   //there is no need to create transaction object because there is no DML  Operation
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Faculty ID:");
	   int fid=sc.nextInt();
	  Faculty faculty=  session.find(Faculty.class, fid);
	  if(faculty!=null)
	  {
		  System.out.println(" Faculty ID: "+faculty.getId() );
		  System.out.println(" Faculty Name: "+faculty.getName() );
		  System.out.println(" Faculty Gender: "+faculty.getGender() );
		  System.out.println(" Faculty Department: "+faculty.getDepartment() );
		  System.out.println(" Faculty Salary: "+faculty.getSalary() );
		  System.out.println(" Faculty Contactno: "+faculty.getContactno() );
	  }
	  else
	  {
		  System.out.println("Faculty id Not Found ");
	  }
	   
	   
	   session.close();
	   sf.close();
	   sc.close();
   }
   public void updatefaculty()
   {
	   Configuration configuration = new Configuration();//to configure the xml file,
	   //while choosing check the package carefully
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//to read the database //no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   Transaction t = session.beginTransaction();//based on operations 
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Faculty ID Update:");
	   int fid=sc.nextInt();
	  Faculty faculty=  session.find(Faculty.class, fid);
	   
	   if(faculty!=null)
	   {
		   System.out.println("Enter Faculty Name");
		   String fname=sc.next();
		   System.out.println("Enter Faculty contact");
		   String fcontactno=sc.next();
		   System.out.println("Enter Faculty salary");
		   double fsalary=sc.nextDouble();
		   
		   faculty.setName(fname);
		   faculty.setSalary(fsalary);
		   faculty.setContactno(fcontactno);
		   
		   t.commit();
		   System.out.println("Faculty updated succesfully");
		   
	   }
	   else
	   {
		   System.out.println("Faculty ID Not Found");
	   }
	   sc.close();
	   session.close();
	   sf.close();
	   
	   
   }
   public void deletefaculty()
   {
	   Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   Transaction t = session.beginTransaction();//based on operations 
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Faculty ID to Delete:");
	   int fid=sc.nextInt();
	   Faculty faculty=  session.find(Faculty.class, fid);
	   if(faculty!=null)
	   {
		   
		   session.remove(faculty);
		   t.commit();
		   System.out.println("Faculty Deleted Succesfully");
	   }
	   else
	   {
		   System.out.println("Faculty Id not found");
	   }
	   
	   
   }
}
