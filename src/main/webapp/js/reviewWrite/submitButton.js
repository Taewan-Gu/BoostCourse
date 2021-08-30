import { URL } from "../common/urlMapper.js";


const FIRST_FILE = 0;
const MIN_REVIEW_LENGTH = 5;

export default class SubmitButton {
	constructor() {
		this.init();
	}
	
	init() {
		const submitButton = document.querySelector(".bk_btn");
		submitButton.addEventListener("click", () => {
			const formData = new FormData()
			const scoreContainers = document.getElementsByClassName("rating_rdo");
			for (let scoreContainer of scoreContainers) {
				if (scoreContainer.checked) {
					formData.append("score", scoreContainer.value);
					break;
				}
			}
			
			if (formData.get("score") == null) {
				return alert("별점을 입력해주세요.");
			}
			
			const reviewTextContainer = document.querySelector(".review_textarea");
			if (reviewTextContainer.value.length < MIN_REVIEW_LENGTH) {
				return alert("최소 코멘트는 5글자 입니다.");
			}
			formData.append("comment", reviewTextContainer.value);
			
			
			const reviewImageContainer = document.querySelector("#reviewImageFileOpenInput");
			// 이미지는 있다면 서버로 보내고 없다면 보내지 않는다.
			if (reviewImageContainer.files[FIRST_FILE]) {
				formData.append("commentImage", reviewImageContainer.files[FIRST_FILE]);
			}
			
			const reservationInfoId = localStorage.getItem("reservationInfoId");
			const path = `/${reservationInfoId}/comments`
			fetch(URL.reservations + path, {
					method: "POST",
					body: formData,
				})
			    .then(response => {
					if (response.redirected) {
						alert("로그인이 필요합니다.");
						location.href = "/bookinglogin.html";
					}
					location.href = "/myreservation.html";
				})
			    .catch(error => {
			    	console.error(error);
			    })
		})		
	}
}