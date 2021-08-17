import { URL } from "../common/urlMapper.js";
import { carousel } from "../util/carousel.js";

export const productDescription = {
	// 현재 detail 페이지의 displayInfoId 반환
	getDisplayInfoId: () => {
		const parameters = new URLSearchParams(location.search);
		
		return parameters.get("displayInfoId");
	},
	
	// 상품 타이틀 및 이미지 배치
	arrangeProductImagePannel: (productImageUrls, title) => {
		const productImagePannelContainer = document.querySelector(".visual_img");
		
		const productImagePannelTemplate = document.querySelector("#productImages").innerText;
		const bindProductImagePannelTemplate = Handlebars.compile(productImagePannelTemplate);
		
		let productImagePannelHtml = "";
		
		productImageUrls.forEach(productImageUrl => {
			const bindProductImagePannelData = {
				"productImageUrl": productImageUrl,
				"title": title,
			};
			productImagePannelHtml += bindProductImagePannelTemplate(bindProductImagePannelData);
		});
		
		productImagePannelContainer.innerHTML += productImagePannelHtml;
	},
	
	// 캐러셀 작동 유무 결정
	initProductCarousel: (productImageLength) => {
		if (productImageLength < 2) {
			// 버튼 삭제
			const prevButtonContainer = document.querySelector(".prev");
			const nextButtonContainer = document.querySelector(".nxt");
			prevButtonContainer.style.display = "none";
			nextButtonContainer.style.display = "none";
			
			// 캐러셀 위치 초기화
			const carouselContainer = document.querySelector(".visual_img");
			carouselContainer.classList.remove("detail_swipe")
		} else {
			// 캐러셀 활성화
			carousel.initProductCarousel(productImageLength);
		}
	},
	
	// 상품 내용 배치
	arrangeProductContent: (content) => {
		const productContentContainer = document.querySelector(".dsc");
		productContentContainer.innerText = content;
	},
	
	// 펼쳐보기 버튼 이벤트 주입
	addEventMoreContentButton: () => {
		// 펼쳐보기, 접기 버튼 토글
		const moreContentButtons = document.getElementsByClassName("bk_more");
		const productContent = document.querySelector(".store_details");
		
		// moreContentButtons[0]: 펼쳐보기, moreContentButtons[1]: 접기
		moreContentButtons[0].addEventListener("click", () => {
			moreContentButtons[0].style = "display:none";
			moreContentButtons[1].style = "";
			productContent.classList.remove("close3");
		})
		moreContentButtons[1].addEventListener("click", () => {
			moreContentButtons[1].style = "display:none";
			moreContentButtons[0].style = "";
			productContent.classList.add("close3");
		})
	},
	
	// 하단 상품 내용 배치
	arrangeBottomProductContent: (content) => {
		const productBottomContainer = document.querySelector(".detail_info");
		const productContentContainer = productBottomContainer.querySelector(".in_dsc");
		productContentContainer.innerText = content;
	},
	
	// 전시, 공연 장소 내용 배치
	arrangeLocationContent: (mapImageUrl, placeStreet, placeLot, placeName, tel) => {
		const locationContainer = document.querySelector(".box_store_info");
		const mapContainer = locationContainer.querySelector(".store_map");
		mapContainer.src = mapImageUrl;
		
		const placeStreetContainer = locationContainer.querySelector(".store_addr_bold");
		placeStreetContainer.innerText = placeStreet;
		
		const placeLotContainer = locationContainer.querySelector(".addr_old_detail");
		placeLotContainer.innerText = placeLot;
		
		const placeNameContainer = locationContainer.querySelector(".addr_detail");
		placeNameContainer.innerText = placeName;
		
		const telContainer = locationContainer.querySelector(".store_tel");
		telContainer.innerText = tel;
	},
	
	// 하단 탭 이벤트 추가
	addEventBottomTapButton: () => {
		const bottomTapButtons = document.getElementsByClassName("anchor");
		
		const specificContentContainer = document.querySelector(".detail_area_wrap");
		const locationContainer = document.querySelector(".detail_location");
		
		// bottomTapButtons[0]: 상세정보, bottomTapButtons[1]: 오시는길
		bottomTapButtons[0].addEventListener("click", () => {
			bottomTapButtons[0].classList.add("active");
			bottomTapButtons[1].classList.remove("active");
			specificContentContainer.classList.remove("hide");
			locationContainer.classList.add("hide");
		})
		bottomTapButtons[1].addEventListener("click", () => {
			bottomTapButtons[1].classList.add("active");
			bottomTapButtons[0].classList.remove("active");
			locationContainer.classList.remove("hide");
			specificContentContainer.classList.add("hide");
		})
	},
	
	initProductDescription: function() {
		const displayInfoId = this.getDisplayInfoId();
		const path = `/${displayInfoId}`
		
		// 상품 상세정보 가져오기
		fetch(URL.products + path)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				// 상단
				this.arrangeProductImagePannel(data.productImageUrls, data.productDescription.title);
				this.initProductCarousel(data.productImageUrls.length);
				this.arrangeProductContent(data.productDescription.content);
				this.addEventMoreContentButton();
				
				// 하단
				this.arrangeBottomProductContent(data.productDescription.content);
				this.arrangeLocationContent(
					data.productDescription.mapImageUrl,
					data.productDescription.placeStreet,
					data.productDescription.placeLot,
					data.productDescription.placeName,
					data.productDescription.tel
				);
				this.addEventBottomTapButton();
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
}