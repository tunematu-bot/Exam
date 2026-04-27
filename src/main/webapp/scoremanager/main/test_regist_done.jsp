<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<<<<<<< HEAD
=======
<c:set var="content">
    <!-- ① 画面タイトル -->
    <div class="bg-light p-3 mb-3 fw-bold">
        成績管理
    </div>

    <!-- ② 登録完了メッセージ（緑帯） -->
    <div class="alert alert-success py-2 mb-4">
        登録が完了しました。
    </div>

    <!-- ③・④ 画面下リンク -->
    <div>
        <a href="test_regist.jsp" class="me-4">戻る</a>
        <a href="test_list.jsp">成績参照</a>
    </div>
</c:set>

>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
<c:import url="/common/base.jsp">
<<<<<<< HEAD
    <c:param name="title">学生成績一覧</c:param>

    <c:param name="content">

        <section class="container mt-4">

            <!-- タイトル -->
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績一覧（学生）
            </h2>

            <div class="card">
                <div class="card-body">

                    <!-- データなし -->
                    <c:if test="${empty list}">
                        <div class="alert alert-warning">
                            該当する学生情報がありません
                        </div>
                    </c:if>

                    <!-- 一覧表示 -->
                    <c:if test="${not empty list}">
                        <table class="table table-bordered table-striped">

                            <thead class="table-light">
                                <tr>
                                    <th>学生番号</th>
                                    <th>学生名</th>
                                    <th>科目</th>
                                    <th>回数</th>
                                    <th>得点</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="test" items="${list}">
                                    <tr>
                                        <td>${test.student.no}</td>
                                        <td>${test.student.name}</td>
                                        <td>${test.subject.name}</td>
                                        <td>${test.no}</td>
                                        <td>${test.point}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>
                    </c:if>

                </div>
            </div>

        </section>

=======
    <c:param name="title" value="成績管理" />
    <c:param name="content">
        <c:out value="${content}" escapeXml="false"/>
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
    </c:param>
</c:import>