import MoveTop from "./common/moveTop.js";

import Comment from "./review/comment.js";


document.addEventListener("DOMContentLoaded", () => {
	// 리뷰
	const comment = new Comment();
	comment.initAllComments();				
	
	// top 버튼
	new MoveTop();
});