<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<title>${status}Error</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/nav.jspf"%>
	
	<header class="intro-header" style="background-image: url('/image/about-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Error</h1>
                    </div>
                </div>
            </div>
        </div>
    </header>
    
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1>${status} Error</h1>

				<h3>
					<i class="fa fa-warning"></i> ${error}
				</h3>

				<p>${message}</p>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>


