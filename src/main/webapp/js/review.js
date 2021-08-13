import { comment } from "./review/comment.js";
import { moveTop } from "./common/moveTop.js";
import { URL } from "./common/urlMapper.js";

document.addEventListener("DOMContentLoaded", () => {
	comment.initComments(URL.allComments);
	
	moveTop.initMoveTopButton();						// top 버튼
});