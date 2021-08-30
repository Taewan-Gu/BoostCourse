import MoveTop from "./common/moveTop.js";
import StarScore from "./reviewWrite/starScore.js";
import ReviewInput from "./reviewWrite/reviewInput.js";
import FileInput from "./reviewWrite/fileInput.js";
import SubmitButton from "./reviewWrite/submitButton.js";


document.addEventListener("DOMContentLoaded", () => {
	new StarScore();
	new ReviewInput();
	new FileInput();
	new SubmitButton();
	
	new MoveTop();
});