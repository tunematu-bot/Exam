package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * 1件取得（科目コードのみ）
     * TestDao や SubjectCreateExecuteAction から呼ばれる
     */
    public Subject get(String cd) throws Exception {

        String sql = "select * from subject where cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                // School は呼び出し側でセットする
                return subject;
            }
        }

        return null;
    }

    /**
     * 学校を指定して科目一覧を取得
     */
    public List<Subject> filter(School school) throws Exception {

        String sql = "select * from subject where school_cd = ? order by cd asc";

        List<Subject> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }
        }

        return list;
    }

    /**
     * insert
     */
    public int insert(Subject subject) throws Exception {

        String sql = "insert into subject(school_cd, cd, name) values(?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getSchool().getCd());
            ps.setString(2, subject.getCd());
            ps.setString(3, subject.getName());

            return ps.executeUpdate();
        }
    }

    /**
     * update
     */
    public int update(Subject subject) throws Exception {

        String sql = "update subject set name = ? where cd = ? and school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getName());
            ps.setString(2, subject.getCd());
            ps.setString(3, subject.getSchool().getCd());

            return ps.executeUpdate();
        }
    }

    /**
     * delete
     */
    public int delete(String cd, School school) throws Exception {

        String sql = "delete from subject where cd = ? and school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cd);
            ps.setString(2, school.getCd());

            return ps.executeUpdate();
        }
    }
    
    /**
     * 科目コードから科目名を取得（成績登録画面用）
     */
    public String findName(String subjectCd) throws Exception {

        String sql = "select name from subject where cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subjectCd);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        }

        return "";
    }
    
    /**
     * 科目一覧取得（検索条件プルダウン用）
     * 学校未指定で全件取得
     */
    public List<Subject> getSubjectList() throws Exception {

        List<Subject> list = new ArrayList<>();

        String sql = "select cd, name from subject order by cd asc";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                list.add(subject);
            }
        }

        return list;
    }
}
