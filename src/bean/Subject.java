package bean;

import java.io.Serializable;

public class Subject implements Serializable {
	/*
	 * school_cd:学校コード
	 * char(3), primary key, not null
	 */
	private String cd;
	public String getCd() {return cd;}
	public void setCd(String cd) {this.cd = cd;}
	/*
	 * cd:科目コード
	 * char(3), primary key, not null
	 */
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	/*
	 * name:科目名
	 * varchar(20), value = null
	 */
	private School school;
	public School getSchool() {return school;}
	public void setSchool(School school) {this.school = school;}
}