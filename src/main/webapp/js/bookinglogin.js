import { BASE_URL } from "./common/urlMapper.js";
import { setCookie } from "./common/cookie.js";

document.addEventListener("DOMContentLoaded", () => {
	const loginButton = document.getElementsByClassName("login_btn")[0];
	
	// 비회원 로그인 시, 쿠키 저장.
	loginButton.addEventListener("click", () => {
		const loginEmailInput = document.getElementsByClassName("login_input")[0];
		const loginEmail = loginEmailInput.value;
		
		setCookie("email", loginEmail);
		location.href = BASE_URL + "myreservation.html"; 
	})
});

