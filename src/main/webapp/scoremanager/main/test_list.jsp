<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="content">
    <!-- 科目情報 -->
    <div class="card mb-4">
        <div class="card-header">科目情報</div>
        <div class="card-body">

            <form action="ScoreList.action" method="get" class="row g-3">

                <div class="col-md-3">
                    <label class="form-label">入学年度</label>
                    <select name="entYear" class="form-select">
                        <option value="">選択してください</option>
                        <option value="2021">2021</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label class="form-label">クラス</label>
                    <select name="classNum" class="form-select">
                        <option value="">選択してください</option>
                        <option value="201">201</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label class="form-label">科目</label>
                    <select name="subject" class="form-select">
                        <option value="">選択してください</option>
                        <option value="情報基礎実験Ⅱ">情報基礎実験Ⅱ</option>
                    </select>
                </div>

                <div class="col-md-3 d-flex align-items-end">
                    <button class="btn btn-primary w-100">検索</button>
                </div>

            </form>
        </div>
    </div>

    <!-- 学生情報 -->
    <div class="card mb-4">
        <div class="card-header">学生情報</div>
        <div class="card-body">

            <form action="StudentSearch.action" method="get" class="row g-3">

                <div class="col-md-6">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="studentNo" class="form-control"
                        placeholder="学生番号を入力してください">
                </div>

                <div class="col-md-3 d-flex align-items-end">
                    <button class="btn btn-secondary w-100">検索</button>
                </div>

            </form>

        </div>
    </div>

    <!-- メッセージ -->
    <div class="alert alert-info">
        科目情報を選択または学生番号を入力して検索ボタンをクリックしてください
    </div>
</c:set>

<c:import url="/common/base.jsp">
	<c:param name="title" value="成績参照" />
    <c:param name="content">
    <c:out value="${content}" escapeXml="false"/>
    </c:param>
</c:import>
