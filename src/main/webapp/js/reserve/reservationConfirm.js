export default class ReservationConfirm {
    constructor(validation) {
		this.init(validation);
	}

    init(validation) {
		this.setConfirmButton(validation);
    }
	
	setConfirmButton(validation) {
		const reservationConfirmButton = document.querySelector(".bk_btn_wrap");
		
		reservationConfirmButton.addEventListener("click", () => {
			// disable일 때 버튼은 작동 X
			if (reservationConfirmButton.classList.contains("disable")) {
				return
			}
			
			if (validation.isNotValidatedName()) {
				return alert("이름을 입력해주세요")
			}
			
			if (validation.isNotValidatedTel()) {
				return alert("전화번호 형식을 맞춰주세요")
			}
			
			if (validation.isNotValidatedEmail()) {
				return alert("이메일 형식을 맞춰주세요")
			}
			
			if (validation.isNotValidatedTicketCount()) {
				return alert("티켓을 선택해주세요")
			}
			const sendReservationButton = reservationConfirmButton.querySelector("button");
			sendReservationButton.type = "submit";
			sendReservationButton.click();
		})
	}
}