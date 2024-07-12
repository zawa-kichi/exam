package bean;

public class School implements java.io.Serializable{
	private String cd;
	private String name;
	private School school_cd;

	public String getCd(){
		return this.cd;
	}
	public void setCd(String cd){
		this.cd = cd;
	}
	public  String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public School getSchool_Cd(){
		return school_cd;
	}
	public void setSchool_Cd(School school_cd){
		this.school_cd = school_cd;
	}
}