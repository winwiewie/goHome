<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tg.mymember.MyMemberDto" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	function backPageFnc() {
		location.href='./mylist';
	}

	function deleteUsrFnc() {
		var obj = document.getElementById('no');
		
		var memberNo = obj.value;
		
		location.href = './delete?no=' + memberNo;
	}
</script>
<head>
<meta charset="UTF-8">
<title>회원정보 조회</title>
</head>
<body>

	<jsp:include page="/Header.jsp"/>

	<%	
// 		ArrayList<MemberDto> memberList = (ArrayList<MemberDto>)request.getAttribute("memberList");
// 		MemberDto memberDto = memberList.get(0);
//		회원정보 조회는 한명만 조회하기 때문에 어레이리스트가 필요없음

		MyMemberDto myMemberDto = (MyMemberDto)request.getAttribute("myMemberDto");
	%>

	<h1>회원수정</h1>
	<form action="./update" method="post"> <!-- 업데이트쪽에잇는 파일을 찾아서 post의 정보를 입력 (post가 정보보안이 좋음) -->
		번호: <input type="text" name='no' value='<%=myMemberDto.getNo()%>' readonly="readonly"><br/>
		이름: <input type="text" name='name' value='<%=myMemberDto.getName()%>'><br/>
		이메일: <input type="text" name='email' value='<%=myMemberDto.getEmail()%>'><br/>
		가입일: <%=myMemberDto.getCreateDate()%><br/>
		<input type ="submit" value ='수정'>
		<input type ="button" value ='삭제' onclick="location.href='./delete?no=<%=myMemberDto.getNo()%>'">
		<input type ="button" value ='뒤로가기' onclick="location.href='./mylist'"><br/>
		<input type ="button" value ='뒤로가기(함수로 한거)' onclick="backPageFnc();">
	</form>

	<jsp:include page = "/Tail.jsp"/>
		
</body>
</html>