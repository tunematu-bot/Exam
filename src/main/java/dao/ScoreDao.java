package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Score;

public class ScoreDao extends Dao {

	public List<Score> search(
		    String year,
		    String classNum,
		    String subjectCd,
		    String times,
		    String schoolCd
		) throws Exception {

        List<Score> list = new ArrayList<>();

        String sql =
        		"SELECT" +
        		  "s.ENTRANCE_YEAR," +
        		  "t.CLASS_NUM," +
        		  "s.STUDENT_NO," +
        		  "s.NAME," +
        		  "t.POINT" +
        		"FROM TEST t" +
        		"JOIN STUDENT s" +
        		  "ON t.STUDENT_NO = s.NO" +
        		 "AND t.SCHOOL_CD = s.SCHOOL_CD" +
        		"WHERE" +
        		    "s.ENTRANCE_YEAR = ?" +
        		"AND t.CLASS_NUM = ?" +
        		"AND t.SUBJECT_CD = ?" +
        		"AND t.NO = ?" +
        		"AND t.SCHOOL_CD = ?" +
        		"ORDER BY s.NO";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

        	ps.setString(1, year);
        	ps.setString(2, classNum);
        	ps.setString(3, subjectCd);
        	ps.setInt(4, Integer.parseInt(times));
        	ps.setString(5, schoolCd);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Score score = new Score();
                score.setYear(rs.getString("ENTRANCE_YEAR"));
                score.setClassNum(rs.getString("CLASS_NUM"));
                score.setStudentNo(rs.getString("STUDENT_NO"));
                score.setStudentName(rs.getString("STUDENT_NAME"));

                int point = rs.getInt("POINT");
                if (rs.wasNull()) {
                    score.setPoint(null);
                } else {
                    score.setPoint(point);
                }

                list.add(score);
            }
        }

        return list;
    }
}