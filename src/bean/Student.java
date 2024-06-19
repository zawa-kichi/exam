package bean;

public class Student implements java.io.Serializable{
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean IsAttend;
	private School school;

	public String getNo(){
		return getNo();
	}
	public void setNo(String no){
		this.no = no;
	}
	public String getName(){
		return getName();
	}
	public void setName(String name){
		this.name = name;
	}
	public int getEntYear(){
		return getEntYear();
	}
	public void setEntYear(int entYear){
		this.entYear = entYear;
	}
	public String getClassNum(){
		return getClassNum();
	}
	public void setClassNum(String classNum){
		this.classNum = classNum;
	}
	public boolean IsAttend(){
		return IsAttend();
	}
	public void setAttend(boolean IsAttend){
		this.IsAttend = IsAttend;
	}
	public School getSchool(){
		return getSchool();
	}
	public void setSchool(School school){
		this.school = school;
	}
	public int getSchoolYear(){
		return getSchoolYear();
	}
}
