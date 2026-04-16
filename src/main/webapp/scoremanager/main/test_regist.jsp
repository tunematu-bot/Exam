<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績管理</c:param>

    <c:param name="content">
        <section class="container mt-4">

            <!-- タイトル -->
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>

            <div class="card">
                <div class="card-body">

                    <form action="ScoreSearch.action" method="get">

                        <div class="row align-items-end g-3">

                            <!-- 入学年度 -->
                            <div class="col-md-2">
                                <label class="form-label fw-bold">入学年度</label>
                                <select name="year" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="y" items="${yearList}">
                                        <option value="${y}">${y}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- クラス -->
                            <div class="col-md-2">
                                <label class="form-label fw-bold">クラス</label>
                                <select name="classNum" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="c" items="${classList}">
                                        <option value="${c.classNum}">
                                            ${c.className}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- 科目 -->
                            <div class="col-md-4">
                                <label class="form-label fw-bold">科目</label>
                                <select name="subject" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="s" items="${subjectList}">
                                        <option value="${s.subjectCd}">
                                            ${s.subjectName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- 回数 -->
                            <div class="col-md-2">
                                <label class="form-label fw-bold">回数</label>
                                <select name="times" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="t" items="${timesList}">
                                        <option value="${t}">${t}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- 検索 -->
                            <div class="col-md-2">
                                <button type="submit"
                                        class="btn btn-secondary w-100">
                                    検索
                                </button>
                            </div>

                        </div>

                    </form>

                </div>
            </div>

        </section>
    </c:param>
</c:import>
``