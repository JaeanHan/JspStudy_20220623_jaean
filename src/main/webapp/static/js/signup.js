const usernameInput = document.querySelector(".username-input");
const usernameCheckMsg = document.querySelector(".username-check-msg");

let signupFlag = false;

usernameInput.onblur = () => {
	let username = usernameInput.value;

	$.ajax({
		type:"get",
		url:`/check/username?username=${username}`,
		dataType:"text", //응답 받을 때의 데이터 형태
		success: (response) => {
			if(response == "true") {
				signupFlag = false;
				usernameCheckMsg.innerHTML = `
					<td colspan="2">${username}은(는) 이미 존재하는 사용자이름 입니다.</td>`;
			} else {
				signupFlag = true;
				usernameCheckMsg.innerHTML = `
					<td colspan="2">${username}은(는) 가입할 수 있는 아이디 입니다.</td>`;				
			}
			alert("요청 후 응답성공 : " + response);
			console.log(response);
		},
		error: (request, status, error) => {
			alert("요청 실패");
			console.log(request.status); //매개변수로 받아오는 status는 자리 채워주기 용도
			console.log(request.responseText);
			console.log(error);
		}
	});
}