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

public class SubjectCreateExecuteAction extends Action {

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
        if (cd == null || cd.isEmpty()) {
            errors.put("cd", "科目コードを入力してください");
        }

        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        // --- 4. 重複チェック ---
        SubjectDao sDao = new SubjectDao();
        if (errors.isEmpty()) {
            Subject existing = sDao.get(cd);
            if (existing != null) {
                errors.put("cd", "科目コードが重複しています");
            }
        }

        // --- 5. エラー時は戻す ---
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);

            new SubjectCreateAction().execute(request, response);
            return;
        }

        // --- 6. 登録処理 ---
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        int line = sDao.insert(subject);

        if (line > 0) {
            request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
        } else {
            errors.put("system", "登録に失敗しました");
            request.setAttribute("errors", errors);
            new SubjectCreateAction().execute(request, response);
        }
    }
}
