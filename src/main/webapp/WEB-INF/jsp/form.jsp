<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pen - What You See Is What You Get (WYSIWYG)</title>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
<style type="text/css">
  *{padding:0;margin:0;}
  html{border-top:10px #1abf89 solid;}
  body{width:750px;margin:0 auto;padding:7% 20px 20px;}
  @media all and (max-width:1024px){ body, pre a{width:85%;} }
  small{color:#999;}
  #toolbar{margin-bottom:1em;position:fixed;left:20px;margin-top:5px;}
  #toolbar [class^="icon-"]:before, #toolbar [class*=" icon-"]:before{font-family:'pen'}
  #back{color:#1abf89;cursor:pointer;}
  #hinted{color:#1abf89;cursor:pointer;}
  #hinted.disabled{color:#666;}
  #hinted:before{content: '\e816';}
  
  .errorblock {border: 2px solid red;}
  .error {color: red;}
</style>

<link rel="stylesheet" href="/webjars/pen/0.1.0/src/pen.css" />
<link rel="stylesheet" href="/webjars/origoni-startbootstrap-clean-blog/1.0.3/css/clean-blog.min.css">

<style type="text/css">
.pen-icon {padding: 0 9.3px;}
.pen-menu {opacity: 0.8; border: -1px; height: 37px;}
.pen-menu:after {display: none;}
.pen p {font-family: Lora, 'Times New Roman', serif; font-size: 20px; color: #404040;}
.pen h1, h2, h3, h4, h5, h6 {
	font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
	font-weight: 800;
	margin-top: 20px;
	margin-bottom: 10px;
	line-height: 1.1;
}
.pen h1 {font-size: 36px;}
.pen h2 {font-size: 30px;}
.pen h3 {font-size: 24px;}
.pen h4 {font-size: 18px;}
</style>
</head>
<body>

	<div id="toolbar">
		<span id="back" class="icon-back" onclick="history.back();">돌아가기</span><br>
		<span id="hinted" class="icon-pre disabled" title="Toggle Markdown Hints"></span>
	</div>

	<div id="custom-toolbar" class="pen-menu pen-menu" style="display: block; top: 20px; margin:0 auto;">
	  <i class="pen-icon icon-insertimage" data-action="insertimage"></i>
	  <i class="pen-icon icon-blockquote" data-action="blockquote"></i>
	  <i class="pen-icon icon-h1" data-action="h1"></i>
	  <i class="pen-icon icon-h2" data-action="h2"></i>
	  <i class="pen-icon icon-h3" data-action="h3"></i>
	  <i class="pen-icon icon-p active" data-action="p"></i>
	  <i class="pen-icon icon-code" data-action="code"></i>
	  <i class="pen-icon icon-insertorderedlist" data-action="insertorderedlist"></i>
	  <i class="pen-icon icon-insertunorderedlist" data-action="insertunorderedlist"></i>
	  <i class="pen-icon icon-inserthorizontalrule" data-action="inserthorizontalrule"></i>
	  <i class="pen-icon icon-indent" data-action="indent"></i>
	  <i class="pen-icon icon-outdent" data-action="outdent"></i>
	  <i class="pen-icon icon-bold" data-action="bold"></i>
	  <i class="pen-icon icon-italic" data-action="italic"></i>
	  <i class="pen-icon icon-underline" data-action="underline"></i>
	  <i class="pen-icon icon-createlink" data-action="createlink"></i>
	</div>

	<c:if test="${post.id == 0}"><c:url var="actionUrl" value="/post/write"/></c:if>
	<c:if test="${post.id != 0}"><c:url var="actionUrl" value="/post/${post.id}/edit"/></c:if>

	<form:form action="${actionUrl}" commandName="post" onsubmit="if($('#pen').html()!='<p><br></p>')$('#content').val($('#pen').html()); pen.destroy();" method="post">

		<c:if test="${post.id != 0}"><form:input type="hidden" path="regDate" /></c:if>

		<form:errors path="*" cssClass="errorblock" element="div" />

		<form:input type="text" path="title" placeholder="Title"
			style="height: 70px; width: 100%; font-size: 55px; 
			border: none; border-right: 0px; border-top: 0px; boder-left: 0px; boder-bottom: 1px; outline-style: none; 
			font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif; font-weight: 800;" />
		<form:errors path="title" cssClass="error" />

		<form:input type="text" path="subtitle" placeholder="Subtitle (option)"
			style="height: 60px; width: 100%; font-size: 24px; 
			border: none; border-right: 0px; border-top: 0px; boder-left: 0px; boder-bottom: 1px; outline-style: none; 
			font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif; font-weight: 600;" />

		<hr style="margin-top: 2px; border-top: 1px solid #999;">

		<div data-toggle="pen" data-placeholder="Content" id="pen" style="min-height: 200px;"></div>
		<form:input type="hidden" path="content" id="content" />
		<form:errors path="content" cssClass="error" />

		<hr>

		<button type="submit" class="btn btn-primary btn-lg btn-block">저장</button>

	</form:form>

	<p class="text-muted">Powered By <a href="http://millky.com">Millky</a> | WYSIWYG Editor by <a href="https://github.com/sofish/pen">Pen Editor</a></p>

	<script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script>
	<script src="/webjars/pen/0.1.0/src/pen.js"></script>
	<script src="/webjars/pen/0.1.0/src/markdown.js"></script>
	<script type="text/javascript">
		// config
		var options = {
			toolbar : document.getElementById('custom-toolbar'),
			editor : document.querySelector('[data-toggle="pen"]')
		};
		
		$('#pen').html($('#content').val());

		// create editor
		var pen = window.pen = new Pen(options);

		pen.focus();

		document.querySelector('#hinted').addEventListener('click', function() {
			var pen = document.querySelector('.pen')

			if (pen.classList.contains('hinted')) {
				pen.classList.remove('hinted');
				this.classList.add('disabled');
			} else {
				pen.classList.add('hinted');
				this.classList.remove('disabled');
			}
		});
		
		// 바디의 마진을 가지고 와서. 툴바의 좌측으로.
	</script>
</body>
</html>