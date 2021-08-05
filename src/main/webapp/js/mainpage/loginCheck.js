import { BASE_URL } from "../common/urlMapper.js";
import { getCookie } from "../common/cookie.js";

function initLoginCheck() {
	// 비회원 로그인 되어 있는지 확인 후, 링크 주소 변경 및 유지 & 기본 쿠키 지속시간 1시간
	const reservationCheckButton = document.getElementsByClassName("btn_my")[0];
	const loginEmail = getCookie("email");
	
	if (loginEmail) {
		reservationCheckButton.href = `${BASE_URL}myreservation.html`
		const reservationEmailBox = reservationCheckButton.getElementsByClassName("viewReservation")[0];
		reservationEmailBox.innerText = loginEmail;
	} else {
		reservationCheckButton.href = `${BASE_URL}bookinglogin.html`
		const reservationEmailBox = reservationCheckButton.getElementsByClassName("viewReservation")[0];
		reservationEmailBox.innerText = "예약확인";
	}
}

export { initLoginCheck };