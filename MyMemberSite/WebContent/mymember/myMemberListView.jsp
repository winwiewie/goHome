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
		ArrayList<MyMemberDto> myMemberList = 
			(ArrayList<MyMemberDto>)request.getAttribute("myMemberList");
	
		for(MyMemberDto memberDto : myMemberList){
	%>
	
	<%=memberDto.getNo()%>,
	<a href='./update?no=<%=memberDto.getNo()%>'><%=memberDto.getName()%></a>,
	<%=memberDto.getEmail()%>,
	<%=memberDto.getPassword()%>,
	<%=memberDto.getCreateDate()%>,
	<%=memberDto.getModifiedDate()%>
	<a href='./delete?no=<%=memberDto.getNo()%>'>[삭제]</a>
	<br>
	
	<%
		}
	%>
	
	<jsp:include page = "/Tail.jsp"/>

</body>
</html>