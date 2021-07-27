<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="demoResult"/>
</jsp:include>
<style>
	table#tbl-dev{
		margin:0 auto;
		width:50%;
	}

</style>
<section id="content">
	<table class="table" id="tbl-dev">
		<tr>
			<th scope="col">이름</th>
			<td><c:out value="${demo.devName}"/>
		</tr>
		<tr>
			<th>나이</th>
			<td><c:out value="${demo.devAge }"/>
		</tr>
		<tr>
			<th>이메일</th>
			<td><c:out value="${demo.devEmail}"/>
		</tr>
		<tr>
			<th>성별</th>
			<td><c:out value="${$demo.devGender }"/>
		</tr>
		<tr>
			<th>개발언어</th>
			<td>
				<c:forEach var="l" items="${demo.devLang}" varStatus="vs">
						<c:out value="${l }"/>
						<c:if test="${not vs.last }">
									<c:out value=","/>
						</c:if>
				</c:forEach>
			</td>								
		</tr>						
	</table>
</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>