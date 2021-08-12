import { URL } from "../common/urlMapper.js";
import { carousel } from "../util/carousel.js";

// 프로모션 캐러셀 컨테이너 선택

export const promotion = {
	promotionsContainer: document.getElementsByClassName("visual_img")[0],

	// 각 프로모션을 보여줄 박스 생성 및 프로모션 데이터 입력 후 배치
	arrangePromotions: function(promotions) {
		const promotionTemplate = document.querySelector("#promotionItem").innerText;
		const bindPromotionTemplate = Handlebars.compile(promotionTemplate);
		
		let promotionHtml = promotions.reduce(function(prev, next) {
		  	return prev + bindPromotionTemplate(next);
		}, "");
		
		this.promotionsContainer.innerHTML = promotionHtml;
	},
	
	initPromotionCarousel: function() {
		// 프로모션 데이터 가져오기
		fetch(URL.promotions)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				this.arrangePromotions(data.promotions);
				carousel.initPromotionCarousel();
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}	
}