<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale.language}">
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value="${post.title}" escapeXml="true" /> : Spring Blog</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/nav.jspf" %>

	<header class="intro-header" style="background-image: url('/image/post-bg.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<div class="post-heading">
						<h1><c:out value="${post.title}" escapeXml="true"></c:out></h1>
						<h2 class="subheading"><c:out value="${post.subtitle}" escapeXml="true" /></h2>
						<span class="meta">Posted by <a href="#">${post.name}</a> in <a href="/category/${post.category.id}/post/list"><c:out value="${post.category.name}" escapeXml="true" /></a> on ${post.regDate}</span>
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

				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<h3 style="line-height: 1.6;">
				<c:forEach var="postTag" items="${post.postTagList}" varStatus="status">
					<a href="/tag/<c:out value="${postTag.tag.name}" escapeXml="true" />/post/list">
						<span class="label label-default"><c:out value="${postTag.tag.name}" escapeXml="true" /></span></a>
				</c:forEach>
				</h3>
				</div>
			</div>

			<c:if test="${_USER!=null && _USER.providerUserId == post.userId}">
			<br>
			<div class="pull-right">
				<a href="/post/${post.id}/edit">
					<button type="button" class="btn btn-warning">Edit</button>
				</a>
				<a href="/post/${post.id}/delete" onclick="if(!confirm('진심이에요?')){return false;}">
					<button type="button" class="btn btn-danger">Delete</button>
				</a>
			</div>
			</c:if>
			
			<hr>
			<div class="row">
				<div id="target" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1"></div>
				<c:if test="${_USER!=null}">
					<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
						<br>
						<form action="/comments" method="post" id="comment_form">
							<input type="hidden" name="postId" value="${post.id}">
							<input type="hidden" name="_csrf" value="${_csrf.token}"></input>
							<div class="media">
								<div class="media-body">
									<textarea name="content" class="form-control" rows="2"></textarea>
								</div>
								<div class="media-right">
									<button class="btn" type="submit">저장</button>
								</div>
							</div>
						</form>
					</div>
				</c:if>
			</div>
		</div>
	</article>

	<hr>

	<%@ include file="/WEB-INF/jspf/footer.jspf" %>

<script id="template" type="x-tmpl-mustache">
{{#.}}
<div class="media">
  <div class="media-body">
	{{{content}}}<br>
	<h4 class="media-heading" style="display: inline-block;">{{name}}</h4> on {{momentNow}} <small>({{momentDate}})</small>
	{{#myComment}}<button type="button" style="margin-bottom: 5px;" class="btn btn-danger btn-sm" onclick="if(!confirm('진심이에요?')){return false;} deleteComment({{postId}}, {{id}});">Delete</button>{{/myComment}}
    <br>
  </div>
</div>
{{/.}}
</script>

<script type="text/javascript">

	function deleteComment(postId, commentId) {
		$.ajax({
			type : "delete",
			url : "/comments/" + commentId + "?postId=" + postId,
			dataType : 'json',
			beforeSend : function(xhr) {
				xhr.setRequestHeader('X-CSRF-Token', '${_csrf.token}');
			},
			success : function(data, status) {
				loadComment();
			},
			error : function(data, status) {
				alert(data.responseJSON.message);
			}
		});
	}

	$("#comment_form").submit(function(event) {
		var form = $(this);
		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			dataType : 'json',
			success : function(data, status) {
				loadComment();
				form[0].reset();
			},
			error : function(data, status) {
				alert(data.responseJSON.message);
			}
		});
		event.preventDefault();
	});

	var autolinker = new Autolinker( {
		newWindow : false,
		truncate  : 70
	} );

	moment.locale('${pageContext.request.locale.language}');
	var template = $('#template').html();
	Mustache.parse(template);
	function loadComment() {
		$.ajax({
			type : "GET",
			url : "/comments",
			data : "postId=${post.id}",
			dataType : 'json',
			cache : false,
			success : function(data, status) {
				
				for (k in data) {
					object = data[k];
					// console.log("object = " + object);
					for (key in object) {
						value = object[key];
						if (key == "regDate") {
							object['momentDate'] = moment(value).format("YYYY-MM-DD HH:mm:ss");
							object['momentNow'] = moment(value).fromNow();
						}
						if (key == "content") {
							object['content'] = autolinker.link(value).replace(/(?:\r\n|\r|\n)/g, "<br />"); // /\r?\n|\r/g
						}
						/* <c:if test="${_USER!=null}"> */
						if (key == "userId") {
							// console.log("value = " + value);
							if (value == "${_USER.providerUserId}") {
								object['myComment'] = true;
							}
						}
						/* </c:if> */
					}
				}
				// console.log(data);
				$('#target').html(Mustache.render(template, data));
			},
			error : function(data, status) {
				alert("error");
			}
		}).always(function() {
		});
	}

	loadComment();
</script>

</body>
</html>


