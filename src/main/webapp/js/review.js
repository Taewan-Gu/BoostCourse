import MoveTop from "./common/moveTop.js";

import Comment from "./review/comment.js";


document.addEventListener("DOMContentLoaded", () => {
	const comment = new Comment();
	comment.initAllComments();				
	
	new MoveTop();
});