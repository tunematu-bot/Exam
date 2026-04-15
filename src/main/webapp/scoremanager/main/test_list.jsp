<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

<style>

/* ========================= */
/* 上部ヘッダー（淡い青） */
/* ========================= */
.navbar-custom {
    background-color: #d9e2ec;   /* 淡い青 */
    border-bottom: 1px solid #b8c7d9;
}

.navbar-custom .navbar-brand {
    font-size: 1.4rem;  /* 左画像と同じ大きさ */
    font-weight: bold;
    color: #2c3e50;
}

.navbar-custom .header-right {
    color: #2c3e50;
    font-weight: bold;
}

.navbar-custom a {
    color: #2c3e50;
    text-decoration: none;
}

/* ========================= */
/* 左サイドバー */
/* ========================= */
.sidebar {
    background-color: #ffffff;
    border-right: 1px solid #dee2e6;
    min-height: 100vh;
}

.sidebar .nav-item {
    color: #333;
    font-size: 15px;
}

/* ========================= */
/* メイン背景 */
/* ========================= */
body {
    background-color: #f8f9fa;
}

</style>
</head>

<body>

<!-- ★ 上部ヘッダー（淡い青に変更） -->
<nav class="navbar navbar-custom px-3 d-flex justify-content-between">
	<span class="navbar-brand mb-0 h4">得点管理システム</span>

    <div class="header-right">
        大原 大阪校　
        <a href="Logout.action">ログアウト</a>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">

        <!-- ★ 左メニュー（白＋薄いグレー） -->
        <div class="col-2 sidebar">
            <h5 class="mt-3">メニュー</h5>
            <ul class="nav flex-column">
                <li class="nav-item mt-2">学生管理</li>
                <li class="nav-item mt-2">成績管理</li>
                <li class="nav-item mt-2">成績参照</li>
                <li class="nav-item mt-2">科目管理</li>
                <li class="nav-item mt-2">引退管理</li>
            </ul>
        </div>

        <!-- メイン -->
        <div class="col-10 p-4">

            <h3 class="mb-4">成績参照</h3>

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
                            <input type="text" name="studentNo" class="form-control" placeholder="学生番号を入力してください">
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

        </div>
    </div>
</div>

</body>
</html>
