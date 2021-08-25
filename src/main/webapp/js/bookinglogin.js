import MoveTop from "./common/moveTop.js";

import Validation from "./util/validation.js";
import Login from "./bookinglogin/login.js";


document.addEventListener("DOMContentLoaded", () => {
	const validation = new Validation();
	validation.initBookinglogin();
	
	new Login(validation);
	
	new MoveTop();
});