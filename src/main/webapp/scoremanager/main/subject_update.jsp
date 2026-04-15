<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目変更</c:param>

    <c:param name="content">
        <section class="container mt-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目変更
            </h2>

            <form method="post" action="SubjectUpdateExecute.action">

                <!-- 科目コード（変更w不可） -->
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">科目コード</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control"
                               value="${subject.cd}" disabled>
                        <input type="hidden" name="cd" value="${subject.cd}">
                    </div>
                </div>

                <!-- 科目名 -->
                <div class="mb-3 row">
                    <label class="col-sm-3 col-form-label">科目名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control"
                               name="name"
                               value="${subject.name}"
                               required>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-sm-9 offset-sm-3">
                        <button type="submit" class="btn btn-primary">更新</button>
                        <a href="SubjectList.action" class="btn btn-secondary ms-2">
                            戻る
                        </a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>
