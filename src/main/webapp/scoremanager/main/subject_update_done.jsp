<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目変更完了</c:param>

    <c:param name="content">
        <section class="container mt-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目変更完了
            </h2>

            <div class="px-4">
                <div class="alert alert-success">
                    <strong>変更が完了しました</strong>
                </div>

                <div class="mt-4">
                    <a href="SubjectList.action" class="btn btn-secondary">
                        科目一覧
                    </a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>
``