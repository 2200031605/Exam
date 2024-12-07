package HQLDemo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import CRUDOperations.Controller;

public class HQLOperations
{
 public static void main(String args[])
 {
	 HQLOperations operations = new HQLOperations();
	   Scanner sc = new Scanner(System.in);
		int  ch = 0;
		 while (ch != 0);
		 {
			 System.out.println("1.addproduct");
			 System.out.println("2.displayallproducts-completeobj");
			 System.out.println("3.displayallproduct- partialobject");
			 System.out.println("4.aggregate functions");
			 System.out.println("5.updatepositionalparams");
			 System.out.println("6.updatenamedparams");
			 System.out.println("7.deletepositionalparams");
			 System.out.println("8.deletenamedparams");
			 System.out.println("9.displayproductby id positional params");
			 System.out.println("10.displayproduct by id named params");
			 System.out.println("11.display products");
			 System.out.println("12.paginationdemo");
			 System.out.println("13.exit");

		
		ch=sc.nextInt();
	
		switch (ch) 
		{
		case 1 :
			operations.addproduct();	
			break;
			
		case 2 :
			operations.displayallproductscompleteobj();
			break;
		case 3:
			operations.displayallproductpartialobject();
		    break;
		
		case 4:
			operations.aggregatefunctions();
			
		    break;	
		case 5:
			operations.updatepositionalparams();
			break;
		case 6:
			operations.updatenamedparams();
			break;
		case 7:
			operations.deletepositionalparams();
			break;
		case 8:
			operations.deletenamedparams();
			break;
		case 9:
			operations.displayproductbyidpositionalparms();
			break;
		case 10:
			operations.displayproductbyidnamedparams();
			break;
		case 11:
			operations.displayproducts();
			break;
		case 12:
			operations.paginationdemo();
			break;
		default:
			System.out.println("exit");
		
			
		 }
		 }

  sc.close();
	 
 }
 
//operations.addproduct();
	 //operations.displayallproductscompleteobj();
	 //operations.displayallproductpartialobject();
	 //operations.aggregatefunctions();
	 //operations.updatepositionalparams();
	 //operations.updatenamedparams();
	// operations.deletepositionalparams();
	// operations.deletenamedparams();
	 //operations.displayproductbyidpositionalparms();
	 //positional,named used for dynamic modules
	 //operations.displayproductbyidnamedparams();
	 //operations.displayproducts();
 //adding product using persistent object(PO)
 public void addproduct()
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   Transaction t = session.beginTransaction();//based on operations 
	   
	   
	   Product product = new Product();
	   product.setId(5);
	   product.setCategory("GROCERIES");
	   product.setName("MILK");
	   product.setCost(25);
	   product.setStock(0);
	   //please set stock status based  on stock value;
	   product.setStatus(false);
	   
