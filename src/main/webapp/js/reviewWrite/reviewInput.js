const EMPTY_REVIEW_LENGTH = 0;
const MIN_REVIEW_LENGTH = 5;
const INVALID_COLOR = "#999";
const VALID_COLOR = "#222";

export default class ReviewInput {
	constructor() {
		this.init();
	}
	
	init() {
		this.setHideReviewManual();
		this.setShowReviewManual();
		this.setReviewLetterLength();
	}
	
	setHideReviewManual() {
		const reviewManual = document.querySelector(".review_write_info");
		const reviewTextArea = document.querySelector(".review_textarea");
		reviewManual.addEventListener("click", () => {
			reviewManual.style.display = "none";
			reviewTextArea.focus();
		})
	}
	
	setShowReviewManual() {
		const reviewManual = document.querySelector(".review_write_info");
		const reviewTextArea = document.querySelector(".review_textarea");
		document.addEventListener("click", event => {
			if (event.target.classList.contains("review_manual")) {
				return;
			}
			
			if (reviewTextArea.value.length == EMPTY_REVIEW_LENGTH) {
				reviewManual.style.display = "block";
			}
		})
	}
	
	setReviewLetterLength() {
		const reviewTextArea = document.querySelector(".review_textarea");
		const reviewLetterLengthContainer = document.querySelector(".guide_review");
		reviewTextArea.addEventListener("keyup", () => {
			const reviewLetterLength = reviewTextArea.value.length;
			const reviewLetterLengthBox = reviewLetterLengthContainer.querySelector("span");
			reviewLetterLengthBox.innerText = reviewLetterLength;
			
			if (reviewLetterLength >= MIN_REVIEW_LENGTH) {
				reviewLetterLengthContainer.style.color = VALID_COLOR;
				return;
			}
			
			reviewLetterLengthContainer.style.color = INVALID_COLOR;
		})
	}
}