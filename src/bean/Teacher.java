package bean;

public class Teacher implements java.io.Serializable{
	private String id;
	private String password;
	private String name;
	private School school;

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public School getSchool(){
		return school;
	}
	public void setSchool(School school){
		this.school = school;
	}
}