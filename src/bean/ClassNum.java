package bean;

public class ClassNum implements java.io.Serializable{
	private School school_cd;
	private int class_num;

	public School getSchool_Cd(){
		return school_cd;
	}
	public void setScool_Cd(School school_cd){
		this.school_cd = school_cd;
	}

	public int getClass_Num(){
		return class_num;
	}
	public void setClass_Num(int class_num){
		this.class_num = class_num;
	}
}
