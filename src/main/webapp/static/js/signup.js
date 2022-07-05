const usernameInput = document.querySelector(".username-input");
const usernameCheckMsg = document.querySelector(".username-check-msg");

const inputItems = document.querySelectorAll("table input");
const submitBtn = document.querySelector(".submit-button");

let signupFlag = [false, false, false, false, false];

submitBtn.onclick = () => {
	for(let i = 0; i < inputItems.length; i++ ) {
		if (isEmpty(inputItems[i].value)) {
			alert((i == 0? "이름을 " 
				: i == 1? "이메일을" 
				: i == 2? "사용자 이름을" 
				: "비밀번호를")
				+ " 입력해주세요.");
			
		signupFlag[i] = false;
		return;
		}
		signupFlag[i] = true;
	}	
	
	if(signupFlag[4] == false) {
		alert("사용자이름 중복확인이 필요합니다.");
		return;
	}
	
	if(!signupFlag.includes(false)) {
		submit();
	}
};

usernameInput.onblur = () => {
	let username = usernameInput.value;

	$.ajax({
		type:"get",
		url:`/check/username?username=${username}`,
		dataType:"text", //응답 받을 때의 데이터 형태
		success: (response) => {
			if(response == "true") {
				signupFlag[4] = false;
				usernameCheckMsg.innerHTML = `
					<td colspan="2">${username}은(는) 이미 존재하는 사용자이름 입니다.</td>`;
			} else {
				signupFlag[4] = true;
				usernameCheckMsg.innerHTML = `
					<td colspan="2">${username}은(는) 가입할 수 있는 아이디 입니다.</td>`;				
			}
			alert("요청 후 응답성공 : " + response);
			console.log(response);
		},
		error:errorMessage
	});
}

function submit() {
	$.ajax({
		type: "post",
		url: "/signup",
		data: { //body
			name: inputItems[0].value,
			email: inputItems[1].value,
			username: inputItems[2].value,
			password: inputItems[3].value
		},
		dataType:"text",
		success: (response) => {
			if(response == "true") {
				alert("축하합니다!\n회원가입에 성공하였습니다.");
				location.replace("/signin")
			} else {
				alert("회원가입에 실패하였습니다.\n다시 시도해 주세요.");
				usernameCheckMsg.innerHTML = "";
				document.querySelector("form").reset();
			}
		},
		error: errorMessage
		
	})
}

function errorMessage(response, status, error) {
	alert("요청 실패");
	console.log(request.status); //매개변수로 받아오는 status는 자리 채워주기 용도
	console.log(request.responseText);
	console.log(error);
}

function isEmpty(str) {
	return str == "" || str == null || typeof str == undefined;
}