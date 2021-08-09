import { URL } from "../common/urlMapper.js";

// 카테고리 컨테이너 선택
const categoriesContainer = document.getElementsByClassName("event_tab_lst")[0];

// 카테고리 배치
function arrangeCategories (categories) {
	const categoryTemplate = document.querySelector("#categoryItem").innerText;
	const bindCategoryTemplate = Handlebars.compile(categoryTemplate);
	
	let categoryHtml = categories.reduce(function(prev, next) {
	  	return prev + bindCategoryTemplate(next);
	}, "");
	
	categoriesContainer.innerHTML += categoryHtml;
}

// 초기 실행
function initCategories() {
	// 카테고리 가져오기
	fetch(URL.categories)
	    .then(response => {
	      	return response.json();
	    })
		.then(data => {
			arrangeCategories(data.categories);
		})
	    .catch(error => {
	    	console.error(error);
	    })
}

export { initCategories };