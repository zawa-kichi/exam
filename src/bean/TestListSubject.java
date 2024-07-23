package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {
	/*
	 * ent_year:入学年度
	 * integer(10), value = null
	 */
	private int entYear;
	public int getEntYear() {
		System.out.println("取得入学年度:'"+this.entYear+"'");
		return this.entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
		System.out.println("設定入学年度:'"+this.entYear+"'");
	}
	/*
	 * student_no:学生番号
	 * varchar(10), primary key, not null
	 */
	private String studentNo;
	public String getStudentNo() {
		System.out.println("取得学生番号:'"+this.studentNo+"'");
		return this.studentNo;
	}
	public void setStudentNo(String no) {
		this.studentNo = no;
		System.out.println("設定学生番号:'"+this.studentNo+"'");
	}
	/*
	 * student_name:学生名
	 * varchar(10), value = null
	 */
	private String studentName;
	public String getStudentName() {
		System.out.println("取得学生名:'"+this.studentName+"'");
		return this.studentName;
	}
	public void setStudentName(String name) {
		this.studentName = name;
		System.out.println("設定学生名:'"+this.studentName+"'");
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
	/*
	 * no:回数
	 * integer(10), primary key, not null
	 * point:得点
	 * intger(10), value = null
	 */
	private Map<Integer, Integer> points;
	public Map<Integer, Integer> getPoints() {
		for (Integer key : this.points.keySet()) {
			System.out.println("第"+key+"回得点:'"+this.points.get(key)+"'");
		}
		return this.points;
	}
	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
		for (Integer key : this.points.keySet()) {
			System.out.println("第"+key+"回得点:'"+this.points.get(key)+"'");
		}
	}
	public String getPoint(int key) throws Exception{
		String point;
		try {
			point = String.valueOf(this.points.get(key));
		} catch (Exception e) {
			 point = "-";
		}
		System.out.println("第"+key+"回得点:'"+point+"'");
		return point;
	}
	public void putPoint(int key, int value){
		this.points.put(key, value);
		System.out.println("第"+key+"回得点:'"+value+"'");
	}
}