import MoveTop from "./common/moveTop.js";
import LoginCheck from "./common/loginCheck.js";

import Comment from "./review/comment.js";
import ProductDescription from "./detail/productDescription.js";
import AnchorButtons from "./detail/anchorButtons.js";


document.addEventListener("DOMContentLoaded", () => {
	new LoginCheck();
	
	new ProductDescription();
	
	const comment = new Comment();
	comment.initComments();
	
	new AnchorButtons;
	new MoveTop();
});