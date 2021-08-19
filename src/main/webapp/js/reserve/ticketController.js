export default class TicketController {
	
	totalTicketCount = 0;
	
    constructor(productPrices) {
		this.initTicketCountControl(productPrices);
	}
	
	initTicketCountControl(productPrices) {
		const ticketContainers = document.getElementsByClassName("count_control");
		
		for (let i = 0; i < ticketContainers.length; i++) {
			// 가격 콤마 제거
			const price = productPrices[i].price.replace(",", "");
			
			const ticketCount = ticketContainers[i].querySelector(".count_control_input");
			const individualTotalPriceContainer = ticketContainers[i].querySelector(".individual_price");
			const individualTotalPrice = individualTotalPriceContainer.querySelector(".total_price");
			
			const minusButton = ticketContainers[i].querySelector(".ico_minus3");
			minusButton.addEventListener("click", () => {
				// 0이 아닐경우 카운트 마이너스 가능.
				if (ticketCount.value != 0) {
					ticketCount.value = Number(ticketCount.value) - 1;
					
					// 예약 페이지 제일 하단의 총 티켓 갯수 세팅
					this.totalTicketCount--;
					this.setReservationTicketCount();
				}
				
				// 0이 될 경우, 버튼과 티켓 수 disabled 및 가격 컬러 off
				if (ticketCount.value == 0) {
					minusButton.classList.add("disabled");
					ticketCount.classList.add("disabled");
					individualTotalPriceContainer.classList.remove("on_color");
				}
				
				individualTotalPrice.innerText = (price * ticketCount.value).toLocaleString("Ko-KR");
			})
			
			const plusButton = ticketContainers[i].querySelector(".ico_plus3");
			plusButton.addEventListener("click", () => {
				// 플러스 버튼을 누를 경우 disabled 제거 및 가격 컬러 on
				minusButton.classList.remove("disabled");
				ticketCount.classList.remove("disabled");
				individualTotalPriceContainer.classList.add("on_color");
				ticketCount.value = Number(ticketCount.value) + 1;
				
				individualTotalPrice.innerText = (price * ticketCount.value).toLocaleString("Ko-KR");
				
				this.setReservationTicketCount();
				
				// 예약 페이지 제일 하단의 총 티켓 갯수 세팅
				this.totalTicketCount++;
				this.setReservationTicketCount();
			})
		}
	}
	
	setReservationTicketCount() {
		const totalTicketCountBox = document.getElementById("totalCount");
		totalTicketCountBox.innerText = this.totalTicketCount;
	}
}