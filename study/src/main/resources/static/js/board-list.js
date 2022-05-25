const boardListTable = document.querySelector('.board-list-table');

let nowPage = 1; // 첫 열람시 페이지는 1로
load(nowPage);

/*
function load(page) { // /board/list 요청으로 해당 페이지의 게시글 5개 가져오기
	$.ajax({
		type: "get",
		url: "/board/list",
		data: {
			'page' : page // controller의 getBoardList의 page로 전달
		},
		dataType: "text",
		success:function(data) {
			console.log(data);  // data = CustomResponseDto 전체
			let boardList = JSON.parse(data); // 전달받은 게시글리스트 data 파싱하여 object로 변환
			getBoardList(boardList.data) // 응답받은 CustomResponseDto의 data
			createPageNumber(boardList.data[0].boardCountAll);
			getBoardItems();
		},
		error: function() {
			alert("비동기 처리 오류");
		}
	});
}
*/

function load(page) {
	let url = "/api/board/list?page=" + page;
	// GET은 option 생략가능
	fetch(url)
	.then(response => {
		if(response.ok) {
			return response.json();
		} else {
			throw new Error("비동기 처리 오류");
		}
	})
	.then(result => { // 받아온 ResponseEntity객체.
		getBoardList(result.data) // 응답받은 CustomResponseDto의 data
		createPageNumber(result.data[0].boardCountAll);
		getBoardItems();
	})
	.catch(error => {console.log(error)});
}

function getBoardItems() {
	const boardItems = document.querySelectorAll('.board-items'); // 만들어진 board-items 객체등록
	for (let i=0; i<boardItems.length; i++) {
		boardItems[i].onclick = () => {
			location.href="/board-info/" + boardItems[i].querySelectorAll('td')[0].textContent;
		}
	}
}

function createPageNumber(data) {
	const boardListPage = document.querySelector('.board-list-page');
	const preNextBtn = document.querySelectorAll('.pre-next-btn');
	
	const totalBoardCount = data;
	const totalPageCount = data % 5 == 0 ? data / 5 : (data / 5) + 1;
	
	// 1 => 1,2,3,4,5
	// 6 => 6,7,8,9,10
	
	const startIndex = nowPage % 5 == 0 ? nowPage - 4 : nowPage - (nowPage % 5) + 1;
	const endIndex = startIndex + 4 <= totalPageCount ? startIndex + 4 : totalPageCount;
	
	preNextBtn[0].onclick = () => { // 이전 버튼	
		nowPage = startIndex != 1 ? startIndex - 1 : 1;
		load(nowPage)
	}
	
	preNextBtn[1].onclick = () => { // 다음 버튼
		nowPage = endIndex != totalPageCount ? endIndex + 1 : totalPageCount;
		load(nowPage)
	}
	
	let pageStr = ``
	
	for(let i = startIndex; i <= endIndex; i++) {
		pageStr += `<div>${i}</div>`;
	}
	
	boardListPage.innerHTML = pageStr;
	
	const pageButton = boardListPage.querySelectorAll('div');
	for(let i = 0; i<pageButton.length; i++) {
		pageButton[i].onclick = () => {
			nowPage = pageButton[i].textContent;
			load(nowPage);
		}
	}
}

function getBoardList(data) { // 응답받은 CustomResponseDto의 data로 게시글리스트 출력 메소드
	const tableBody = boardListTable.querySelector('tbody');
	/*
	while(tbody.hasChildNodes()){ // 자식노드인 <tr> 존재시 샘플 게시글 전체 삭제
		tbody.removeChild(tbody.firstChild);
	}
	*/
	let tableStr = ``
	
	for (let i =0; i<data.length; i++) { // 실질적 게시글리스트 출력
		tableStr += `
			<tr class="board-items">
				<td>${data[i].boardCode}</td>
				<td>${data[i].title}</td>
				<td>${data[i].username}</td>
				<td>${data[i].boardCount}</td>
			</tr>`
	}
	
	tableBody.innerHTML = tableStr;
}


















