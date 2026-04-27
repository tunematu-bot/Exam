package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            response.sendRedirect(request.getContextPath() + "/scoremanager/login.jsp");
            return;
        }

        // GET → 編集画面表示
        if (request.getMethod().equals("GET")) {

            String cd = request.getParameter("cd");

            SubjectDao dao = new SubjectDao();
            Subject subject = dao.get(cd);

            request.setAttribute("subject", subject);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        // POST → 更新処理
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Map<String, String> errors = new HashMap<>();

        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);

            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            request.setAttribute("subject", subject);

            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        SubjectDao dao = new SubjectDao();
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        int line = dao.update(subject);

        if (line > 0) {
            response.sendRedirect("SubjectList.action");
        } else {
            errors.put("system", "更新に失敗しました");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
        }
    }
}
