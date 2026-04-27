package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    private String baseSql = "select * from student where school_cd=?";

    // ---------------------------------------------------------
    // 単体取得
    // ---------------------------------------------------------
    public Student get(String no) throws Exception {

        Student student = null;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select * from student where no=?")) {

            ps.setString(1, no);
            ResultSet rs = ps.executeQuery();

            SchoolDao schoolDao = new SchoolDao();

            if (rs.next()) {
                student = new Student();
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                student.setEntYear(rs.getInt("ent_year"));
                student.setClassNum(rs.getString("class_num"));
                student.setAttend(rs.getBoolean("is_attend"));
                student.setSchool(schoolDao.get(rs.getString("school_cd")));
            }
        }

        return student;
    }

    // ---------------------------------------------------------
    // 共通：ResultSet → List<Student>
    // ---------------------------------------------------------
    private List<Student> postFilter(ResultSet rs, School school) throws Exception {

        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            Student s = new Student();
            s.setNo(rs.getString("no"));
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            s.setAttend(rs.getBoolean("is_attend"));
            s.setSchool(school);
            list.add(s);
        }

        return list;
    }

    // ---------------------------------------------------------
    // フィルタ（年度 + クラス）
    // ---------------------------------------------------------
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

        List<Student> list = new ArrayList<>();

        String sql = baseSql +
                " and ent_year=? and class_num=?"
                + (isAttend ? " and is_attend=true" : "")
                + " order by no asc";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);

            ResultSet rs = ps.executeQuery();
            list = postFilter(rs, school);
        }

        return list;
    }

    // ---------------------------------------------------------
    // フィルタ（年度のみ）
    // ---------------------------------------------------------
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

        List<Student> list = new ArrayList<>();

        String sql = baseSql +
                " and ent_year=?"
                + (isAttend ? " and is_attend=true" : "")
                + " order by no asc";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);

            ResultSet rs = ps.executeQuery();
            list = postFilter(rs, school);
        }

        return list;
    }

    // ---------------------------------------------------------
    // フィルタ（学校のみ）
    // ---------------------------------------------------------
    public List<Student> filter(School school, boolean isAttend) throws Exception {

        List<Student> list = new ArrayList<>();

        String sql = baseSql +
                (isAttend ? " and is_attend=true" : "")
                + " order by no asc";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());

            ResultSet rs = ps.executeQuery();
            list = postFilter(rs, school);
        }

        return list;
    }

    // ---------------------------------------------------------
    // 保存（INSERT / UPDATE）
    // ---------------------------------------------------------
    public boolean save(Student student) throws Exception {

        int count = 0;

        try (Connection con = getConnection()) {

            Student old = get(student.getNo());
            PreparedStatement ps;

            if (old == null) {
                ps = con.prepareStatement(
                        "insert into student(no, name, ent_year, class_num, is_attend, school_cd) "
                                + "values(?, ?, ?, ?, ?, ?)");
                ps.setString(1, student.getNo());
                ps.setString(2, student.getName());
                ps.setInt(3, student.getEntYear());
                ps.setString(4, student.getClassNum());
                ps.setBoolean(5, student.isAttend());
                ps.setString(6, student.getSchool().getCd());

            } else {
                ps = con.prepareStatement(
                        "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
                ps.setString(1, student.getName());
                ps.setInt(2, student.getEntYear());
                ps.setString(3, student.getClassNum());
                ps.setBoolean(4, student.isAttend());
                ps.setString(5, student.getNo());
            }

            count = ps.executeUpdate();
            ps.close();
        }

        return count > 0;
    }

    // ---------------------------------------------------------
    // 入学年度一覧
    // ---------------------------------------------------------
    public List<Integer> getYearList() throws Exception {

        List<Integer> list = new ArrayList<>();

        String sql = "select distinct ent_year from student order by ent_year desc";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("ent_year"));
            }
        }

        return list;
    }
}
