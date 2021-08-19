import { productDescription } from "./detail/productDescription.js";
import AnchorButtons from "./detail/anchorButtons.js";
import { comment } from "./review/comment.js";
import { moveTop } from "./common/moveTop.js";

document.addEventListener("DOMContentLoaded", () => {
	productDescription.initProductDescription();		// 상품 상세내용
	comment.initComments();								// 리뷰 미리보기
	
	new AnchorButtons;									// 페이지 이동 버튼
	
	moveTop.initMoveTopButton();						// top 버튼
});