export default class StarScore {
	constructor() {
		this.init();
	}
	
	init() {
		const ratingContainer = document.querySelector(".rating");
		ratingContainer.addEventListener("click", event => {
			if (event.target.classList.contains("rating_rdo")) {
				this.setScore(event.target);
				this.setStars(event.target);	
			}
		})		
	}
	
	setScore(target) {
		const score = target.value;
		const scoreContainer = document.querySelector(".star_rank");
		scoreContainer.innerText = score;
		scoreContainer.classList.remove("gray_star");
	}
	
	setStars(target) {
		target.checked = true;
		
		let previousTarget = target.previousElementSibling;
		let nextTarget = target.nextElementSibling;
		
		while (previousTarget) {
			if (previousTarget.tagName == "SPAN") {
				previousTarget = previousTarget.previousElementSibling;
				continue;
			}
			
			previousTarget.checked = false;
			previousTarget.classList.add("checked");
			previousTarget = previousTarget.previousElementSibling;
		}
		
		while (nextTarget) {
			if (nextTarget.tagName == "SPAN") {
				nextTarget = nextTarget.nextElementSibling;
				continue;
			}
			
			nextTarget.checked = false;
			nextTarget.classList.remove("checked");
			nextTarget = nextTarget.nextElementSibling;
		}
	}
}