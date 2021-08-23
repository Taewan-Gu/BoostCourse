import { cookie } from "./common/cookie.js";
import { moveTop } from "./common/moveTop.js";
import { URL } from "./common/urlMapper.js";

import ReservationCount from "./myreservation/reservationCount.js";
import Reservations from "./myreservation/reservations.js";
import Cancel from "./myreservation/cancel.js";

document.addEventListener("DOMContentLoaded", () => {
	new MyReservation();
	
	moveTop.initMoveTopButton();				// top 버튼
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
		
		this.setReservationEmail(reservationEmail);
	}
	
	setReservationEmail(reservationEmail) {
		const topReservationEmail = document.querySelector(".viewReservation");
		topReservationEmail.innerText = reservationEmail;
	}
}