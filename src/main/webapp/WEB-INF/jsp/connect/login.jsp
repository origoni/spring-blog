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
                        <span class="subheading">Connect to Facebook</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

	<div class="container">
		<h3>Connect to Facebook API v2.3</h3>

		<form action="/signin/facebook" method="POST">
			<input type="hidden" name="_csrf" value="${_csrf.token}"></input>
			<div class="formInfo">
				<p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
			</div>
			<p>
				<button type="submit">Connect to Facebook</button>
			</p>
		</form>
	</div>

	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>