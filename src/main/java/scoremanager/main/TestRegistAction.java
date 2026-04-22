package scoremanager.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestRegistAction")
public class TestRegistAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        List<Integer> yearList = new ArrayList<>();

        int currentYear = LocalDate.now().getYear();
        for (int i = 0; i < 10; i++) {
            yearList.add(currentYear - i);
        }
        request.setAttribute("yearList", yearList);

        try {
            String[] studentNos = request.getParameterValues("studentNo");
            String[] points = request.getParameterValues("point");

            String subjectCd = request.getParameter("subjectCd");
            int no = Integer.parseInt(request.getParameter("no"));
            String classNum = request.getParameter("classNum");

            School school = (School) request.getSession().getAttribute("school");

            // ----------------------------
            // エラー・入力保持用
            // ----------------------------
            Map<String, String> errorMap = new HashMap<>();
            Map<String, String> inputPointMap = new HashMap<>();

            // ----------------------------
            // 点数チェック
            // ----------------------------
            for (int i = 0; i < studentNos.length; i++) {

                String p = points[i];
                inputPointMap.put(studentNos[i], p);

                if (p == null || p.isEmpty()) {
                    continue;
                }

                int point = Integer.parseInt(p);
                if (point < 0 || point > 100) {
                    errorMap.put(
                        studentNos[i],
                        "0～100の範囲で入力してください"
                    );
                }
            }

            // ----------------------------
            // エラーがあれば戻す
            // ----------------------------
            if (!errorMap.isEmpty()) {
                request.setAttribute("errorMap", errorMap);
                request.setAttribute("inputPointMap", inputPointMap);

                // 再表示に必要な情報は検索時と同じ
                request.getRequestDispatcher("test_regist.jsp")
                       .forward(request, response);
                return;
            }

            // ----------------------------
            // 正常登録処理
            // ----------------------------
            StudentDao studentDao = new StudentDao();
            SubjectDao subjectDao = new SubjectDao();
            TestDao testDao = new TestDao();

            Subject subject = subjectDao.get(subjectCd);
            List<Test> list = new ArrayList<>();

            for (int i = 0; i < studentNos.length; i++) {
                if (points[i] == null || points[i].isEmpty()) continue;

                Test test = new Test();
                Student student = studentDao.get(studentNos[i]);

                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setClassNum(classNum);
                test.setNo(no);
                test.setPoint(Integer.parseInt(points[i]));

                list.add(test);
            }

            testDao.save(list);

            response.sendRedirect("TestRegistComplete.action");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}