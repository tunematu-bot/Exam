package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestRegistDao extends Dao {

    /**
     * 成績（点数）を更新
     *
     * @param studentNo 学生番号
     * @param subjectCd 科目コード
     * @param schoolCd  学校コード
     * @param times     回数
     * @param point     点数（null可）
     */
    public void updatePoint(
            String studentNo,
            String subjectCd,
            String schoolCd,
            int times,
            Integer point
    ) throws Exception {

        String sql =
            "update test set point = ? " +
            "where student_no = ? " +
            "and subject_cd = ? " +
            "and school_cd = ? " +
            "and no = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            if (point == null) {
                ps.setNull(1, java.sql.Types.INTEGER);
            } else {
                ps.setInt(1, point);
            }

            ps.setString(2, studentNo);
            ps.setString(3, subjectCd);
            ps.setString(4, schoolCd);
            ps.setInt(5, times);

            ps.executeUpdate();
        }
    }
}