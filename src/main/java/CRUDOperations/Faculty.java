package CRUDOperations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty_table")
public class Faculty 
{
	@Id
	@Column(name="fid")
	private int id;
	@Column(name="fname",length=50,nullable = false)
	private String name;
	@Column(name="fgender",length=10,nullable=false)
	private String gender;
	@Column(name="fdepartment",length=50,nullable=false)
	private String department;
	@Column(name="fsalary",nullable=false)
	private double salary;
	@Column(name="fcontactno",length=20,nullable=false,unique=true)
	private String contactno;
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName() 
	
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getGender() 
	{
		return gender;
	}
	public void setGender(String gender) 
	{
		this.gender = gender;
	}
	public String getDepartment() 
	{
		return department;
	}
	public void setDepartment(String department) 
	{
		this.department = department;
	}
	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary) 
	{
		this.salary = salary;
	}
	public String getContactno() 
	{
		return contactno;
	}
	public void setContactno(String contactno)
	{
		this.contactno = contactno;
	}
    
}
