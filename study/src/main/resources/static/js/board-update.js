const submitBtn = document.querySelector('.submit-btn');
const cancelBtn = document.querySelector('.cancel-btn');
const inputItems = document.querySelectorAll('.input-items');
const textareaItem = document.querySelector('.textarea-item');
let path = window.location.pathname;
let boardCode = path.substring(path.lastIndexOf("/") + 1); // 요청 주소에서 마지막 숫자만 남기고 다 삭제한 값.
load()

submitBtn.onclick = () => {
	submit();
}

cancelBtn.onclick = () => {
	history.back();
}

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
	inputItems[0].value = data.title;
	inputItems[1].value = data.username;
	textareaItem.textContent = data.content;
}


function submit() {
	let path = window.location.pathname;
	let boardCode = path.substring(path.lastIndexOf("/") + 1); // 요청 주소에서 마지막 숫자만 남기고 다 삭제한 값.
	
	let url = `/api/board/${boardCode}`;
	
	let option = {
		method: "PUT",
		headers : {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify ({
			title : inputItems[0].value,
			content : textareaItem.value
		})
	};
	fetch(url, option)
	.then(response => { // 응답을 받음
		console.log(response);
		if(response.ok) { // 200~299 응답일시
			return response.json();
		} else {
			throw new Error(response.json());
		}
	})
	.then(data =>{location.href="/board-info/" + data.data;})
	.catch(error => console.log(error));
}
