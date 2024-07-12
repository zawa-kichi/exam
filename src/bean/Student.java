package bean;

import java.io.Serializable;

public class Student implements Serializable{
	/*
	 * no:学生番号
	 * varchar(10), primary key, not null
	 */
	private String no;
	public String getNo() {return this.no;}
	public void setNo(String no) {this.no = no;}
	/*
	 * name:学生名
	 * varchar(10), value = null
	 */
	private String name;
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	/*
	 * ent_year:入学年度
	 * integer(10), value = null
	 */
	private int entYear;
	public int getEntYear() {return this.entYear;}
	public void setEntYear(int entYear) {this.entYear = entYear;}
	/*
	 * class_num:クラス番号
	 * char(3), value = null
	 */
	private String classNum;
	public String getClassNum() {return this.classNum;}
	public void setClassNum(String classNum) {this.classNum = classNum;}
	/*
	 * is_attend:在学中フラグ
	 * boolean, value = null
	 */
	private boolean isAttend;
	public boolean isAttend() {return this.isAttend;}
	public void setAttend(boolean isAttend) {this.isAttend = isAttend;}
	/*
	 * school_cd:学校コード
	 * char(3), value = null
	 */
	private School school;
	public School getSchool() {return this.school;}
	public void setSchool(School school) {this.school = school;}
	public int getSchoolYear() {return getSchoolYear();}
}