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
			<c:if test="${query!=null}">
				<c:out value="${query}" escapeXml="true" /> (으)로 검색된 
			</c:if>
			<c:if test="${category!=null}">
				<c:out value="${category}" escapeXml="true" /> 카테고리에 
			</c:if>
			<c:if test="${tag!=null}">
				<c:out value="${tag}" escapeXml="true" /> 태그에 
			</c:if>
			<c:if test="${postPage.totalElements>0}">
				총 ${postPage.totalElements} 개의 글이 있습니다.
			</c:if>
			<c:if test="${postPage.totalElements==0}">
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
						<c:if test="${query==null}">
							<a href="?page=${postPage.number-1}">&larr; Newer Posts</a>
						</c:if>
						<c:if test="${query!=null}">
							<a href="?type=${param.type}&query=${param.query}&page=${postPage.number-1}">&larr; Newer Posts</a>
						</c:if>
					</li>
					</c:if>
					<c:if test="${!postPage.last}">
					<li class="next">
						<c:if test="${query==null}">
							<a href="?page=${postPage.number+1}">Older Posts &rarr;</a>
						</c:if>
						<c:if test="${query!=null}">
							<a href="?type=${param.type}&query=${param.query}&page=${postPage.number+1}">Older Posts &rarr;</a>
						</c:if>
					</li>
					</c:if>
				</ul>
				
				<br />
				
				<form action="/post/search" method="get" class="form-inline">
					<select class="form-control input-lg" name="type">
						<option <c:if test="${type=='title'}">selected="selected" </c:if>value="title">Title</option>
						<option <c:if test="${type=='contents'}">selected="selected" </c:if>value="contents">Contents</option>
						<option <c:if test="${type=='titleAndContents'}">selected="selected" </c:if>value="titleAndContents">Title And Contents</option>
					</select>
					<div class="input-group">
						<input type="text" name="query" class="form-control input-lg" placeholder="Search..." value="${query}">
						<span class="input-group-btn">
							<button type="submit" class="btn btn-default input-lg" style="border-radius: 0 4px 4px 0; padding: 10px 20px;">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				
			</div>
		</div>
	</div>
	<hr>
	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>


