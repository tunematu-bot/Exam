//package scoremanager.main;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import bean.Subject;
//import bean.Teacher;
//import dao.SubjectDao;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import tool.Action;
//
//public class SubjectCreateExecuteAction extends Action {
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        // --- 1. ログインチェック ---
//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("user");
//        if (teacher == null) {
//            response.sendRedirect(request.getContextPath() + "/scoremanager/login.jsp");
//            return;
//        }
//
//        // --- 2. リクエストパラメータ取得 ---
//        String cd = request.getParameter("cd");
//        String name = request.getParameter("name");
//
//        // --- 3. 入力チェック（バリデーション） ---
//        Map<String, String> errors = new HashMap<>();
//
//        if (cd == null || cd.isEmpty()) {
//            errors.put("cd", "科目コードを入力してください");
//        } else if (cd.length() != 3) {
//            errors.put("cd", "科目コードは3桁で入力してください");
//        }
//
//        if (name == null || name.isEmpty()) {
//            errors.put("name", "科目名を入力してください");
//        }
//
//        // --- 4. 科目コード重複チェック（school_cd + cd） ---
//        SubjectDao sDao = new SubjectDao();
//        String schoolCd = teacher.getSchool().getCd();
//
//        if (errors.isEmpty()) {
//        	sDao.get(cd, teacher.getSchool());
//            if (existing != null) {
//                errors.put("cd", "科目コードが重複しています");
//            }
//        }
//
//        // --- 5. エラーがあれば登録画面に戻す ---
//        if (!errors.isEmpty()) {
//            request.setAttribute("errors", errors);
//            request.setAttribute("cd", cd);
//            request.setAttribute("name", name);
//
//            new SubjectCreateAction().execute(request, response);
//            return;
//        }
//
//        // --- 6. 科目情報を保存 ---
//        Subject subject = new Subject();
//        subject.setSchoolCd(schoolCd);
//        subject.setCd(cd);
//        subject.setName(name);
//
//        boolean success = sDao.save(subject);
//
//        if (success) {
//            // --- 7. 完了画面へ ---
//            request.getRequestDispatcher("subject_create_done.jsp")
//                   .forward(request, response);
//        } else {
//            errors.put("system", "登録に失敗しました");
//            request.setAttribute("errors", errors);
//            new SubjectCreateAction().execute(request, response);
//        }
//    }
//}
