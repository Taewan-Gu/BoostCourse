import { URL } from "../common/urlMapper.js";

export default class Cancel {
    constructor() {
		this.init();
	}

    init() {
		this.setBookingCancelButtons();
		this.initCancelPopup();
	}
	
	setBookingCancelButtons() {
		const confirmReservationsContainer = document.querySelector(".confirmed");
		confirmReservationsContainer.addEventListener("click", event => {
			// 취소 버튼 클릭 시
			if (event.target.classList.contains("booking_cancel_btn")) {
				const cancelPopup = document.querySelector(".popup_booking_wrapper");
				cancelPopup.style.display = "block";
				
				// reservationInfoId 쿠키 저장
				const reservationInfoId = event.target.id;
				localStorage.setItem("reservationInfoId", reservationInfoId);
				
				this.setCancelSummary(reservationInfoId);
			}
		})
	}
	
	initCancelPopup() {
		const cancelPopup = document.querySelector(".popup_booking_wrapper");
		
		const rejectReservationCancelButton = document.querySelector(".btn_gray");
		rejectReservationCancelButton.addEventListener("click", () => {
			cancelPopup.style.display = "none";
		})
		
		const closeReservationCancelButton = document.querySelector(".popup_btn_close");
		closeReservationCancelButton.addEventListener("click", () => {
			cancelPopup.style.display = "none";
		})
		
		const confirmReservationCancelButton = document.querySelector(".btn_green");
		confirmReservationCancelButton.addEventListener("click", () => {
			const reservationInfoId = localStorage.getItem("reservationInfoId");
			const path = `/${reservationInfoId}`;
			
			fetch(URL.reservations + path, {
					method: "PUT"
				})
			    .then(() => {
					this.changeReservationStatus(reservationInfoId);
				})
			    .catch(error => {
			    	console.error(error);
			    })
			
			cancelPopup.style.display = "none";
		})
	}
	
	setCancelSummary(reservationInfoId) {
		// 화면 CancelSummary 배치
		const reservation = document.querySelector(`#reservation_${reservationInfoId}`);
		
		const reservationTitle = reservation.querySelector(".tit").innerText;
		const reservationDate = reservation.querySelector(".item_dsc").innerText;
		
		const cancelReservationBox = document.querySelector(".pop_tit");
		const cancelReservationTitle = cancelReservationBox.querySelector("span");
		const cancelReservationDate = cancelReservationBox.querySelector("small");
		
		cancelReservationTitle.innerText = reservationTitle;
		cancelReservationDate.innerText = reservationDate;
	}
	
	changeReservationStatus(reservationInfoId) {
		// 취소 후, 예약 카드 내용 변경
		const bookingCancelButton = document.getElementById(reservationInfoId);
		const reservation = document.querySelector(`#reservation_${reservationInfoId}`);
		const reservationPriceTitle = reservation.querySelector(".price_tit");
		
		reservationPriceTitle.innerText = "결제 취소금액";
		bookingCancelButton.style.display = "none";
		
		// 취소 후, 상단 전광판 숫자 변경
		const confirmReservationCountContainer = document.querySelector("#confirm_reservations");
		const cancelReservationCountContainer = document.querySelector("#cancel_reservations");
		const confirmReservationCount = confirmReservationCountContainer.querySelector(".figure");
		const cancelReservationCount = cancelReservationCountContainer.querySelector(".figure");
		confirmReservationCount.innerText = Number(confirmReservationCount.innerText) - 1;
		cancelReservationCount.innerText = Number(cancelReservationCount.innerText) + 1;
		
		const cancelReservationContainer = document.querySelector(".cancel");
		cancelReservationContainer.appendChild(reservation);
	}
}