package scoremanager.main;

import java.io.IOException;
import java.util.List;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            // ===============================
            // ★ ログインチェック（最重要）
            // ===============================
            HttpSession session = req.getSession(false);

            if (session == null ||
                session.getAttribute("user") == null) {

                res.sendRedirect(
                    req.getContextPath()
                    + "/scoremanager/main/Login.action");

                return;
            }

            Teacher teacher =
                (Teacher) session.getAttribute("user");

            School school = teacher.getSchool();

            // ===============================
            // 学生番号取得
            // ===============================
            String studentNo = req.getParameter("studentNo");

            if (studentNo != null) {
                studentNo = studentNo.trim();
            }

            TestDao dao = new TestDao();

            // ===============================
            // 学生検索あり
            // ===============================
            if (studentNo != null && !studentNo.isBlank()) {

                List<Test> list =
                        dao.get(studentNo, school);

                req.setAttribute("list", list);

                req.getRequestDispatcher(
                    "/scoremanager/main/test_list_student.jsp")
                    .forward(req, res);

                return;
            }

            // ===============================
            // 初期画面
            // ===============================
            req.getRequestDispatcher(
                "/scoremanager/main/test_list.jsp")
                .forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}