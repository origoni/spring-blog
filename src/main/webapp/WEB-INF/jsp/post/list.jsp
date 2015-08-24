<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title>Hello Spring Blog</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/nav.jspf" %>

	<header class="intro-header" style="background-image: url('/image/home-bg.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<div class="site-heading">
						<h1>Spring Blog</h1>
						<hr class="small">
						<span class="subheading">Spring Blog form Millky</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
			<c:if test="${category!=null}">
				<c:out value="${category}" escapeXml="true" /> 카테고리에 
			</c:if>
			<c:if test="${tag!=null}">
				<c:out value="${tag}" escapeXml="true" /> 태그에 
			</c:if>
			<c:if test="${postPage.content.size()>0}">
				${postPage.content.size()} 개의 글이 있습니다.
			</c:if>
			<c:if test="${postPage.content.size()==0}">
				글이 없습니다.
			</c:if>
			<c:forEach var="post" items="${postPage.content}">
				<div class="post-preview">
					<a href="/post/${post.id}">
						<h2 class="post-title">
							<c:out value="${post.title}" escapeXml="true"></c:out>
						</h2>
						<h3 class="post-subtitle">
							<c:out value="${post.subtitle}" escapeXml="true"></c:out>
						</h3>
					</a>
					<p class="post-meta">Posted by <a href="#">${post.name}</a> in <a href="/category/${post.category.id}/post/list"><c:out value="${post.category.name}" escapeXml="true" /></a> on ${post.regDate}</p>
				</div>
				<hr>
			</c:forEach>

				<ul class="pager">
					<c:if test="${!postPage.first}">
					<li class="previous">
						<a href="?page=${postPage.number-1}">&larr; Newer Posts</a>
					</li>
					</c:if>
					<c:if test="${!postPage.last}">
					<li class="next">
						<a href="?page=${postPage.number+1}">Older Posts &rarr;</a>
					</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<hr>
	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>


