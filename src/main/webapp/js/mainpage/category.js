import { URL } from "../common/urlMapper.js";

export const category = {
	// 카테고리 컨테이너 선택
	categoriesContainer: document.querySelector(".event_tab_lst"),
	
	// 카테고리 배치
	arrangeCategories: function(categories) {
		const categoryTemplate = document.querySelector("#categoryItem").innerText;
		const bindCategoryTemplate = Handlebars.compile(categoryTemplate);
		
		let categoryHtml = categories.reduce(function(prev, next) {
		  	return prev + bindCategoryTemplate(next);
		}, "");
		
		this.categoriesContainer.innerHTML += categoryHtml;
	},
	
	// 초기 실행
	initCategories: function() {
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
}