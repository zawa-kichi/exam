package bean;

public class Teacher implements java.io.Serializable{
	private String id;
	private String password;
	private String name;
	private School school;

	public String getId(){
		return getId();
	}
	public void setId(String id){
		this.id = id;
	}
	public String getPassword(){
		return getPassword();
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getName(){
		return getName();
	}
	public void setName(String name){
		this.name = name;
	}
	public School getSchool(){
		return getSchool();
	}
	public void setSchool(School school){
		this.school = school;
	}
}