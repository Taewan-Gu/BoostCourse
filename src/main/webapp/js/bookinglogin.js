import MoveTop from "./common/moveTop.js";

import Validation from "./util/validation.js";
import Login from "./bookinglogin/login.js";

document.addEventListener("DOMContentLoaded", () => {
	// 예약 폼 밸리데이션
	const validation = new Validation();
	validation.initBookinglogin();
	
	new Login(validation);				// 로그인 버튼
	
	new MoveTop();						// top 버튼
});