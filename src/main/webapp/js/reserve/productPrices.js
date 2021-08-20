export default class ProductPrices {
	
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
	
    constructor(productPrices) {
		this.render(productPrices);
	}
	
    render(productPrices) {
		this.arrangeProductPrices(productPrices)
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
		
		productPrices.forEach((productPrice, index) => {
			const priceTypeName = this.priceTypeNameTable[productPrice.priceTypeName];
			const price = productPrice.price.toLocaleString("ko-KR");
			priceDescription.innerText += `${priceTypeName}: ${price}원 \n`;
			
			// hidden form 태그를 위한 index 설정
			productPrice.productPricesIndex = index;
			
			productPrice.priceTypeName = priceTypeName;
			productPrice.price = price;
			ticketHtml += bindTicketTemplate(productPrice);
		})
		
		ticketContainer.innerHTML += ticketHtml;
	}
}