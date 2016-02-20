<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pen - What You See Is What You Get (WYSIWYG)</title>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.5/dist/css/bootstrap.min.css">
<style type="text/css">
  *{padding:0;margin:0;}
  html{border-top:10px #1abf89 solid;}
  body{width:750px;margin:0 auto;padding:7% 20px 20px;}
  @media all and (max-width:1024px){ body, pre a{width:85%;} }
  @media (max-width: 767px) { body, pre a{width:95%;} body{margin:0 auto;padding:50px 5px 5px;} }
  small{color:#999;}
  #toolbar [class^="icon-"]:before, #toolbar [class*=" icon-"]:before{font-family:'pen'}
  @media (max-width: 767px) { #toolbar{margin-bottom:1em;position:static;right:auto;margin-top:25px;width:100%;} }
  @media (min-width: 768px) { #toolbar{margin-bottom:1em;position:fixed;right:20px;top:25px;} }
  #back{color:#1abf89;cursor:pointer;}
  #hinted{color:#1abf89;cursor:pointer;}
  #hinted.disabled{color:#666;}
  #hinted:before{content: '\e816';}
  .error {color: red;}
</style>

<link rel="stylesheet" href="/webjars/pen/0.2.2/src/pen.css" />
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

	<form:form action="${requestScope['javax.servlet.forward.servlet_path']}" commandName="postCommand" id="post" onsubmit="if($('#pen').html()!='<p><br></p>')$('#content').val($('#pen').html()); pen.destroy();" method="post">

		<form:input type="hidden" path="_csrf" value="${_csrf.token}"></form:input>
	
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

		<form:input type="text" path="tags" placeholder="Tag (option - 최대 10개. 공백으로 구분합니다.)"
			style="height: 40px; width: 100%; font-size: 18px; 
			border: none; border-right: 0px; border-top: 0px; boder-left: 0px; boder-bottom: 1px; outline-style: none; 
			font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif; font-weight: 600;" />
		
		<hr style="margin-top: 2px; border-top: 1px solid #999;">
		
		<div class="form-group" style="height: 30px;">
			<label for="category" class="col-sm-2 col-xs-3 control-label" style="padding-left: 5px;">Category</label>
			<div class="col-sm-10 col-xs-9" style="padding-right: 5px;">
				<form:select path="categoryId" items="${categoryMap}" id="category" class="form-control"/>
				<form:errors path="categoryId" cssClass="error" />
			</div>
		</div>
				
		<button type="submit" class="btn btn-primary btn-lg btn-block">저장</button>

	</form:form>
	
	
	<div id="toolbar">
		<span id="back" class="icon-back" onclick="history.back();">돌아가기</span><br>
		<span id="hinted" class="icon-pre disabled" title="Toggle Markdown Hints"></span>
		
		<form action="/category/add" method="post" id="add_category" >
			<input type="text" name="categoryName" class="form-control" placeholder="새로운 카테고리" required="required">
			<input type="hidden" name="_csrf" value="${_csrf.token}">
			<button type="submit" class="form-control">추가</button>
		</form>
	</div>
	
	<p class="text-muted" style="font-size: 14px;">Powered By <a href="http://millky.com">Millky</a> | WYSIWYG Editor by <a href="https://github.com/sofish/pen">Pen Editor</a></p>

	<script src="/webjars/jquery/2.1.4/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/3.3.5/dist/js/bootstrap.min.js"></script>
	<script src="/webjars/pen/0.2.2/src/pen.js"></script>
	<script src="/webjars/pen/0.2.2/src/markdown.js"></script>
	<script type="text/javascript">
		$('#add_category').submit(function(event) {
			var form = $(this);
						
			$.ajax({
				type : form.attr('method'),
				url : form.attr('action'),
				data : form.serialize()
			}).done(function(c) {				
				$("#category").append("<option value=" + c.id + ">" + c.name + "</option>");
				$("#category").val(c.id);
				
				alert(c.name + " 카테고리가 추가되었습니다.");
			}).fail(function() {
				alert('error');
			});
			event.preventDefault();
		});

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
	</script>
</body>
</html>