<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="content">
    <!-- ① 画面タイトル -->
    <div class="bg-light p-3 mb-3 fw-bold">
        成績管理
    </div>

    <!-- ② 登録完了メッセージ（緑帯） -->
    <div class="alert alert-success py-2 mb-4">
        登録が完了しました。
    </div>

    <!-- ③・④ 画面下リンク -->
    <div>
        <a href="test_regist.jsp" class="me-4">戻る</a>
        <a href="test_list.jsp">成績参照</a>
    </div>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title" value="成績管理" />
    <c:param name="content">
        <c:out value="${content}" escapeXml="false"/>
    </c:param>
</c:import>