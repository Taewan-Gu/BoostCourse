export default class Reservations {
    constructor(reservations) {
		this.init(reservations);
	}

    init(reservations) {
		this.setReservations(reservations);
    }

	setReservations(reservations) {		
		const confirmReservationTemplate = document.querySelector("#confirm").innerText;
		const completeReservationTemplate = document.querySelector("#complete").innerText;
		const cancelReservationTemplate = document.querySelector("#cancel").innerText;
		const bindConfirmReservationTemplate = Handlebars.compile(confirmReservationTemplate);
		const bindCompleteReservationTemplate = Handlebars.compile(completeReservationTemplate);
		const bindCancelReservationTemplate = Handlebars.compile(cancelReservationTemplate);
		
		let confirmReservationHtml = "";
		let completeReservationHtml = "";
		let cancelReservationHtml = "";

		reservations.forEach(reservation => {
			reservation.totalPrice = reservation.totalPrice.toLocaleString("ko-KR");
			if (reservation.cancelFlag) {
				return cancelReservationHtml += bindCancelReservationTemplate(reservation);
			}
			
			const reservationDate = new Date(reservation.reservationDate);
			if (Date.now() < reservationDate.getTime()) {
				return confirmReservationHtml += bindConfirmReservationTemplate(reservation);
			}
			return completeReservationHtml += bindCompleteReservationTemplate(reservation);
		})
		
		const confirmReservationContainer = document.querySelector(".confirmed");
		const completeReservationContainer = document.querySelector(".used");
		const cancelReservationContainer = document.querySelector(".cancel");
		
		confirmReservationContainer.innerHTML += confirmReservationHtml;
		completeReservationContainer.innerHTML += completeReservationHtml;
		cancelReservationContainer.innerHTML += cancelReservationHtml;
	}
}