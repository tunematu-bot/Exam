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
     * ベースSQL
     */
    private String baseSql = "select * from student where school_cd=? ";

    /**
     * 1件取得
     */
    public Student get(String studentNo, School school) throws Exception {

        String sql = baseSql + "and student_no=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setString(2, studentNo);

            ResultSet rs = ps.executeQuery();
            List<Student> list = postFilter(rs, school);

            return list.isEmpty() ? null : list.get(0);
        }
    }

    /**
     * ResultSet → List<Student>
     */
    public List<Student> postFilter(ResultSet rSet, School school) throws Exception {

        List<Student> list = new ArrayList<>();

        while (rSet.next()) {

            Student student = new Student();

            student.setNo(rSet.getString("student_no"));
            student.setName(rSet.getString("name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));
            student.setSchool(school);

            list.add(student);
        }

        return list;
    }

    /**
     * 条件検索
     */
    public List<Student> filter(int entYear, String classNum, School school) throws Exception {

        StringBuilder sql = new StringBuilder(baseSql);
        List<Object> params = new ArrayList<>();

        params.add(school.getCd());

        if (entYear != 0) {
            sql.append("and ent_year=? ");
            params.add(entYear);
        }
        if (classNum != null && !classNum.isEmpty()) {
            sql.append("and class_num=? ");
            params.add(classNum);
        }

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            return postFilter(rs, school);
        }
    }

    /**
     * 複数保存
     */
    public boolean save(List<Student> list) throws Exception {

        try (Connection con = getConnection()) {
            for (Student student : list) {
                if (!save(student, con)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 1件保存
     */
    public boolean save(Student student, Connection con) throws Exception {

        String sql = "insert into student(student_no, name, ent_year, class_num, school_cd) "
                   + "values(?,?,?,?,?) "
                   + "on duplicate key update name=?, ent_year=?, class_num=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getClassNum());
            ps.setString(5, student.getSchool().getCd());

            // update 用
            ps.setString(6, student.getName());
            ps.setInt(7, student.getEntYear());
            ps.setString(8, student.getClassNum());

            return ps.executeUpdate() > 0;
        }
    }
}
