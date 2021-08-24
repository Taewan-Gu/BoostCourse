import { BASE_URL } from "../common/urlMapper.js";
import { cookie } from "../common/cookie.js";

export default class LoginCheck {
	constructor() {
		this.init();
	}
	
	init() {
		// 비회원 로그인 되어 있는지 확인 후, 링크 주소 변경 및 유지 & 기본 쿠키 지속시간 30분
		const reservationCheckButton = document.querySelector(".btn_my");
		const reservationEmailBox = reservationCheckButton.querySelector(".viewReservation");
		const loginEmail = cookie.getCookie("email");
		
		if (loginEmail) {
			reservationCheckButton.href = `${BASE_URL}myreservation.html`
			reservationEmailBox.innerText = loginEmail;
		} else {
			reservationCheckButton.href = `${BASE_URL}bookinglogin.html`
			reservationEmailBox.innerText = "예약확인";
		}
	}
}