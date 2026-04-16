<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git

<c:import url="/common/base.jsp">
<<<<<<< HEAD
    <c:param name="content">
=======
    <c:param name="title">成績管理</c:param>
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git

<<<<<<< HEAD
        <div class="main">
=======
    <c:param name="content">
        <section class="container mt-4">
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git

<<<<<<< HEAD
            <h1 class="h5 fw-bold bg-light border p-2 mb-4">
                成績管理
            </h1>
=======
            <!-- タイトル -->
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git

<<<<<<< HEAD
            <div class="card shadow-sm">
=======
            <div class="card">
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
                <div class="card-body">

                    <form action="ScoreSearch.action" method="get">

<<<<<<< HEAD
                        <div class="row g-4">
=======
                        <div class="row align-items-end g-3">
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git

<<<<<<< HEAD
                            <div class="col-md-3">
=======
                            <!-- 入学年度 -->
                            <div class="col-md-2">
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
                                <label class="form-label fw-bold">入学年度</label>
                                <select name="year" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="y" items="${yearList}">
                                        <option value="${y}">${y}</option>
                                    </c:forEach>
                                </select>
                            </div>

<<<<<<< HEAD
                            <div class="col-md-3">
                                <label class="form-label fw-bold">クラス</label>
                                <select name="classNum" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="c" items="${classList}">
                                        <option value="${c.classNum}">${c.className}</option>
=======
                            <!-- クラス -->
                            <div class="col-md-2">
                                <label class="form-label fw-bold">クラス</label>
                                <select name="classNum" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="c" items="${classList}">
                                        <option value="${c.classNum}">
                                            ${c.className}
                                        </option>
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
                                    </c:forEach>
                                </select>
                            </div>

<<<<<<< HEAD
                            <div class="col-md-4">
                                <label class="form-label fw-bold">科目</label>
                                <select name="subject" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="s" items="${subjectList}">
                                        <option value="${s.subjectCd}">${s.subjectName}</option>
=======
                            <!-- 科目 -->
                            <div class="col-md-4">
                                <label class="form-label fw-bold">科目</label>
                                <select name="subject" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="s" items="${subjectList}">
                                        <option value="${s.subjectCd}">
                                            ${s.subjectName}
                                        </option>
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
                                    </c:forEach>
                                </select>
                            </div>

<<<<<<< HEAD
=======
                            <!-- 回数 -->
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
                            <div class="col-md-2">
                                <label class="form-label fw-bold">回数</label>
                                <select name="times" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="t" items="${timesList}">
                                        <option value="${t}">${t}</option>
                                    </c:forEach>
                                </select>
                            </div>

<<<<<<< HEAD
                            <div class="col-md-2 d-grid mt-3">
                                <button type="submit" class="btn btn-secondary">
=======
                            <!-- 検索 -->
                            <div class="col-md-2">
                                <button type="submit"
                                        class="btn btn-secondary w-100">
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
                                    検索
                                </button>
                            </div>

                        </div>

                    </form>

                </div>
            </div>
<<<<<<< HEAD

        </div>
=======
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git

<<<<<<< HEAD
    </c:param>
</c:import>
=======
        </section>
    </c:param>
</c:import>
``
>>>>>>> branch 'master' of https://github.com/tunematu-bot/Exam.git
