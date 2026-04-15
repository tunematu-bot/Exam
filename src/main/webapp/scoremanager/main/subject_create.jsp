







                               class="form-control"
                               class="form-control"
                               id="cd"
                               id="name"
                               maxlength="20"
                               maxlength="3"
                               name="cd"
                               name="name"
                               placeholder="科目コードを入力"
                               placeholder="科目名を入力"
                               required>
                               required>
                               value="${cd}"
                               value="${name}"
                            <li>${error.value}</li>
                            キャンセル
                        </a>
                        </c:forEach>
                        <a href="SubjectList.action" class="btn btn-secondary ms-2">
                        <button type="submit" class="btn btn-primary">登録</button>
                        <c:forEach var="error" items="${errors}">
                        <input type="text"
                        <input type="text"
                    </div>
                    </div>
                    </div>
                    </ul>
                    <div class="col-sm-6">
                    <div class="col-sm-6">
                    <div class="col-sm-9 offset-sm-3">
                    <label for="cd" class="col-sm-3 col-form-label">科目コード</label>
                    <label for="name" class="col-sm-3 col-form-label">科目名</label>
                    <ul class="mb-0 ps-3">
                <%-- ボタン --%>
                <%-- 科目コーsド --%>
                <%-- 科目名 --%>
                </div>
                </div>
                </div>
                </div>
                <div class="alert alert-danger mx-3">
                <div class="mb-3 row">
                <div class="mb-3 row">
                <div class="row mt-4">
                科目登録
            <%-- エラーメッセージ --%>
            </c:if>
            </form>
            </h2>
            <c:if test="${not empty errors}">
            <form method="post" action="SubjectCreateExecute.action" class="px-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
        </section>
        <section class="container mt-4">
    </c:param>
    <c:param name="content">
    <c:param name="title">科目登録</c:param>
    pageEncoding="UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
</c:import>
<c:import url="/common/base.jsp">