<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="/css/board/board-update.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div id="container">
		<div class="board-list">
			<table class="board-list-table">
				<tr>
					<th>제목</th>
					<td><input type="text" class="input-items"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" class="input-items" readonly="readonly"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="textarea-item"></textarea></td>
				</tr>
			</table>
			
		</div>
		<div class="option-buttons">
			<button type="button" class="submit-btn">수정하기</button>
			<button type="button" class="cancel-btn">취소</button>
		</div>
	</div>
	<script src="/js/board-update.js"></script>
</body>
</html>