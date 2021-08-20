export default class ReservationDate {
    constructor(reservationDate) {
		this.init(reservationDate);
	}

    init(reservationDate) {
		const reservationDateInput = document.querySelector("#reservationDate");
		reservationDateInput.value = reservationDate;
		this.setReservationDate(reservationDate);
    }

	setReservationDate(reservationDate) {
		const reservationDateBox = document.querySelector(".inline_txt .date");
		reservationDateBox.innerText = this.dateParsing(reservationDate);
	}

	dateParsing(reservationDate) {
		const splitedRawDate = reservationDate.split("-");
		
		let date = ""
		splitedRawDate.forEach(dateElement => {
			date += `${parseInt(dateElement)}.`;
		})
		
		return date;
	}
}