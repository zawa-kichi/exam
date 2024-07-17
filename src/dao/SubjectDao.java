package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        String sql = "SELECT * FROM SUBJECT WHERE cd = ? AND school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            // パラメータを設定
            st.setString(1, cd);
            st.setString(2, school.getCd());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setCd(rs.getString("cd"));
                    subject.setName(rs.getString("name"));
                    subject.setSchool(school);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return subject;
    }

    public List<Subject> filter(School school) throws Exception {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT cd, name, school_cd FROM SUBJECT WHERE school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, school.getCd());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setCd(rs.getString("cd"));
                    subject.setName(rs.getString("name"));
                    subject.setSchool(school);
                    subjects.add(subject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return subjects;
    }

    public boolean save(Subject subject) throws Exception {
        int count = 0;
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement;

            Subject existingSubject = get(subject.getCd(), subject.getSchool());

            // 科目コード
            String cd = subject.getCd();
            // 科目名
            String name = subject.getName();
            // 学校コード
            String schoolCd = subject.getSchool().getCd();


            if (existingSubject == null) {
                // 新規挿入
                String insertSql = "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(insertSql);
                statement.setString(1, cd);
                statement.setString(2, name);
                statement.setString(3, schoolCd);
            } else {
                // 更新
                String updateSql = "UPDATE subject SET name = ? WHERE cd = ? AND school_cd = ?";
                statement = connection.prepareStatement(updateSql);
                statement.setString(1, name);
                statement.setString(2, cd);
                statement.setString(3, schoolCd);
            }

            count = statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return count > 0;
    }

    public boolean delete(Subject subject) throws Exception {
        int count = 0;
        String sql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";

        // 科目コード
        String cd = subject.getCd();
        // 科目名
        String schoolCd = subject.getSchool().getCd();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            System.out.println("Preparing statement for subject deletion");
            statement.setString(1, cd);
            statement.setString(2, schoolCd);
            System.out.println("Executing delete statement for subject: " + subject.getCd());
            count = statement.executeUpdate();
            System.out.println("Delete statement executed, affected rows: " + count);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return count > 0;
    }
}
