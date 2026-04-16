package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectEditAction extends Action {

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

        // --- 3. 科目取得 ---
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(cd);

        // --- 4. 画面へセット ---
        request.setAttribute("subject", subject);

        // --- 5. 編集画面へ遷移 ---
        request.getRequestDispatcher("subject_edit.jsp").forward(request, response);
    }
}
