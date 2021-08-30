export default class FileInput {
	constructor() {
		this.init();
	}
	
	init() {
		this.setFileInputThumbnail();
		this.setDeleteThumbnail();
	}
	
	setFileInputThumbnail() {
		const reviewImageContainer = document.querySelector("#reviewImageFileOpenInput");
        reviewImageContainer.addEventListener("change", event => {
            const reviewImage = event.target.files[0];
            if(this.isNotValidatedImageType(reviewImage)) {
				reviewImageContainer.value = "";
                alert("이미지 확장자는 jpg, png만 가능합니다.");
                return;
            }
			const thumbnailContainer = document.querySelector(".lst_thumb .item");
			thumbnailContainer.style.display = "inline-block";

            const thumbnailImage = document.querySelector(".item_thumb");
            thumbnailImage.src = window.URL.createObjectURL(reviewImage);
        })
	}
	
	isNotValidatedImageType(image) {
		if (["image/png", "image/jpg" ].includes(image.type)) {
			return false;
		}
		return true;
	}
	
	setDeleteThumbnail() {
		const deleteButton = document.querySelector(".anchor");
        deleteButton.addEventListener("click", () => {
			const reviewImageContainer = document.querySelector("#reviewImageFileOpenInput");
			reviewImageContainer.value = "";
			
			const thumbnailContainer = document.querySelector(".lst_thumb .item");
			thumbnailContainer.style.display = "none";
        })
	}
}