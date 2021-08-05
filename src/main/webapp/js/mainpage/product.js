import { URL } from "../common/urlMapper.js";

const categoriesContainer = document.getElementsByClassName("event_tab_lst")[0];
let productsCount = 0;

// 상품 추가하기.
function arrangeProducts(products) {
	const productsContainers = document.getElementsByClassName('lst_event_box');
	
	const productTemplate = document.querySelector("#itemList").innerText;
	const bindProductTemplate = Handlebars.compile(productTemplate);
	
	let leftProductHtml = "";
	let rightProductHtml = "";

	products.forEach(function (item, index) {
		if (index % 2 == 0) {
			leftProductHtml += bindProductTemplate(item);
		} else {
			rightProductHtml += bindProductTemplate(item);
		}
		productsCount++;
	});
	
	productsContainers[0].innerHTML += leftProductHtml;
	productsContainers[1].innerHTML += rightProductHtml;
}

// 상품 전시판 초기화.
function initProductDisplay() {
	const productsContainers = document.getElementsByClassName('lst_event_box');
	
	for (let i = 0; i < productsContainers.length; i++) {
		productsContainers[i].innerHTML = "";
	}
	
	productsCount = 0;
}

// 전시 갯수 현황판 변경
function changeTotalCount(totalCount) {
	const totalCountBox = document.getElementsByClassName('totalCount')[0];
	totalCountBox.innerText = totalCount;
}

// 카테고리 변경 후, 상품 배치 함수, 전시 갯수 변경 함수 호출.
function changeCategory(target) {
	const categoryButtons = categoriesContainer.getElementsByTagName("a");
	
	for (let i = 0; i < categoryButtons.length; i++) {
		categoryButtons[i].className = "anchor";
	}
	target.className = "anchor active";
	
	let query = "";
	const categoryId = target.getAttribute("categoryId");
	
	if (categoryId != 0) {
		query = `/${categoryId}`;
	}
	
	// 상품 가져오기
	fetch(URL.products + query)
	    .then(response => {
	      	return response.json();
	    })
		.then(data => {
			const moreButton = document.getElementsByClassName("more")[0];
			if (data.products.length < 4) {
				moreButton.style.display = "none";
			} else {
				moreButton.style.display = "block"
			}
			
			changeTotalCount(data.totalCount);
			initProductDisplay();
			arrangeProducts(data.products);
		})
	    .catch(error => {
	    	console.error(error);
	    })
}

// 초기 실행 함수
function initProducts() {
	// event delegation: 클릭한 카테고리를 찾아서 상품 변경 함수 실행
	categoriesContainer.onclick = function(event) {
		let target = event.target;
	
		if (target.tagName === 'A') {
			changeCategory(target);
		}
	}
	
	// 전체리스트 카테고리 상품 가져오기.
	const categoryButton = categoriesContainer.getElementsByTagName("a")[0];
	
	changeCategory(categoryButton);
	
	// 더 보기 버튼 누를 시, 상품리스트 추가
	const moreButton = document.getElementsByClassName("more")[0];
	
	moreButton.addEventListener("click", () => {
		const selectedCategory = categoriesContainer.getElementsByClassName("active")[0];
		const categoryId = selectedCategory.getAttribute("categoryId");
		
		let query = `?start=${productsCount}`;
		
		if (categoryId != 0) {
			query = `/${categoryId}${query}`;
		}
		
		// 상품 가져오기
		fetch(URL.products + query)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				if (data.products.length < 4) {
					moreButton.style.display = "none";
				}
				arrangeProducts(data.products);
			})
		    .catch(error => {
		    	console.error(error);
		    })
	})
}

export { initProducts };