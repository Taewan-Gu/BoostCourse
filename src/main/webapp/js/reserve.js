import { parameter } from "./common/parameter.js";
import { URL } from "./common/urlMapper.js";
import MoveTop from "./common/moveTop.js";

import Validation from "./util/validation.js";
import ProductSummary from "./reserve/productSummary.js";
import ProductPrices from "./reserve/productPrices.js";
import TicketController from "./reserve/ticketController.js";
import ReservationConfirm from "./reserve/reservationConfirm.js";
import AnchorButtons from "./reserve/anchorButtons.js";
import Agreement from "./reserve/agreement.js";
import ReservationDate from "./reserve/reservationDate.js";


document.addEventListener("DOMContentLoaded", () => {
	new Reserve();
	new MoveTop();
});


class Reserve {
	constructor() {
		this.init();
	}
	
	init() {
		const displayInfoId = parameter.getDisplayInfoId();
		const query = `?displayInfoId=${displayInfoId}`;
		// 예약 폼 밸리데이션
		const validation = new Validation();
		validation.initReserve();
		
		// 상품 상세정보 가져오기
		fetch(URL.reserve + query)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				new ProductSummary(data.productSummary);
				new ProductPrices(data.productPrices);
				new TicketController();
				new ReservationDate(data.reservationDate);
				new ReservationConfirm(validation);
			})
		    .catch(error => {
		    	console.error(error);
		    })
		
		new Agreement();
		new AnchorButtons(displayInfoId);
	}
}