import { BASE_URL } from "./common/urlMapper.js";
import { cookie } from "./common/cookie.js";

document.addEventListener("DOMContentLoaded", () => {
	const loginButton = document.querySelector(".login_btn");
	
	// 비회원 로그인 시, 쿠키 저장.
	loginButton.addEventListener("click", () => {
		const loginEmailInput = document.querySelector(".login_input");
		const loginEmail = loginEmailInput.value;
		
		cookie.setCookie("email", loginEmail);
		location.href = BASE_URL + "myreservation.html"; 
	})
});