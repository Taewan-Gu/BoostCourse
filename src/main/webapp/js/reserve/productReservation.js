import { URL } from "../common/urlMapper.js";
import ticketController from "./ticketController.js";

export default class ProductReservation {
	
	priceTypeNameTable = {
		"A": "성인(만 19~64세)",
		"Y": "청소년(만 13~18세)",
		"B": "어린이(만 4~12세)",
		"D": "장애인",
		"E": "65세 이상",
		"S": "S석",
		"V": "VIP석",
		"R": "R석",
		"ASeat": "A석"
	}
	
    constructor(displayInfoId) {
		this.render(displayInfoId);
	}
	
    render(displayInfoId) {
		const query = `?displayInfoId=${displayInfoId}`;
		
		// 상품 상세정보 가져오기
		fetch(URL.reserve + query)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				// 상품예약 설명 배치
				this.arrangeProductReservation(data.productReservation);
				// 상품가격 배치
				this.arrangeProductPrices(data.productPrices);
				// 티켓 갯수 컨트롤러 생성
				new ticketController(data.productPrices);
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
	
	arrangeProductReservation(productReservation) {
		// 타이틀
		const navTitle = document.querySelector(".title");
		const previewTitle = document.querySelector(".preview_txt_tit");
		const inTitle = document.querySelector(".in_tit");
		navTitle.innerText = productReservation.title;
		previewTitle.innerText = productReservation.title;
		inTitle.innerText = productReservation.title;
		
		// 장소
		const place = document.querySelector(".place_dsc");
		place.innerText = productReservation.place;
		
		// 관람시간 및 기간
		const openingHours = document.querySelector(".opening_hours_dsc");
		openingHours.innerText = productReservation.openingHours		
	}
	
	arrangeProductPrices(productPrices) {
		// 최소 가격 표시
		this.arrangeMinPrice(productPrices);
		
		// 가격 설명
		this.arrangePriceDescription(productPrices);
	}
	
	arrangeMinPrice(productPrices) {
		const minPriceContainer = document.querySelector(".preview_txt_dsc");
		const minPrice = productPrices.reduce((prev, next) => {
			return {"price": Math.min(prev.price, next.price)};
		});
		minPriceContainer.innerText = `₩${minPrice.price.toLocaleString("ko-KR")} ~`;
	}
	
	arrangePriceDescription(productPrices) {	
		const priceDescription = document.querySelector(".price_dsc");
		const ticketContainer = document.querySelector(".ticket_body");
		
		const ticketTemplate = document.querySelector("#ticket").innerText;
		const bindTicketTemplate = Handlebars.compile(ticketTemplate);
		
		let ticketHtml = "";
		
		productPrices.forEach(productPrice => {
			const priceTypeName = this.priceTypeNameTable[productPrice.priceTypeName];
			const price = productPrice.price.toLocaleString("ko-KR");
			priceDescription.innerText += `${priceTypeName}: ${price}원 \n`;
			
			productPrice.priceTypeName = priceTypeName;
			productPrice.price = price;
			ticketHtml += bindTicketTemplate(productPrice);
		})
		
		ticketContainer.innerHTML += ticketHtml;
	}
}