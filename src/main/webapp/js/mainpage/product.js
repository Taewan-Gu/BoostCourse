import { URL } from "../common/urlMapper.js";

export default class Product {
	categoriesContainer = document.getElementsByClassName("event_tab_lst")[0];
	productsRequest = false;
	productsCount = 0;
	
	constructor() {
		this.init();
	}
	
	init() {
		// onclick 안에서 this를 사용하기 위한 구문
		const self = this;
		
		// event delegation: 클릭한 카테고리를 찾아서 상품 변경 함수 실행
		this.categoriesContainer.onclick = function(event) {
			const target = event.target;
		
			if (target.tagName === 'A') {
				self.changeCategory(target);
			}
		}
		
		// 전체리스트 카테고리 상품 가져오기.
		const categoryButton = this.categoriesContainer.querySelector("a");
		
		this.changeCategory(categoryButton);
		
		// 더 보기 버튼 누를 시, 상품리스트 추가
		const moreButton = document.querySelector(".more");
		
		moreButton.addEventListener("click", () => {
			// 이미 요청 보낸 상태
			if (this.productsRequest) {
				return;
			}
			
			const selectedCategory = this.categoriesContainer.querySelector(".active");
			const categoryId = selectedCategory.getAttribute("categoryId");
			
			let query = `?start=${this.productsCount}`;
			
			if (categoryId != 0) {
				query = `${query}&categoryId=${categoryId}`;
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
					this.arrangeProducts(data.products);
				})
			    .catch(error => {
			    	console.error(error);
			    })

			this.productsRequest = true;
		})
	}
	
	// 상품 추가하기.
	arrangeProducts(products) {
		const productsContainers = document.getElementsByClassName('lst_event_box');
		
		const productTemplate = document.querySelector("#itemList").innerText;
		const bindProductTemplate = Handlebars.compile(productTemplate);
		
		let leftProductHtml = "";
		let rightProductHtml = "";
		
		// forEach문 안에서 this를 사용하기 위한 구문
		const self = this;
		
		products.forEach(function (item, index) {
			// index가 짝수일 경우, 왼쪽 배치. 홀수일 경우, 오른쪽 배치
			if (index % 2 == 0) {
				leftProductHtml += bindProductTemplate(item);
			} else {
				rightProductHtml += bindProductTemplate(item);
			}
			self.productsCount++;
		});
		
		productsContainers[0].innerHTML += leftProductHtml;
		productsContainers[1].innerHTML += rightProductHtml;
		
		this.productsRequest = false;
	}
	
	// 상품 전시판 초기화.
	initProductDisplay() {
		const productsContainers = document.getElementsByClassName('lst_event_box');
		
		for (let productsContainer of productsContainers) {
			productsContainer.innerHTML = "";
		}
		
		this.productsCount = 0;
	}
	
	// 전시 갯수 현황판 변경
	changeTotalCount(totalCount) {
		const totalCountBox = document.querySelector('.totalCount');
		totalCountBox.innerText = totalCount;
	}
	
	// 카테고리 변경 후, 상품 배치 함수, 전시 갯수 변경 함수 호출.
	changeCategory(target) {
		const categoryButtons = this.categoriesContainer.getElementsByTagName("a");
		
		for (let categoryButton of categoryButtons) {
			categoryButton.className = "anchor";
		}
		target.className = "anchor active";
		
		let query = "";
		const categoryId = target.getAttribute("categoryId");
		
		if (categoryId != 0) {
			query = `?categoryId=${categoryId}`;
		}
		
		// 상품 가져오기
		fetch(URL.products + query)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				const moreButton = document.querySelector(".more");
				if (data.products.length < 4) {
					moreButton.style.display = "none";
				} else {
					moreButton.style.display = "block"
				}
				
				this.changeTotalCount(data.totalCount);
				this.initProductDisplay();
				this.arrangeProducts(data.products);
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
}