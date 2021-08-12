import { loginCheck } from "./mainpage/loginCheck.js";
import { promotion } from "./mainpage/promotion.js";
import { category } from "./mainpage/category.js";
import { product } from "./mainpage/product.js";
import { moveTop } from "./common/moveTop.js";

document.addEventListener("DOMContentLoaded", () => {
	loginCheck.initLoginCheck();					// 비회원 로그인 체크

	promotion.initPromotionCarousel(); 			// 프로모션
	category.initCategories();					// 카테고리
	product.initProducts();						// 프로덕트
	
	moveTop.initMoveTopButton();				// top 버튼
});