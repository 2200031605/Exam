package InheritanceMapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InheritanceDemo 
{
  public static void main(String args[])
  {
	  Configuration cfg = new Configuration();
  	  cfg.configure("hibernate.cfg.xml");
  	
  	  SessionFactory sf = cfg.buildSessionFactory();
  	  Session session = sf.openSession();
  	
  	  Transaction t = session.beginTransaction();
  	  
  	  Person p = new Person();

  	  p.setName("SURYA");
  	  p.setAge(30);
  	  p.setEmail("surya@gmail.com");
  	  p.setContact("9394034040");
  	  p.setLocation("VJA");
  	  
  	  Teacher teacher = new Teacher();
  	  teacher.setDepartment("CSE");
  	  teacher.setQualification("MTECH");
  	  teacher.setDesignation("ASST PROF");
  	  teacher.setSalary(30000);
  	  
  	  Scholar s = new Scholar();
  	  s.setDepartment("CSE");
  	  s.setProgram("BTECH");
  	  s.setYear(2);
  	  s.setSemester("ODD");
  	  s.setCgpa(5.6);
  	  s.setBacklogs(0);
  	  
  	  session.persist(p);
  	  session.persist(teacher);
  	 session.persist(s);
  	 
  	   t.commit();
  	   System.out.println("SUCCESS....");
  	
  	session.close();
  	sf.close();
  }
}
