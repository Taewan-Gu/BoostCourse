import MoveTop from "./common/moveTop.js";
import LoginCheck from "./common/loginCheck.js";

import Comment from "./review/comment.js";
import ProductDescription from "./detail/productDescription.js";
import AnchorButtons from "./detail/anchorButtons.js";


document.addEventListener("DOMContentLoaded", () => {
	new LoginCheck();						// 비회원 로그인 확인
	
	new ProductDescription();				// 상품 상세내용
	
	const comment = new Comment();
	comment.initComments();					// 리뷰 미리보기
	
	new AnchorButtons;						// 리뷰 페이지 이동 버튼
	new MoveTop();							// top 버튼
});