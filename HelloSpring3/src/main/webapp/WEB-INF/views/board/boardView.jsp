<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="HelloSpring"/>
</jsp:include>
 <style>
    div#board-container{width:400px; margin:0 auto; text-align:center;}
    div#board-container input,div#board-container button{margin-bottom:15px;}
    div#board-container label.custom-file-label{text-align:left;}
</style>
<section id="container">
 <div id="board-container">
		
        <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" value="${board.boardTitle }"  required>
        <input type="text" class="form-control" name="boardWriter" value="${board.boardWriter }"  readonly required>
		<c:if test="${board.attachments.size()>0 }">
			<c:forEach var="a" items="${board.attachments }" varStatus="vs">
                    <button type="button" 
                    class="btn btn-outline-success btn-block"
                    onclick="location.assign('${path}/board/fileDownload.do?oriname=${a.originalFilename }&rename=${a.renamedFilename }')">
                    <c:out value="첨부파일 ${vs.count }-${a.originalFilename }"/>
            </button>
        </c:forEach>
        </c:if>
        
        <textarea class="form-control" name="boardContent" placeholder="내용" required></textarea>
    </div>

</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
