<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/auth/signin" method="post"> <!-- SecurityConfig.java에 post요청 존재 -->
		<input type="text" name="username" placeholder="아이디"><br>
		<input type="password" name="password" placeholder="패스워드"><br>
		<button>로그인</button>
	</form>
	<a href="/oauth2/authorization/google">구글 로그인</a>
	<a href="/oauth2/authorization/naver">네이버 로그인</a>
</body>
</html>