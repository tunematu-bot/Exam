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

        // --- 1. ログインチェック ---
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            response.sendRedirect(request.getContextPath() + "/scoremanager/login.jsp");
            return;
        }

        // --- 2. パラメータ取得 ---
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Map<String, String> errors = new HashMap<>();

        // --- 3. バリデーション ---
        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        // --- 4. エラー時は戻す ---
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);

            new SubjectEditAction().execute(request, response);
            return;
        }

        // --- 5. 更新処理 ---
        SubjectDao sDao = new SubjectDao();
        Subject subject = new Subject();

        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        int line = sDao.update(subject);

        if (line > 0) {
            request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
        } else {
            errors.put("system", "更新に失敗しました");
            request.setAttribute("errors", errors);
            new SubjectEditAction().execute(request, response);
        }
    }
}
