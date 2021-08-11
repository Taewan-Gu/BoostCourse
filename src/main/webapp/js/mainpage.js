import { initLoginCheck } from "./mainpage/loginCheck.js";
import { initPromotionCarousel } from "./mainpage/promotion.js";
import { initCategories } from "./mainpage/category.js";
import { initProducts } from "./mainpage/product.js";
import { initMoveTopButton } from "./common/moveTop.js";

document.addEventListener("DOMContentLoaded", () => {
	initLoginCheck();					// 비회원 로그인 체크

	initPromotionCarousel(); 			// 프로모션
	initCategories();					// 카테고리
	initProducts();						// 프로덕트
	
	initMoveTopButton();				// top 버튼
});