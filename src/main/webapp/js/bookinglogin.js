import { BASE_URL } from "./common/urlMapper.js";
import { cookie } from "./common/cookie.js";
import { moveTop } from "./common/moveTop.js";

document.addEventListener("DOMContentLoaded", () => {
	const loginButton = document.querySelector(".login_btn");
	
	// 비회원 로그인 시, 쿠키 저장. 나중에 세션으로 바꿔야 할 것.
	loginButton.addEventListener("click", () => {
		const loginEmailInput = document.querySelector(".login_input");
		const loginEmail = loginEmailInput.value;
		
		cookie.setCookie("email", loginEmail);
		location.href = BASE_URL + "myreservation.html"; 
	})
	
	moveTop.initMoveTopButton();				// top 버튼
});