	   session.persist(product);//for hibernate we use this logic 
	   t.commit();
	   System.out.println(" product added succesfully");
	   session.close();
	   sf.close();
	   
 }
 public  void displayallproductscompleteobj()//complete object ,// query we used is select
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   String hql = " from Product";//we will retrieve all the "Properties"
	   
	   Query<Product> qry = session.createQuery(hql, Product.class) ;
	   List<Product> productlist = qry.getResultList();//now the product size is 3 because we have data we have is 3 products
	   System.out.println("Total Products="+productlist.size());
	   
	   for(Product p :productlist)//the for loop run n times 
	   {
		   System.out.println("id:"+p.getId());
		   System.out.println("Category:"+p.getCategory());
		   System.out.println("Name:"+p.getName());
		   System.out.println("Cost:"+p.getCost());
		   System.out.println("Quantity:"+p.getStock());
		   System.out.println("Stock Status:"+p.isStatus());
		   
	   }
		   session.close();
		   sf.close();
	   
	   
	   
	 
	 
 }
 public void displayallproductpartialobject()//partial object,query we used is select
 //here we take object[] because we dont'no the type of object so we use this
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   String hql=" select p.id,p.name,p.cost from Product p ";
	   // p is a reference object or allias;
	   
	   Query<Object[]> qry = session.createQuery(hql, Object[].class);
	   List<Object[]> productlist = qry.getResultList();
	   
	   System.out.println("Total Products="+productlist.size());
	   for(Object[] obj:productlist)
	   {
		   System.out.println("Product ID="+obj[0]);
		   System.out.println("Product Name="+obj[1]);
		   System.out.println("Product Cost="+obj[2]);
	   }
	   session.close();
	   sf.close();
	   
	   
	   
	 
 }
 
 public void aggregatefunctions()//multi row function called as aggregate 
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   String hql1="select count(*) from Product";
	   //you can also use count(property)
	   Query<Long>qry1=session.createQuery(hql1, long.class);
	   Long count=qry1.getSingleResult();
	   System.out.println("Total Products="+count);
	   
	   
	   String hql2="select sum(cost) from Product";
	   //you can also use count(property)
	   Query<Double>qry2=session.createQuery(hql2, Double.class);
	   double totalcost=qry2.getSingleResult();
	   System.out.println("Total cost="+totalcost);
	   
	   String hql3="select avg(cost) from Product";
	   Query<Double>qry3=session.createQuery(hql3, Double.class);
	   double avgcost=qry3.getSingleResult();
	   System.out.println("Average costs="+avgcost);
	   
	   String hql4="select min(stock) from Product";
	   //you can also use count(property)
	   Query<Integer>qry4=session.createQuery(hql4, Integer.class);
	   int minstock =qry4.getSingleResult();
	   System.out.println("Minimum stock="+minstock);
	   
	   String hql5="select max(stock) from Product";
	   //you can also use count(property)
	   Query<Integer>qry5=session.createQuery(hql5, Integer.class);
	   int maxstock =qry5.getSingleResult();
	   System.out.println("Maximum stock="+maxstock);
	   
	   session.close();
	   sf.close();
	   
	   
	   
	 
 }
 public void updatepositionalparams()
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   Transaction t = session.beginTransaction();//based on operations 
	   
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Product id");
	   int pid=sc.nextInt();
	   System.out.println("Enter Product name:");
	  String pname=sc.next();
	  System.out.println("Enter product cost:");
	  double pcost=sc.nextDouble();
	  
	  String hql="update Product set name=?1,cost=?2 where id=?3";
	 MutationQuery qry= session.createMutationQuery(hql);
	 qry.setParameter(1, pname);
	 qry.setParameter(2,pcost );
	 qry.setParameter(3, pid);
	 int n=qry.executeUpdate();
	 
	 System.out.println(n+"Product(s) Updated Succesfully");
	 
	 sc.close();
	 session.close();
	 sf.close();
	 
	   
	 
 }
 public void deletepositionalparams()
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   Transaction t = session.beginTransaction();//based on operations 
	   
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Product id");
	   int pid=sc.nextInt();
	   
	  
	  String hql="delete from  Product where id=?1";
	 MutationQuery qry= session.createMutationQuery(hql);
	 qry.setParameter(1, pid);
	 int n=qry.executeUpdate();
	 t.commit();
	 if(n>0)
	 {
		 System.out.println("Product Deleted succesfully");
	 }
	 else
	 {
		 System.out.println("Product ID Not Found");
	 }
	 
	
	 
	 sc.close();
	 session.close();
	 sf.close();
	 
	 
	 
	 
	 
 }
 public void updatenamedparams()
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   Transaction t = session.beginTransaction();//based on operations 
	   
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Product id");
	   int pid=sc.nextInt();
	   System.out.println("Enter Product name:");
	  String pname=sc.next();
	  System.out.println("Enter product cost:");
	  double pcost=sc.nextDouble();
	  
	  String hql="update Product set name=:v1,cost=:v2 where id=:v3";
	 MutationQuery qry= session.createMutationQuery(hql);
	 qry.setParameter("v1", pname);
	 qry.setParameter("v2",pcost );
	 qry.setParameter("v3", pid);
	 int n=qry.executeUpdate();
	 
	 System.out.println(n+"Product(s) Updated Succesfully");
	 
	 sc.close();
	 session.close();
	 sf.close();
	 
	   
 }
 public void deletenamedparams()
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   
	   Transaction t = session.beginTransaction();//based on operations 
	   
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Product id");
	   int pid=sc.nextInt();
	   
	  
	  String hql="delete from  Product where id=:v";
	 MutationQuery qry= session.createMutationQuery(hql);
	 qry.setParameter("v", pid);
	 int n=qry.executeUpdate();
	 t.commit();
	 if(n>0)
	 {
		 System.out.println("Product Deleted succesfully");
	 }
	 else
	 {
		 System.out.println("Product ID Not Found");
	 }
	 
	
	 
	 sc.close();
	 session.close();
	 sf.close();
	 
 }
 //display product by id using positional params 
 public void displayproductbyidpositionalparms()
 {
	 Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Product ID: ");
	    int pid=sc.nextInt();
	   String hql = " from Product where id=?1";//hql query
	   Query<Product> qry = session.createQuery(hql, Product.class) ;
	   qry.setParameter(1, pid);
	   
	  Product p = qry.getSingleResultOrNull();
	  if(p!=null)
	  {
		  //we can use getter methods to print every property of product object(p)
		  System.out.println(p.toString());//this 
		  
	  }
	  else
	  {
		  System.out.println("Product ID Not Found");
	  }
	  sc.close();
	 session.close();
 }
