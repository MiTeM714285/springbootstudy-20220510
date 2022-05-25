const submitBtn = document.querySelector('.submit-btn');
const inputItems = document.querySelectorAll('.input-items');
const textareaItem = document.querySelector('.textarea-item');



submitBtn.onclick = () => {
	submit();
}

/*
function submit() {
	$.ajax({
		type: "post",
		url: '/board',
		data: JSON.stringify({
			title : inputItems[0].value,
			usercode : inputItems[1].value,
			content : textareaItem.value
		}),
		contentType: "application/json",
		dataType: "text",
		success: (data) => {
			alert("게시글 작성 완료")
			let dataObj = JSON.parse(data)
			location.href="/board/dtl/" + dataObj.data; // 해당 게시글의 dtl로 이동
		},
		error: () => {
			alert("비동기 처리 오류");
		}
	});
}
*/

/*
	Promise
*/

function test(data) {
	return new Promise((resolve, reject) => {
		if(data > 100) {
			resolve(data); // then
		} else {
			throw reject(new Error('data가 100보다 작거나 같습니다'));
		}
		
	});
}

test(50)
.then(testData => {alert(testData)})
.catch(error => {console.log(error)});

function submit() {
	let url = "/api/board";
	
	let option = {
		method: "POST",
		headers : {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify ({
			title : inputItems[0].value,
			usercode : inputItems[1].value,
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