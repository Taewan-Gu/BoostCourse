import { BASE_URL, URL } from "../common/urlMapper.js";

export default class Login {
    constructor(validation) {
		this.init(validation);
	}

    init(validation) {
		this.setLoginButton(validation);
    }

	setLoginButton(validation) {
		// 이미 로그인되어 있다면 패스
		fetch(URL.loginCheck, {
				method: "POST",
			})
		    .then(response => {
				if (response.status == 204) {
					return;
				}
				location.href = BASE_URL + "myreservation.html";
			})
		    .catch(error => {
		    	console.error(error);
		    })
		
		const loginButton = document.querySelector(".login_btn");
		loginButton.addEventListener("click", () => {
			this.loginEvent(validation)
		})
		
		const emailInput = document.getElementById('email');
		emailInput.addEventListener("keypress", event => {
			if (event.key == "Enter") {
				this.loginEvent(validation);
			}
		})
	}
	
	loginEvent(validation) {	
		if (validation.isNotValidatedEmail()) {
			return alert("이메일 형식을 맞춰주세요")
		}
		
		const loginEmailInput = document.querySelector(".login_input");
		const loginEmail = loginEmailInput.value;
		const loginForm = { "email": loginEmail }
		
		// 로그인
		fetch(URL.login, {
				method: "POST",
				body: JSON.stringify(loginForm),
				headers: {
		            'Content-Type': 'application/json',
		        },
			})
		    .then(() => {
				localStorage.setItem("email", loginEmail);
				location.href = BASE_URL + "myreservation.html"; 
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
}