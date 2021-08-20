export default class TicketController {
	
    constructor() {
		this.initTicketCountControl();
	}
	
	initTicketCountControl() {
		const ticketContainers = document.getElementsByClassName("qty");
		
		for (let ticketContainer of ticketContainers) {
			// 가격 콤마 제거
			const price = ticketContainer.querySelector(".price").innerText.replace(",", "");
			const ticketCount = ticketContainer.querySelector(".count_control_input");
			const individualTotalPriceContainer = ticketContainer.querySelector(".individual_price");
			const individualTotalPrice = individualTotalPriceContainer.querySelector(".total_price");
			
			const minusButton = ticketContainer.querySelector(".ico_minus3");
			minusButton.addEventListener("click", () => {
				// 0이하일 경우, 버튼과 티켓 수 disabled 및 가격 컬러 off
				if (ticketCount.value <= 0) {
					minusButton.classList.add("disabled");
					ticketCount.classList.add("disabled");
					individualTotalPriceContainer.classList.remove("on_color");
					return
				}
				
				ticketCount.value = Number(ticketCount.value) - 1;
				
				individualTotalPrice.innerText = (price * ticketCount.value).toLocaleString("Ko-KR");
				
				// 예약 페이지 제일 하단의 총 티켓 갯수 세팅
				this.setReservationTicketCount();
			})
			
			const plusButton = ticketContainer.querySelector(".ico_plus3");
			plusButton.addEventListener("click", () => {
				// 플러스 버튼을 누를 경우 disabled 제거 및 가격 컬러 on
				minusButton.classList.remove("disabled");
				ticketCount.classList.remove("disabled");
				individualTotalPriceContainer.classList.add("on_color");
				ticketCount.value = Number(ticketCount.value) + 1;
				
				individualTotalPrice.innerText = (price * ticketCount.value).toLocaleString("Ko-KR");
				
				this.setReservationTicketCount();
				
				// 예약 페이지 제일 하단의 총 티켓 갯수 세팅
				this.setReservationTicketCount();
			})
		}
	}
	
	setReservationTicketCount() {
		const totalTicketCountBox = document.getElementById("totalCount");
		const ticketContainers = document.getElementsByClassName("count_control");
		
		let totalTicketCount = 0;
		for(let ticketContainer of ticketContainers) {
			const ticketCount = ticketContainer.querySelector(".count_control_input");
			totalTicketCount += Number(ticketCount.value);
		}
		
		totalTicketCountBox.innerText = totalTicketCount;
	}
}