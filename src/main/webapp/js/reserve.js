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
	new Reserve();							// 예약하기 페이지
	new MoveTop();							// top 버튼
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
				// 상품예약 설명 배치
				new ProductSummary(data.productSummary);
				// 상품가격 배치
				new ProductPrices(data.productPrices);
				// 티켓 갯수 컨트롤러 생성
				new TicketController();
				// 예매일 설정
				new ReservationDate(data.reservationDate);
				// 예약 승인
				new ReservationConfirm(validation);
			})
		    .catch(error => {
		    	console.error(error);
		    })
		
		new Agreement();						// 약관 펼쳐보기
		new AnchorButtons(displayInfoId);		// 뒤로가기 버튼
	}
}