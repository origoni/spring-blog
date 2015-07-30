<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<title>Hello Millky</title>
</head>
<body>
<a href="https://github.com/origoni/Spring-Blog"><img style="position: absolute; top: 0; right: 0; border: 0; z-index: 2;" src="https://camo.githubusercontent.com/652c5b9acfaddf3a9c326fa6bde407b87f7be0f4/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f72696768745f6f72616e67655f6666373630302e706e67" alt="Fork me on GitHub" data-canonical-src="https://s3.amazonaws.com/github/ribbons/forkme_right_orange_ff7600.png"></a>
	<div class="container" id="body_test">
		<h2>Hello! ${name}</h2>
		<div>JSP version</div>
		<div><fmt:message key="required"/></div>
		<a href="/post/list">
			<button type="button" class="btn btn-lg btn-success btn-block">
			<c:if test="${_USER!=null}">환영합니다! ${_USER.displayName}<br><br></c:if>
			Spring Blog 라이브 데모 들어가기
			</button>
		</a>

		<article>
			<h1>SpringBlog from Millky</h1>
			<p>
				밀키(millky.com)에서 블로그 부분을 뽑아(?) 오픈소스 합니다. <a href="http://millky.com/@origoni">http://millky.com/@origoni</a><br>
				단지 코드만 공개한는 것이 아니라. 개발 과정을 같이 공개하려 합니다.<br> 문의사항은 밀키, 페이스북, 깃헙 등 모두 열려 있습니다 ^^;
			</p>

			<h3>관련 링크</h3>
			<ul>
				<li><a href="http://millky.com/">http://millky.com/</a></li>
				<li><a href="http://millky.com/@origoni/folder/30/post/list">http://millky.com/@origoni/folder/30/post/list</a></li>
				<li><a href="https://github.com/origoni/Spring-Blog">https://github.com/origoni/Spring-Blog</a></li>
			</ul>

			<h3>Project Convention</h3>

			<h4>Package Structure</h4>
			<pre><code>com.millky.blog
    └── application
        └── configuration
        └── utility
    └── domain
        └── model
            └── command
            └── entity
            └── exception
        └── repository
        └── service
    └── infrastructure
        └── dao
    └── presentation
        └── controller
            └── rest
        └── support
            └── result</code></pre>

			<h4>프로젝트 설정</h4>

			<ol>
				<li>STS 설치 -&gt; <a href="http://millky.com/@origoni/post/1100">http://millky.com/@origoni/post/1100</a></li>
				<li>Lombok 설치 -&gt; <a href="https://projectlombok.org/">https://projectlombok.org/</a>
					(설치 : <a href="http://millky.com/@origoni/post/1164">http://millky.com/@origoni/post/1164</a>)
				</li>
				<li>GitHub 에서 다운 -&gt; <a href="http://millky.com/@origoni/post/1145">http://millky.com/@origoni/post/1145</a>
					(OSX : <a href="http://millky.com/@origoni/post/1140">http://millky.com/@origoni/post/1140</a>)
				</li>
			</ol>
		</article>
	</div>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
