const boardListTable = document.querySelector('.board-list-table');
const updateBtn =  document.querySelector('.update-btn');
const deleteBtn =  document.querySelector('.delete-btn');
const listBtn =  document.querySelector('.list-btn');
let path = window.location.pathname;
let boardCode = path.substring(path.lastIndexOf("/") + 1); // 요청 주소에서 마지막 숫자만 남기고 다 삭제한 값.
load();

/*
function load() { // /board/list 요청으로 해당 페이지의 게시글 5개 가져오기
	let path = window.location.pathname;
	let boardCode = path.substring(path.lastIndexOf("/") + 1); // 요청 주소에서 마지막 숫자만 남기고 다 삭제한 값.

	$.ajax({
		type: "get",
		url: `/api/board/${boardCode}`,
		dataType: "text",
		success: function(data) {
			let boardObj = JSON.parse(data);
			getBoardDtl(boardObj.data);
			alert(data);
		},
		error: function() {
			alert("비동기 처리 오류");
		}
	});
}
*/

function load() {
	let url = `/api/board/${boardCode}`
	// GET은 option 생략가능
	fetch(url)
	.then(response => {
		if(response.ok) {
			return response.json();
		} else {
			throw new Error("비동기 처리 오류");
		}
	})
	.then(result => {
		let boardObj = result;
		getBoardDtl(boardObj.data);
	})
	.catch(error => {console.log(error)});
}

function getBoardDtl(data) {
	boardListTable.innerHTML = `
	<tr>
		<th>제목</th>
		<td>${data.title}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${data.username}</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${data.boardCount}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${data.content}</td>
	</tr>
	`
}

function deleteBoard(boardCode) { // 게시글 삭제 함수
	let url = `/api/board/${boardCode}`;
	let option = {
		method: "DELETE"
	};
	fetch(url, option)
	.then(response => {
		if(response.ok) {
			return response.json();
		} else {
			throw new Error("비동기 처리 오류");
		}
	})
	.then(data =>{location.replace("/board/list");})
	.catch(error => {console.log(error)});
}

updateBtn.onclick = () => {
	location.href = "/board/" + boardCode;
}

deleteBtn.onclick = () => {
	if(confirm("삭제합니까?")) {
		deleteBoard(boardCode);
	}
}

listBtn.onclick = () => {
	location.href = "/board/list";
}