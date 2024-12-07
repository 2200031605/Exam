package GeneratorClassDemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_table")
public class Employee 
{ 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="eid")
	private int id;
	@Column(name="ename",length=50,nullable=false)
	private String name;
	@Column(name="egender",length=10,nullable=false)
	private String gender;
	@Column(name="econtact",length=20,nullable=false,unique =true )
	private String contact;
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
	public String getContact()
	{
		return contact;
	}
	public void setContact(String contact)
	{
		this.contact = contact;
	}
	

}
