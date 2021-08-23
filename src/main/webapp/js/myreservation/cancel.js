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
			const target = event.target;
			if (target.classList.contains("booking_cancel_btn")) {
				const cancelPopup = document.querySelector(".popup_booking_wrapper");
				cancelPopup.style.display = "block";
				this.setCancelRequest(target);
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
	}
	
	setCancelRequest(bookingCancelButton) {
		const cancelPopup = document.querySelector(".popup_booking_wrapper");
		
		const confirmReservationCancelButton = document.querySelector(".btn_green");		
		confirmReservationCancelButton.addEventListener("click", () => {
			alert("취소 API 미구현");
			
			const cancelReservationContainer = document.querySelector(".cancel");
			
			const reservationId = bookingCancelButton.id;
			const reservation = document.querySelector(`#reservation_${reservationId}`);
			
			const reservationPriceTitle = reservation.querySelector(".price_tit");

			reservationPriceTitle.innerText = "결제 취소금액";
			bookingCancelButton.style.display = "none";
			
			cancelReservationContainer.appendChild(reservation);
			
			cancelPopup.style.display = "none";
		})
	}
}