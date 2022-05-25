/**
 * 
 */
 
const usernameText = document.querySelector(".username-text");
const textInputs = document.querySelectorAll(".text-inputs");
const fileInput = document.querySelector(".file-input");
const profileImgUrl = document.querySelector(".profile-img-url")
let usercode = 0;


async function imgSubmit() {
	let formData = new FormData(document.querySelector("form")); // 함수가 호출되면 해당 form을 저장
	
	const url = `/api/v1/user/account/profile/img/${usercode}`;
	const option = {
		method : "PUT",
		headers : {},
		body:formData
	};
	const response = await fetch(url, option);
	if(response.ok) {
		alert("프로필 이미지 변경이 되었습니다.")
		return response.json();
	} else {
		throw new Error("Failed to upload img");
	}
}

fileInput.onchange = () => { // 파일을 새로 등록할시
	let reader = new FileReader();
	reader.onload = (e) => {
		profileImgUrl.src = e.target.result // 새 이미지파일을 profileImgUrl의 src로 지정
		if (confirm("이미지를 변경하시겠습니까?")) {
			const result = imgSubmit();
			console.log(JSON.stringify(result));
		}
	}
	reader.readAsDataURL(fileInput.files[0]); // 업로드 중 맨 처음의 파일로 url을 지정
}

getAuthenticationReq() // authentication/principal.js 의 함수, promise로 return된 값
	.then(result => {
		let principal = result.data.user;
		console.log(result.data.user)
		usernameText.textContent = principal.username;
		textInputs[0].value = principal.username;
		textInputs[1].value = principal.email;
		textInputs[2].value = principal.name;
		usercode = principal.usercode;
		
	})
	.catch(error => {
		console.log(error)
	})
