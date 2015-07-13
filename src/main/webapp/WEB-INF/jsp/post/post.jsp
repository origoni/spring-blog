<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value="${post.title}" escapeXml="true"></c:out> : Spring Blog</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/nav.jspf" %>

    <header class="intro-header" style="background-image: url('http://ironsummitmedia.github.io/startbootstrap-clean-blog/img/post-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1><c:out value="${post.title}" escapeXml="true"></c:out></h1>
                        <h2 class="subheading"><c:out value="${post.subtitle}" escapeXml="true"></c:out></h2>
                        <span class="meta">Posted by <a href="#">${post.name}</a> in <a href="/post/list?category=${post.category.id}"><c:out value="${post.category.name}" escapeXml="true" /></a> on ${post.regDate}</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    ${post.content}
                </div>
            </div>
            
            <c:if test="${_USER!=null && _USER.providerUserId == post.userId}"> 
            <div class="pull-right">
            	<a href="/post/${post.id}/edit">
            		<button type="button" class="btn btn-warning">Edit</button>
            	</a>
            	<a href="/post/${post.id}/delete" onclick="if(!confirm('진심이에요?')){return false;}">
            		<button type="button" class="btn btn-danger">Delete</button>
            	</a>
            </div>
            </c:if>
        </div>
    </article>
    
    <hr>
    
	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>


