import { URL } from "../common/urlMapper.js";

// 프로모션 캐러셀(무한 슬라이드) 컨테이너 선택
const promotionsContainer = document.getElementsByClassName("visual_img")[0];

function moveCarouselFirstToLast() {
	const firstNode = promotionsContainer.firstChild;
	const lastNode = promotionsContainer.removeChild(firstNode);
	promotionsContainer.appendChild(lastNode);	
}

// 프로모션 캐러셀(무한 슬라이드) 화면 이동
function moveCarousel() {
	promotionsContainer.style.transition = "0.5s";
	promotionsContainer.style.transform = "translateX(-100%)";
	
	promotionsContainer.ontransitionend = () => {
		promotionsContainer.removeAttribute("style")
		
		moveCarouselFirstToLast();			// promotionNode 이동
	}
}

// 각 프로모션을 보여줄 박스 생성 및 프로모션 데이터 입력 후 배치
function arrangePromotions(promotions) {
	const promotionTemplate = document.querySelector("#promotionItem").innerText;
	const bindPromotionTemplate = Handlebars.compile(promotionTemplate);
	
	let promotionHtml = promotions.reduce(function(prev, next) {
	  	return prev + bindPromotionTemplate(next);
	}, "");
	
	promotionsContainer.innerHTML = promotionHtml;
	
	// textNode 삭제
	for (let i = 0; i < promotions.length * 2 + 1; i++) {
		if (i % 2 == 0) {
			const textNode = promotionsContainer.firstChild;
			promotionsContainer.removeChild(textNode);
		} else {
			moveCarouselFirstToLast();
		}
	}
}

function initPromotionCarousel() {
	// 프로모션 데이터 가져오기
	fetch(URL.promotions)
	    .then(response => {
	      	return response.json();
	    })
		.then(data => {
			arrangePromotions(data.promotions);
			setInterval(moveCarousel, 2000);
		})
	    .catch(error => {
	    	console.error(error);
	    })
}

export { initPromotionCarousel };