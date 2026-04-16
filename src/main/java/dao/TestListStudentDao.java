package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao {

    private String baseSql;

    public List<TestListStudent> postFilter(ResultSet rSet) throws SQLException {
        // TODO: ResultSet を TestListStudent の List に変換
        // while (rSet.next()) { ... }
        return null;
    }

    public List<TestListStudent> filter(Student student) {
        // TODO: student を条件にして baseSql を組み立てて検索
        // DB から取得 → postFilter に渡す
        return null;
    }
}
