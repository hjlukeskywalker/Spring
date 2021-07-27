<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${path }/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${path }/resources/css/style.css" type="text/css">
</head>
<body>
	<div id="container">
		<header>
			<div id="header-container">
				<h2>Hello Spring</h2>
			</div>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a class="navbar-brand" href="#">
					<img src="${path }/resources/images/logo-spring.png"
					alt="스프링로고" width="50px"/>
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" 
				data-target="#navbarNav" aria-controls="navabarNav" aria-expanded="false"
				aria-label="Toggle navigation">
					<span class="navabar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active">
							<a class="nav-link" href="${path }">Home</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="">게시판</a>
						</li>
<%-- 						<li class="nav-item">
							<a class="nav-link" href="${path}/demo/demo.do">DEMO</a>
						</li>	 --%>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">demo</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${path }/demo/demo.do">demo등록</a>
								<a class="dropdown-item" href="${path }/demo/selectDev.do">demo조회</a>
							</div>
						</li>					
					</ul>
				</div>
			</nav>
		</header>
		<section id="content">
			<img src="${path }/resources/images/logo-spring.png" id="center-image" alt="스프링로고">
		</section>
