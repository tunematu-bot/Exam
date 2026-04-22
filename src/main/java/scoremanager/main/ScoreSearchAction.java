package scoremanager.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Score;
import dao.ScoreDao;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ScoreSearch.action")
public class ScoreSearchAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            // ① 初期表示用マスタ取得（常に実行）
            setCommonMaster(request);

            // ② 検索条件取得
            String year      = request.getParameter("year");
            String classNum  = request.getParameter("classNum");
            String subjectCd = request.getParameter("subjectCd");
            String times     = request.getParameter("times");

            // ③ 検索条件が揃っている場合のみ検索
            if (isNotEmpty(year, classNum, subjectCd, times)) {

                // ★ 学校コードを session から取得
                HttpSession session = request.getSession();
                School school = (School) session.getAttribute("school");

                if (school == null) {
                    throw new ServletException("学校情報がセッションに存在しません");
                }

                String schoolCd = school.getCd();

                // ④ 検索（schoolCd を渡す）
                ScoreDao dao = new ScoreDao();
                List<Score> studentList =
                        dao.search(year, classNum, subjectCd, times, schoolCd);

                request.setAttribute("studentList", studentList);

                // ⑤ 表示用データ
                request.setAttribute(
                        "subjectName",
                        new SubjectDao().findName(subjectCd)
                );
                request.setAttribute("times", times);
            }

            // ⑥ JSPへフォワード
            request.getRequestDispatcher("test_register.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            // デバッグ時は一時的に有効化してOK
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    /**
     * 検索条件プルダウン用マスタ設定
     */
    private void setCommonMaster(HttpServletRequest request) throws Exception {

        // 入学年度：過去10年
        List<Integer> yearList = new ArrayList<>();
        int currentYear = java.time.Year.now().getValue();
        for (int i = 0; i < 10; i++) {
            yearList.add(currentYear - i);
        }
        request.setAttribute("yearList", yearList);

        // クラス番号（DB）
        request.setAttribute(
            "classList",
            new dao.ClassNumDao().getClassList()
        );

        // 科目（DB）
        request.setAttribute(
            "subjectList",
            new dao.SubjectDao().getSubjectList()
        );

        // 回数（固定）
        request.setAttribute(
            "timesList",
            List.of(1, 2, 3, 4)
        );
    }

    /**
     * 全パラメータ未入力チェック
     */
    private boolean isNotEmpty(String... values) {
        for (String v : values) {
            if (v == null || v.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}