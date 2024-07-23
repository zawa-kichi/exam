package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {
	/*
	 * subject_cd:科目コード
	 * char(3), primary key, not null
	 */
	private String subjectCd;
	public String getSubjectCd() {
		System.out.println("取得科目コード:'"+this.subjectCd+"'");
		return this.subjectCd;
	}
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
		System.out.println("設定科目コード:'"+this.subjectCd+"'");
	}
	/*
	 * subject_name:科目名
	 * varchar(20), value = null
	 */
	private String subjectName;
	public String getSubjectName() {
		System.out.println("取得科目名:'"+this.subjectName+"'");
		return this.subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
		System.out.println("設定科目名:'"+this.subjectName+"'");
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
		this.no = no;
		System.out.println("設定回数:'"+this.no+"'");
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
		this.point = point;
		System.out.println("設定得点:'"+this.point+"'");
	}
}