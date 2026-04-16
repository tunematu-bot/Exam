<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績管理</c:param>

    <c:param name="content">
        <section class="container mt-4">

            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>

            <!-- 検索条件 -->
            <div class="card mb-3">
                <div class="card-body">

                    <form action="ScoreSearch.action" method="get">
                        <div class="row align-items-end g-3">

                            <div class="col-md-2">
                                <label class="form-label fw-bold">入学年度</label>
                                <select name="year" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="y" items="${yearList}">
                                        <option value="${y}"
                                            <c:if test="${y == param.year}">selected</c:if>>
                                            ${y}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <label class="form-label fw-bold">クラス</label>
                                <select name="classNum" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="c" items="${classList}">
                                        <option value="${c.classNum}"
                                            <c:if test="${c.classNum == param.classNum}">selected</c:if>>
                                            ${c.className}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-4">
                                <label class="form-label fw-bold">科目</label>
                                <select name="subjectCd" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="s" items="${subjectList}">
                                        <option value="${s.subjectCd}"
                                            <c:if test="${s.subjectCd == param.subjectCd}">selected</c:if>>
                                            ${s.subjectName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <label class="form-label fw-bold">回数</label>
                                <select name="times" class="form-select">
                                    <option value="">--------</option>
                                    <c:forEach var="t" items="${timesList}">
                                        <option value="${t}"
                                            <c:if test="${t == param.times}">selected</c:if>>
                                            ${t}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <button type="submit" class="btn btn-secondary w-100">
                                    検索
                                </button>
                            </div>

                        </div>
                    </form>

                </div>
            </div>

            <!-- ▼ 検索後：点数入力画面 ▼ -->
            <c:if test="${not empty studentList}">

                <div class="mb-2 fw-bold">
                    科目：${subjectName}（${times}回）
                </div>

                <form action="TestRegistAction" method="post">

                    <input type="hidden" name="subjectCd" value="${param.subjectCd}">
                    <input type="hidden" name="times" value="${param.times}">
                    <input type="hidden" name="classNum" value="${param.classNum}">

                    <table class="table table-bordered">
                        <thead class="table-light">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="stu" items="${studentList}" varStatus="st">
                            <tr>
                                <td>${stu.year}</td>
                                <td>${stu.classNum}</td>
                                <td>
                                    ${stu.studentNo}
                                    <input type="hidden"
                                           name="studentNo"
                                           value="${stu.studentNo}">
                                </td>
                                <td>${stu.studentName}</td>
                                <td>
								    <input type="text"
								           name="point"
								           class="form-control text-end"
								           value="${inputPointMap[stu.studentNo]}">
								
								    <c:if test="${not empty errorMap[stu.studentNo]}">
								        <div class="text-warning small">
								            ${errorMap[stu.studentNo]}
								        </div>
								    </c:if>
								
								    <input type="hidden"
								           name="studentNo"
								           value="${stu.studentNo}">
								</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <button type="submit" class="btn btn-secondary">
                        登録して終了
                    </button>

                </form>
            </c:if>

        </section>
    </c:param>
</c:import>