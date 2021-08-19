import { parameter } from "../common/parameter.js";
import { URL } from "../common/urlMapper.js";

import Validation from "./validation.js";

export default class ReservationConfirm {
    constructor(productReservation) {
		this.init(productReservation);
	}

    init(productReservation) {
		this.setConfirmButton(productReservation);
    }
	
	setConfirmButton(productReservation) {
		const reservationConfirmButton = document.querySelector(".bk_btn_wrap");
		
		const validation = new Validation();
		reservationConfirmButton.addEventListener("click", () => {
			// disable일 때 버튼은 작동 X
			if (reservationConfirmButton.classList.contains("disable")) {
				return
			}
			this.sendReservationForm(productReservation);
			
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
			
			this.sendReservationForm(productReservation);
		})
	}
	
	sendReservationForm(productReservation) {
		const nameInput = document.getElementById('name');
		const telInput = document.getElementById('tel');
		const emailInput = document.getElementById('email');
		const dateNow = new Date(Date.now());
		
		const ticketCountInputs = document.getElementsByClassName("count_control_input")
		const productPrices = []
		
		for (let ticketCountInput of ticketCountInputs) {
			const productPrice = {
				productPriceId : ticketCountInput.id,
				count: ticketCountInput.value,
			}
			productPrices.push(productPrice);
		}
		
		const reservationForm = {
			productId: productReservation.productId,
			displayInfoId: parameter.getDisplayInfoId(),
			reservationName: nameInput.value,
			reservationTel: telInput.value,
			reservationEmail: emailInput.value,
			reservationDate: dateNow,
			productPrices: productPrices,
		}
		
		fetch(URL.reserve, {
				method: 'POST',
				headers: {
		            'Content-Type': 'application/json',
		        },
				body: JSON.stringify(reservationForm)
			}).then(response => {
				return response;
			}).catch(error => {
		    	console.error(error);
		    })
	}
}