public void displayproductbyidnamedparams()
{
	Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Product ID: ");
	    int pid=sc.nextInt();
	   String hql = " from Product where id=:v";//hql query
	   Query<Product> qry = session.createQuery(hql, Product.class) ;
	   qry.setParameter("v", pid);
	   
	  Product p = qry.getSingleResultOrNull();
	  if(p!=null)
	  {
		  //we can use getter methods to print every property of product object(p)
		  System.out.println(p.toString());//this 
		  
	  }
	  else
	  {
		  System.out.println("Product ID Not Found");
	  }
	  sc.close();
	 session.close();
}
//display products based on category and stock
//category = list
//unique property means object
// non-unique property is List
public void displayproducts()
{
	Configuration configuration = new Configuration();//to configure the xml file
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();//no of databasee depend on by sessionfactory
	   Session session = sf.openSession();//to establish connection
	   Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Product 	Category: ");
	    String pcategory=sc.next();
	    System.out.println("Enter product stock:");
	    int pstock = sc.nextInt();
	    
	   
	   String hql = " from Product where category=?1 and stock>=?2 ";//we use from only in hql because it is completeparam
	   
	   Query<Product> qry = session.createQuery(hql, Product.class) ;
	   qry.setParameter(1, pcategory);
	   qry.setParameter(2, pstock);
	   List<Product> productlist = qry.getResultList();//now the product size is 3 because we have data we have is 3 products
	   System.out.println("Total Products="+productlist.size());
	   
	   for(Product p :productlist)//the for loop run n times 
	   {
		   System.out.println("id:"+p.getId());
		   System.out.println("Category:"+p.getCategory());
		   System.out.println("Name:"+p.getName());
		   System.out.println("Cost:"+p.getCost());
		   System.out.println("Quantity:"+p.getStock());
		   System.out.println("Stock Status:"+p.isStatus());
		   
	   }
		   session.close();
		   sf.close();
	   
}
//pagination
public void paginationdemo()
{
  Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
      
      SessionFactory sf = configuration.buildSessionFactory();
      Session session = sf.openSession();
      
      String hql = "from Product"; // select * from product_table
      
      Query<Product> qry = session.createQuery(hql, Product.class);
      qry.setFirstResult(1);
      qry.setMaxResults(3);
      List<Product> productlist =  qry.getResultList();
      
      System.out.println("Total Products="+productlist.size());
      
       for( Product p : productlist) 
       {
         System.out.println(p.toString());
       }
       
       session.close();
       sf.close();
}

 
}
