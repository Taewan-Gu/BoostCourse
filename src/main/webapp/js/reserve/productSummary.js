export default class ProductSummary {
	
    constructor(productSummary) {
		this.render(productSummary);
	}
	
    render(productSummary) {
		// 타이틀
		const navTitle = document.querySelector(".title");
		const previewTitle = document.querySelector(".preview_txt_tit");
		const inTitle = document.querySelector(".in_tit");
		navTitle.innerText = productSummary.title;
		previewTitle.innerText = productSummary.title;
		inTitle.innerText = productSummary.title;
		
		// 이미지
		const thumbImage = document.querySelector(".img_thumb");
		thumbImage.src = productSummary.productImageUrl;
		
		// 장소
		const place = document.querySelector(".place_dsc");
		place.innerText = productSummary.place;
		
		// 관람시간 및 기간
		const openingHours = document.querySelector(".opening_hours_dsc");
		openingHours.innerText = productSummary.openingHours
		
		// productId 배치
		const productIdInput = document.querySelector("#productId");
		productIdInput.value = productSummary.id;
		
		// displayInfoId 배치
		const displayInfoIdInput = document.querySelector("#displayInfoId");
		displayInfoIdInput.value = productSummary.displayInfoId;
	}
}