<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/account/mypage.css">
</head>
<body>
	<div id="container">
		<form action="" enctype="multipart/form-data">
			<div class="user-info">
				<div class="profile-img">
					<img class="profile-img-url" alt="profile">
				</div>
				<div class="username-text">username</div><br>
				<input type="file" class="file-input" name="file">
			</div>
		</form>

		<div>
			<div class="input-items">
				<input type="text" class="text-inputs" placeholder="username">
			</div>
			<div class="input-items">
				<input type="text" class="text-inputs" placeholder="email">
			</div>
			<div class="input-items">
				<input type="text" class="text-inputs" placeholder="name">
			</div>
		</div>
		<div>
			<button>수정하기</button>
			<button>취소</button>
			<button>회원탈퇴</button>
		</div>
	</div>
	<script type="text/javascript" src="/js/authentication/principal.js"></script>
	<script type="text/javascript" src="/js/account/mypage.js"></script>
</body>
</html>