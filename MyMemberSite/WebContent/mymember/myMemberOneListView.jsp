<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tg.mymember.MyMemberDto" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/Header.jsp"/>
	
	<h2>회원 목록</h2>
	<div>
		<a href="./add">신규회원</a>
	</div>
	
		<!-- scriptlet 스크립틀릿  -->
	<% 		
		MyMemberDto myMemberDto = (MyMemberDto)request.getAttribute("myMemberDto");
	%>
	
	<%=myMemberDto.getNo()%>,
	<a href='./update?no=<%=myMemberDto.getNo()%>'><%=myMemberDto.getName()%></a>,
	<%=myMemberDto.getEmail()%>,
	<%=myMemberDto.getPassword()%>,
	<%=myMemberDto.getCreateDate()%>,
	<%=myMemberDto.getModifiedDate()%>
	<a href='./delete?no=<%=myMemberDto.getNo()%>'>[삭제]</a>
	<br>
	
	
	
	<jsp:include page = "/Tail.jsp"/>

</body>
</html>