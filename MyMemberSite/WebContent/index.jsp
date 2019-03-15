<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href='./mymember/mylist'>연습 페이지로 이동</a>
	
	<form action="./mymember/onelist" method="get">
		검색할 아이디: <input type="text" 
			name="email" placeholder="이메일을 입력해주세요">
		비밀번호을 입력하세요: <input type="text" name="password" placeholder="비밀번호을 입력해주세요">
		
		<input type="submit" value="검색">
	</form>
</body>
</html>