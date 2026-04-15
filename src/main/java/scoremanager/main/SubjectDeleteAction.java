package scoremanager.main;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ログインチェック
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            response.sendRedirect(request.getContextPath() + "/scoremanager/login.jsp");
            return;
        }

        // パラメータ取得
        String cd = request.getParameter("cd");

        // 削除処理
        SubjectDao dao = new SubjectDao();
        dao.delete(cd, teacher.getSchool());

        // 一覧に戻る
        response.sendRedirect("SubjectList.action");
    }
}
