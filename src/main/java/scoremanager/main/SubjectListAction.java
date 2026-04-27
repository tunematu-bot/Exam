package scoremanager.main;

import java.util.List;

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

        // ★ 修正ポイント：teacher が null ならログイン画面へ
        if (teacher == null) {
            response.sendRedirect(request.getContextPath() + "/scoremanager/login.jsp");
            return;
        }

        // 科目一覧取得
        SubjectDao subjectDao = new SubjectDao();
        List<bean.Subject> subjects = subjectDao.filter(teacher.getSchool());

        // デバッグ
        System.out.println("★科目取得件数 -> " + subjects.size());

        // JSP へ渡す
        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}
