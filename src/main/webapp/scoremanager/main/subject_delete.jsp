<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目削除</c:param>

    <c:param name="content">
        <section class="container mt-4">
            <h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4">
                科目削除
            </h2>

            <form method="post" action="SubjectDeleteExecute.action">

                <!-- 科目コード -->
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
                               value="${subject.name}" disabled>
                    </div>
                </div>

                <!-- ボタン -->
                <div class="row mt-4">
                    <div class="col-sm-9 offset-sm-3">
                        <button type="submit" class="btn btn-danger">
                            削除
                        </button>
                        SubjectList.action
                            キャンセル
                        </a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>