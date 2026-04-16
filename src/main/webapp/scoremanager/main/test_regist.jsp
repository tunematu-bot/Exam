<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="content">

        <div class="main">

            <h1 class="h5 fw-bold bg-light border p-2 mb-4">
                成績管理
            </h1>

            <div class="card shadow-sm">
                <div class="card-body">

                    <form action="ScoreSearch.action" method="get">

                        <div class="row g-4">

                            <div class="col-md-3">
                                <label class="form-label fw-bold">入学年度</label>
                                <select name="year" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="y" items="${yearList}">
                                        <option value="${y}">${y}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-3">
                                <label class="form-label fw-bold">クラス</label>
                                <select name="classNum" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="c" items="${classList}">
                                        <option value="${c.classNum}">${c.className}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-4">
                                <label class="form-label fw-bold">科目</label>
                                <select name="subject" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="s" items="${subjectList}">
                                        <option value="${s.subjectCd}">${s.subjectName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <label class="form-label fw-bold">回数</label>
                                <select name="times" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="t" items="${timesList}">
                                        <option value="${t}">${t}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-2 d-grid mt-3">
                                <button type="submit" class="btn btn-secondary">
                                    検索
                                </button>
                            </div>

                        </div>

                    </form>

                </div>
            </div>

        </div>

    </c:param>
</c:import>
