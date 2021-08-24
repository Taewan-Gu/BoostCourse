import LoginCheck from "./common/loginCheck.js";
import MoveTop from "./common/moveTop.js";

import Promotion from "./mainpage/promotion.js";
import Category from "./mainpage/category.js";
import Product from "./mainpage/product.js";


document.addEventListener("DOMContentLoaded", () => {
	new LoginCheck();					// 비회원 로그인 확인

	new Promotion();					// 프로모션
	new Category();						// 카테고리
	new Product();						// 프로덕트
	
	new MoveTop();						// top 버튼
});