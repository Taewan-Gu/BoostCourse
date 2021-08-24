import { cookie } from "./common/cookie.js";
import { URL } from "./common/urlMapper.js";
import MoveTop from "./common/moveTop.js";
import LoginCheck from "./common/loginCheck.js";

import ReservationCount from "./myreservation/reservationCount.js";
import Reservations from "./myreservation/reservations.js";
import Cancel from "./myreservation/cancel.js";


document.addEventListener("DOMContentLoaded", () => {
	new LoginCheck();							// 비회원 로그인 확인
	
	new MyReservation();						// 나의 예약페이지
	
	new MoveTop();								// top 버튼
});


class MyReservation {
	constructor() {
		this.init();
	}
	
	init() {
		const reservationEmail = cookie.getCookie("email");
		const query = `?reservationEmail=${reservationEmail}`
		
		// 상품 상세정보 가져오기
		fetch(URL.reservations + query)
		    .then(response => {
				if (response.redirected) {
					location.href = response.url;
				}
		      	return response.json();
		    })
			.then(data => {
				new ReservationCount(data.reservationCount);
				new Reservations(data.reservations);
				new Cancel();
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
}