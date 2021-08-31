import { URL } from "../common/urlMapper.js";

export default class Logout {
	
	constructor() {
		this.init();
	}
	
	init() {
		this.setLogoutButton();
	}
	
	setLogoutButton() {
		const logoutButton = document.querySelector(".logout_btn");
		logoutButton.addEventListener("click", () => {
			fetch(URL.logout, {"method": "post"})
				.then(() => {
					localStorage.removeItem("email");
					location.reload();
				})
				.catch(error => {
					console.error(error);
				})
		})
		
	}
}