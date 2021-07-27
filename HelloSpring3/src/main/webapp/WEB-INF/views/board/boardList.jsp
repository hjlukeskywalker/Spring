<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="demoTest"/>
</jsp:include>
<style>
 button#btn-add{
 	float:right;
 	margin:0 0 15px;
 }
</style>
<section id="board-container" class="container">
        <button id="btn-add" class="btn btn-outline-success" onclick="location.assign('${path}/board/boardForm.do;')">글쓰기</button>
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:forEach var="b" items="${list }">
            	<tr>
            		<td><c:out value="${b['BOARDNO'] }"/></td>
            		<td>
            		<a href="${path }/board/boardView.do?no=${b['BOARDNO'] }">
            			<c:out value="${b['BOARDTITLE'] }"/>
            		</a>
            		</td>	
            		<td><c:out value="${b['BOARDWRITER'] }"/></td>
            		<td><c:out value="${b['BOARDDATE'] }"/></td>
            		<td><c:out value="${b['BOARDCONTENT'] }"/></td>
            		<td><c:out value="${b['BOARDREADCOUNT'] }"/></td>
            	</tr>
 			</c:forEach>	            	
            	

        </table>
        <div id="pagebar_container">
        	${requestScope.pagebar }
        </div> 
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
