<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title>Login</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/nav.jspf" %>

    <header class="intro-header" style="background-image: url('/image/contact-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="page-heading">
                        <h1>Login</h1>
                        <hr class="small">
                        <span class="subheading">Connect to ...</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

	<div class="container">
        <h3>Login with OAuth 2.0</h3>

        <table>
        <c:forEach var="url" items="${urls}">
            <tr><td><a class="btn-warning" href="${url.value}">${url.key}</a></td></tr>
        </c:forEach>
        </table>
	</div>

	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>