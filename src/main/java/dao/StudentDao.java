package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    /**
     * 1件取得（学生番号のみ）
     */
    public Student get(String no) throws Exception {

        String sql = "select * from student where student_no=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, no);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setNo(rs.getString("student_no"));
                student.setName(rs.getString("name"));
                student.setEntYear(rs.getInt("ent_year"));
                student.setClassNum(rs.getString("class_num"));
                student.setAttend(rs.getBoolean("is_attend"));
                // School は呼び出し側でセットする
                return student;
            }
        }

        return null;
    }

    /**
     * filter(学校, 入学年度, クラス, 在学)
     */
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

        String sql = "select * from student where school_cd=? and ent_year=? and class_num=? and is_attend=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setBoolean(4, isAttend);

            return postFilter(ps.executeQuery(), school);
        }
    }

    /**
     * filter(学校, 入学年度, 在学)
     */
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

        String sql = "select * from student where school_cd=? and ent_year=? and is_attend=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setBoolean(3, isAttend);

            return postFilter(ps.executeQuery(), school);
        }
    }

    /**
     * filter(学校, 在学)
     */
    public List<Student> filter(School school, boolean isAttend) throws Exception {

        String sql = "select * from student where school_cd=? and is_attend=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setBoolean(2, isAttend);

            return postFilter(ps.executeQuery(), school);
        }
    }

    /**
     * ResultSet → List<Student>
     */
    private List<Student> postFilter(ResultSet rs, School school) throws Exception {

        List<Student> list = new ArrayList<>();

        while (rs.next()) {

            Student student = new Student();

            student.setNo(rs.getString("student_no"));
            student.setName(rs.getString("name"));
            student.setEntYear(rs.getInt("ent_year"));
            student.setClassNum(rs.getString("class_num"));
            student.setAttend(rs.getBoolean("is_attend"));
            student.setSchool(school);

            list.add(student);
        }

        return list;
    }

    /**
     * save（1件保存）
     */
    public boolean save(Student student) throws Exception {

        String sql = "insert into student(student_no, name, ent_year, class_num, is_attend, school_cd) "
                   + "values(?,?,?,?,?,?) "
                   + "on duplicate key update "
                   + "name=?, ent_year=?, class_num=?, is_attend=?, school_cd=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // insert
            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getClassNum());
            ps.setBoolean(5, student.isAttend());
            ps.setString(6, student.getSchool().getCd());

            // update
            ps.setString(7, student.getName());
            ps.setInt(8, student.getEntYear());
            ps.setString(9, student.getClassNum());
            ps.setBoolean(10, student.isAttend());
            ps.setString(11, student.getSchool().getCd());

            return ps.executeUpdate() > 0;
        }
    }
}
