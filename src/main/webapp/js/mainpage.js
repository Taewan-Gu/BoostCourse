import LoginCheck from "./common/loginCheck.js";
import MoveTop from "./common/moveTop.js";
import Logout from "./common/logout.js";

import Promotion from "./mainpage/promotion.js";
import Category from "./mainpage/category.js";
import Product from "./mainpage/product.js";


document.addEventListener("DOMContentLoaded", () => {
	new LoginCheck();

	new Promotion();
	new Category();
	new Product();
	
	new MoveTop();
	
	// 추가 사항
	new Logout();
});