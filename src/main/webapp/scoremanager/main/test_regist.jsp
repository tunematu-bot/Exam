<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>成績管理 | 得点管理システム</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet">

<style>

/* ===== 上部ヘッダー ===== */
.header {
    background-color:#d9e2ec;
    padding:15px 20px;
    font-weight:bold;
}

/* ===== 左メニュー ===== */
.sidebar {
    width:200px;
    background:#f5f5f5;
    min-height:100vh;
    padding:15px;
}

.sidebar a{
    display:block;
    margin-bottom:10px;
    text-decoration:none;
}

/* ===== メイン画面 ===== */
.main {
    flex:1;
    padding:25px;
}

</style>
</head>

<body>

<!-- ========================= -->
<!-- 上部ヘッダー -->
<!-- ========================= -->
<div class="header d-flex justify-content-between align-items-end">    <div><h2>得点管理システム</h2></div>
    <div>太田 太郎　<a href="#">ログアウト</a></div>
</div>

<!-- ========================= -->
<!-- 2カラムレイアウト -->
<!-- ========================= -->
<div class="d-flex">

    <!-- ===== 左メニュー ===== -->
    <div class="sidebar">

        <a href="menu.jsp">メニュー</a>

        <div class="fw-bold mt-3">学生管理</div>
        <a href="#">学生一覧</a>

        <div class="fw-bold mt-3">成績管理</div>
        <a href="#">成績登録</a>
        <a href="#">成績参照</a>

        <div class="fw-bold mt-3">科目管理</div>
        <a href="#">科目一覧</a>

    </div>

    <!-- ===== メイン ===== -->
    <div class="main">

        <!-- ① タイトル -->
        <h1 class="h5 fw-bold bg-light border p-2 mb-4">
            成績管理
        </h1>

        <div class="card">
            <div class="card-body">

                <form action="ScoreSearch.action" method="get">

                    <!-- 横並び -->
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

    </div>

</div>

</body>
</html>