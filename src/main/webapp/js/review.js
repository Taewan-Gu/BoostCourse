import { comment } from "./review/comment.js";
import { moveTop } from "./common/moveTop.js";

document.addEventListener("DOMContentLoaded", () => {
	comment.initAllComments();					// 리뷰
	
	moveTop.initMoveTopButton();				// top 버튼
});