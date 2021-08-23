const NO_RESERVATION_COUNT = 0;

export default class ReservationCount {
    constructor(reservationCount) {
		this.init(reservationCount);
	}

    init(reservationCount) {
		this.setReservationCount(reservationCount);
		this.setReservationCountEventButtons();
		
		const reservationList = document.querySelector(".list_cards");
		const emptyReservation = document.querySelector(".err");
		
		// 예약이 없을 때
		if (reservationCount.totalSize == NO_RESERVATION_COUNT) {
			reservationList.style.display = "none";
			emptyReservation.style.display = "block";
			return
		}
		
		// 예약이 있을 때
		reservationList.style.display = "block";
		emptyReservation.style.display = "none";
    }

	setReservationCount(reservationCount) {
		const reservationCountContainer = document.querySelector(".summary_board");
		
		const reservationCountTemplate = document.querySelector("#reservationCount").innerText;
		const bindReservationCountTemplate = Handlebars.compile(reservationCountTemplate);
		
		let reservationCountHtml = "";

		reservationCountHtml += bindReservationCountTemplate(reservationCount);
		
		reservationCountContainer.innerHTML += reservationCountHtml;
	}
	
	setReservationCountEventButtons() {
		const reservationCountContainer = document.querySelector(".summary_board");
		
		// event delegation 안에서 this를 사용하기 위해
		const self = this;
		
		reservationCountContainer.addEventListener("click", event => {
			const target = event.target;
			
			if (target.tagName === 'A') {
				self.changeReservationStatus(reservationCountContainer, target);
			}
			
			// A의 안 타겟들도 예약 상태 변경 가능.
			if (target.tagName === 'EM' || target.tagName === 'SPAN' || target.tagName === 'I') {
				self.changeReservationStatus(reservationCountContainer, target.parentElement);
			}
		})
	}
	
	changeReservationStatus(reservationCountContainer, target) {
		const reservationCountBoxs = reservationCountContainer.getElementsByClassName("link_summary_board");
		for (let reservationCountBox of reservationCountBoxs) {
			reservationCountBox.classList.remove("on");
		}
		target.classList.add("on");
		
		const reservationList = document.querySelector(".list_cards");
		const emptyReservation = document.querySelector(".err");
		
		// 해당 예약 상태 건이 아무 것도 없을 때
		if (target.querySelector(".figure").innerText == NO_RESERVATION_COUNT) {
			reservationList.style.display = "none";
			emptyReservation.style.display = "block";
			return
		}
		
		// 예약이 있을 때
		reservationList.style.display = "block";
		emptyReservation.style.display = "none";
		
		// 모든 예약
		if (target.id === "all_reservations") {
			for(let reservationStatusContainer of reservationList.children) {
				 reservationStatusContainer.style.display = "block";
			}
			return
		}
		
		// 각 예약 상태들마다 해당 건들 배치
		for(let reservationStatusContainer of reservationList.children) {
			console.log(reservationStatusContainer.classList)
			if (reservationStatusContainer.classList.contains(target.id)) {
				reservationStatusContainer.style.display = "block";
				continue
			}
			reservationStatusContainer.style.display = "none";
		}
	}
}