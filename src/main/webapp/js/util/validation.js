export default class Validation {
    constructor() {}

    initReserve() {
		this.setNameValidationButton();
		this.setTelValidationButton();
		this.setEmailValidationButton();
    }

	initBookinglogin() {
		this.setEmailValidationButton();
	}
	
	setNameValidationButton() {
		const nameInput = document.getElementById('name');
		const warningText = nameInput.nextElementSibling;
		nameInput.addEventListener("keypress", () => {
			if (this.isNotValidatedName()) {
				warningText.style.display = "block";
			} else {
				warningText.style.display = "none";
			}
		})
	}
	
	setTelValidationButton() {
		const telInput = document.getElementById('tel');
		const warningText = telInput.nextElementSibling;
		telInput.addEventListener("keypress", () => {
			if (this.isNotValidatedTel()) {
				warningText.style.display = "block";
			} else {
				warningText.style.display = "none";
			}
		})
	}
	
	setEmailValidationButton() {
		const emailInput = document.getElementById('email');
		const warningText = emailInput.nextElementSibling;
		emailInput.addEventListener("keypress", () => {
			if (this.isNotValidatedEmail()) {
				warningText.style.display = "block";
			} else {
				warningText.style.display = "none";
			}
		})
	}
	
	isNotValidatedName() {
		const nameInput = document.getElementById('name');
		
		if (nameInput.value.length <= 0) {
			return true;
		}
		return false;
	}
	
	isNotValidatedTel() {
		const telInput = document.getElementById('tel');
		const regexValidationNewTel = /^010-\d{4}-\d{4}$/
		const regexValidationOldTel = /^01[1789]-\d{3}-\d{4}$/
		
		if (regexValidationNewTel.test(telInput.value) || regexValidationOldTel.test(telInput.value)) {
			return false;
		}
		return true;
	}
	
	isNotValidatedEmail() {
		const emailInput = document.getElementById('email');
		// email ID는 4~20자, @ 이후 도메인은 (1~100자).(2~10자)
		const regexValidation = /^[0-9a-zA-Z]{4,20}@[0-9a-zA-Z]{1,100}[.][0-9a-zA-Z]{2,10}$/

		if (regexValidation.test(emailInput.value)) {
			return false;
		} 
		return true;
	}
	
	// 티켓 밸리데이션은 무조건 인풋 값으로 비교, 아니라면 데이터 바꿔서 전송 가능하기 때문
	isNotValidatedTicketCount() {
		const ticketCountInputs = document.getElementsByClassName("count_control_input");
		for (let ticketCountInput of ticketCountInputs) {
			if (ticketCountInput.value > 0) {
				return false;
			}
		}
		return true;
	}
}