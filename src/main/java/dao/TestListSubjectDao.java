package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao {

    private String baseSql;

    public List<TestListSubject> postFilter(ResultSet rSet) throws SQLException {
        // TODO: ResultSet → List<TestListSubject> に変換
        // while (rSet.next()) { ... }
        return null;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) {
        // TODO:
        // baseSql を元に検索条件を組み立てる
        // entYear, classNum, subject, school を WHERE に反映
        // DB から取得 → postFilter に渡す
        return null;
    }
}
