import { comment } from "./review/comment.js";
import { initMoveTopButton } from "./common/moveTop.js";

document.addEventListener("DOMContentLoaded", () => {
	comment.initAllComments();
	
	initMoveTopButton();							// top 버튼
});