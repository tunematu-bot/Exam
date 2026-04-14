package scoremanager.main;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // --- 1. ログインチェック ---
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            response.sendRedirect(request.getContextPath() + "/scoremanager/login.jsp");
            return;
        }

        // --- 2. school_cd を Teacher → School → cd から取得 ---
        String schoolCd = teacher.getSchool().getCd();

        // JSPに出さないなら、ここは不要（保険で残してもOK）
        request.setAttribute("school_cd", schoolCd);

        // --- 3. JSPへフォワード ---
        request.getRequestDispatcher("subject_create.jsp")
               .forward(request, response);
    }
}