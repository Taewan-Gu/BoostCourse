import { URL } from "../common/urlMapper.js";

export default class Category {
	constructor() {
		this.init();
	}
	
	init() {
		// 카테고리 가져오기
		fetch(URL.categories)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				this.arrangeCategories(data.categories);
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
	
	// 카테고리 배치
	arrangeCategories(categories) {
		const categoryTemplate = document.querySelector("#categoryItem").innerText;
		const bindCategoryTemplate = Handlebars.compile(categoryTemplate);
		
		let categoryHtml = categories.reduce(function(prev, next) {
		  	return prev + bindCategoryTemplate(next);
		}, "");
		
		const categoriesContainer = document.querySelector(".event_tab_lst");
		categoriesContainer.innerHTML += categoryHtml;
	}
}