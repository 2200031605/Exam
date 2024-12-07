package GeneratorClassDemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import GeneratorClassDemo.Employee;

 

public class Demo
{
  public static void main(String args[])
  {
	Demo demo = new Demo();
	//demo.addEmployee();
	//demo.displayallemps();
}
  public void addEmployee()
  {
	  Configuration configuration = new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();
	   Session session = sf.openSession();
	   Transaction t = session.beginTransaction();
	   
	    Employee e = new Employee();
	    e.setName("EP");
	    e.setGender("Female");
	    e.setContact("2314876590");
	    
	   
	   session.persist(e);//for hibernate we use this logic 
	   t.commit();
	   System.out.println(" Employee  added succesfully");
	   session.close();
	   sf.close();
  }
  public void displayallemps()
  {

	  Configuration configuration = new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();
	   Session session = sf.openSession();
	   
	   String hql="from Employee";//select * from employee_table
	   Query<Employee> qry= session.createQuery(hql,Employee.class);
	   List<Employee> emps=qry.getResultList();
	   
	   System.out.println("Total Employee ="+emps.size());
	   
	   for(Employee e: emps)
	   {
		   System.out.println("ID="+e.getId());
		   System.out.println("Name="+e.getName());
		   System.out.println("Gender ="+e.getGender());
		   System.out.println("Contact="+e.getContact());
	   }
	   
	   session.close();
	   sf.close();
	   
  }
}
