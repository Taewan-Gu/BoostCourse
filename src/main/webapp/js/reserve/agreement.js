export default class Agreement {
    constructor() {
		this.init();
	}

    init() {
		this.setReservationDate();
		this.setAgreementFoldButtons();
		this.setAgreementConfirmButton();
    }

	setAgreementFoldButtons() {
		const agreements = document.getElementsByClassName('agreement_content');
		for (let agreement of agreements) {
			const agreementFoldButton = agreement.querySelector('.btn_agreement')
			
			agreementFoldButton.addEventListener('click', () => {
				// open이 있다면 제거, 없다면 추가 및 아이콘 up, down 변경
				if (agreement.classList.contains('open')) {
					agreement.classList.remove('open')
					agreementFoldButton.querySelector('i').className = "fn fn-down2"	
				} else {
					agreement.classList.add('open')
					agreementFoldButton.querySelector('i').className = "fn fn-up2"
				}
			})
		}
	}
	
	setAgreementConfirmButton() {
		const agreementConfirmButton = document.getElementById("chk3");
		const reservationConfirmButton = document.querySelector(".bk_btn_wrap");
		agreementConfirmButton.addEventListener("click", () => {
			if (agreementConfirmButton.checked) {
				reservationConfirmButton.classList.remove("disable")
			} else {
				reservationConfirmButton.classList.add("disable")
			}
		})
	}

	setReservationDate() {
		const reservationDate = document.querySelector(".inline_txt .date");
		reservationDate.innerText = this.dateParsing(Date.now());
	}

	dateParsing(timeStamp) {
		const rawDateTime = new Date(timeStamp);
		const rawDate = rawDateTime.toISOString().split("T")[0];
		const splitedRawDate = rawDate.split("-");
		
		let date = ""
		splitedRawDate.forEach(dateElement => {
			date += `${parseInt(dateElement)}.`;
		})
		
		return date;
	}
}