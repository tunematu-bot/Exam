package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    /**
     * ベースSQL : String
     */
    private String baseSql = "select * from test where school_cd=? ";

    /**
     * get
     */
    public Test get(String studentNo, String subjectCd, School school, int no) throws Exception {

        String sql = baseSql + "and student_no=? and subject_cd=? and no=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setString(2, studentNo);
            ps.setString(3, subjectCd);
            ps.setInt(4, no);

            ResultSet rs = ps.executeQuery();
            List<Test> list = postFilter(rs, school);

            return list.isEmpty() ? null : list.get(0);
        }
    }

    /**
     * postFilter
     */
    public List<Test> postFilter(ResultSet rSet, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        while (rSet.next()) {

            Test test = new Test();

            // student
            Student student = studentDao.get(rSet.getString("student_no"));
            test.setStudent(student);

            // classNum
            test.setClassNum(rSet.getString("class_num"));

            // subject
            Subject subject = subjectDao.get(rSet.getString("subject_cd"));
            test.setSubject(subject);

            // school
            test.setSchool(school);

            // no
            test.setNo(rSet.getInt("no"));

            // point
            test.setPoint(rSet.getInt("point"));

            list.add(test);
        }

        return list;
    }

    /**
     * filter
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        StringBuilder sql = new StringBuilder(baseSql);

        if (entYear != 0) {
            sql.append("and ent_year=").append(entYear).append(" ");
        }
        if (classNum != null && !classNum.isEmpty()) {
            sql.append("and class_num='").append(classNum).append("' ");
        }
        if (subject != null) {
            sql.append("and subject_cd='").append(subject.getCd()).append("' ");
        }
        if (num != 0) {
            sql.append("and no=").append(num).append(" ");
        }

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            ps.setString(1, school.getCd());
            ResultSet rs = ps.executeQuery();

            return postFilter(rs, school);
        }
    }

    /**
     * save (List)
     */
    public boolean save(List<Test> list) throws Exception {

        try (Connection con = getConnection()) {
            for (Test test : list) {
                if (!save(test, con)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * save (Test + Connection)
     */
    public boolean save(Test test, Connection connection) throws Exception {

        String sql = "insert into test(student_no, class_num, subject_cd, school_cd, no, point) "
                   + "values(?,?,?,?,?,?) "
                   + "on duplicate key update point=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, test.getStudent().getNo());
            ps.setString(2, test.getClassNum());
            ps.setString(3, test.getSubject().getCd());
            ps.setString(4, test.getSchool().getCd());
            ps.setInt(5, test.getNo());
            ps.setInt(6, test.getPoint());

            // update 用
            ps.setInt(7, test.getPoint());

            return ps.executeUpdate() > 0;
        }
    }
    
    public List<Test> get(String studentNo, School school) throws Exception {

        String sql = baseSql + "and student_no=? order by no asc";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setString(2, studentNo);

            ResultSet rs = ps.executeQuery();
            return postFilter(rs, school);
        }
    }
    
    public List<Test> findBySchool(School school) throws Exception {
        List<Test> list = new ArrayList<>();

        // SQLを書く（school_cdで検索）

        return list;
    }

}
