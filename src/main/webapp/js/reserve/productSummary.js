export default class ProductSummary {
	
    constructor(productSummary) {
		this.render(productSummary);
	}
	
    render(productSummary) {
		const navTitle = document.querySelector(".title");
		const previewTitle = document.querySelector(".preview_txt_tit");
		const inTitle = document.querySelector(".in_tit");
		navTitle.innerText = productSummary.title;
		previewTitle.innerText = productSummary.title;
		inTitle.innerText = productSummary.title;
		
		const thumbImage = document.querySelector(".img_thumb");
		thumbImage.src = productSummary.productImageUrl;
		
		const place = document.querySelector(".place_dsc");
		place.innerText = productSummary.place;
		
		const openingHours = document.querySelector(".opening_hours_dsc");
		openingHours.innerText = productSummary.openingHours
		
		const productIdInput = document.querySelector("#productId");
		productIdInput.value = productSummary.id;
		
		const displayInfoIdInput = document.querySelector("#displayInfoId");
		displayInfoIdInput.value = productSummary.displayInfoId;
	}
}