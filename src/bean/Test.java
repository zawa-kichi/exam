package bean;

import java.io.Serializable;

public class Test implements Serializable {
	/*
	 * student_no:学生番号
	 * varchar(10), primary key, not null
	 */
	private Student student;
	public Student getStudent() {
		System.out.println("取得学生情報:");
		this.student.getNo();
		this.student.getName();
		this.student.getEntYear();
		this.student.getClassNum();
		this.student.isAttend();
		this.student.getSchool();
		return this.student;
	}
	public void setStudent(Student student) {
		this.student = student;
		System.out.println("設定学生情報:");
		this.student.getNo();
		this.student.getName();
		this.student.getEntYear();
		this.student.getClassNum();
		this.student.isAttend();
		this.student.getSchool();
	}
	/*
	 * subject_cd:科目コード
	 * char(3), primary key, not null
	 */
	private Subject subject;
	public Subject getSubject() {
		System.out.println("取得科目情報:");
		this.subject.getSchool();
		this.subject.getCd();
		this.subject.getName();
		return this.subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
		System.out.println("設定科目情報:");
		this.subject.getSchool();
		this.subject.getCd();
		this.subject.getName();
	}
	/*
	 * school_cd:学校コード
	 * char(3), primary key, not null
	 */
	private School school;
	public School getSchool(){
		System.out.println("取得学校情報:");
		this.school.getCd();
		this.school.getName();
		return this.school;
	}
	public void setSchool(School school){
		this.school = school;
		System.out.println("設定学校情報:");
		this.school.getCd();
		this.school.getName();
	}
	/*
	 * no:回数
	 * integer(10), primary key, not null
	 */
	private int no;
	public int getNo() {
		System.out.println("取得回数:'"+this.no+"'");
		return this.no;
	}
	public void setNo(int no) {
		System.out.println("設定回数:'"+this.no+"'");
		this.no = no;
	}
	/*
	 * point:得点
	 * intger(10), value = null
	 */
	private int point;
	public int getPoint() {
		System.out.println("取得得点:'"+this.point+"'");
		return this.point;
	}
	public void setPoint(int point) {
		System.out.println("設定得点:'"+this.point+"'");
		this.point = point;
	}
	/*
	 * class_num:クラス番号
	 * varchar(5), value = null
	 */
	private String classNum;
	public String getClassNum() {
		System.out.println("取得クラス番号:'"+this.classNum+"'");
		return this.classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
		System.out.println("設定クラス番号:'"+this.classNum+"'");
	}
}