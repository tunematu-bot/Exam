package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            teacher = new Teacher();
            School school = new School();
            school.setCd("oom"); // DBの学校コードと一致さwせる
            teacher.setSchool(school);
            session.setAttribute("user", teacher);
        }

        SubjectDao subjectDao = new SubjectDao();
        List<bean.Subject> subjects = new ArrayList<>();
        try {
            subjects = subjectDao.filter(teacher.getSchool());
        } catch (Exception e) {
            System.out.println("★科目一覧の取得に失敗: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("★デバッグ: 科目取得件数 -> " + subjects.size());

        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}