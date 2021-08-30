import { BASE_URL, URL } from "../common/urlMapper.js";

export default class LoginCheck {
	constructor() {
		this.init();
	}
	
	init() {
		const reservationCheckButton = document.querySelector(".btn_my");
		const reservationEmailBox = reservationCheckButton.querySelector(".viewReservation");
		
		// 로그인 체크
		fetch(URL.loginCheck, {
				method: "POST",
			})
		    .then(response => {
				if (response.status == 204) {
					if (location.pathname == "/myreservation.html") {
						location.href = `${BASE_URL}bookinglogin.html`;
					}
					reservationCheckButton.href = `${BASE_URL}bookinglogin.html`;
					reservationEmailBox.innerText = "예약확인";
					return;
				}
				return response.json();
			})
			.then(data => {
				if (data) {
					reservationCheckButton.href = `${BASE_URL}myreservation.html`;
					reservationEmailBox.innerText = data.email;	
				}
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
}