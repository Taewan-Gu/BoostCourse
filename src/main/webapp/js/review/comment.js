import { URL } from "../common/urlMapper.js";

export default class Comment {
	constructor() {}
	
	// 현재 detail 페이지의 displayInfoId 반환
	getDisplayInfoId() {
		const parameters = new URLSearchParams(location.search);
		
		return parameters.get("displayInfoId");
	}
	
	// 집계함수 배치 (totalCount, averageScore)
	arrangeCommentsCountAndAverageScore(commentsCountAndAverageScore) {
		const gradeContainer = document.querySelector('.grade_area');
		
		const graphAverageScoreContainer = gradeContainer.querySelector('.graph_value');
		const averageScoreContainer = gradeContainer.querySelector('.text_value');
		const averageScoreSpan = averageScoreContainer.querySelector('span');
		
		// 소수점 한자리까지만 출력
		const averageScore = commentsCountAndAverageScore.averageScore.toString().substr(0, 3);
		averageScoreSpan.innerText = averageScore;
		
		// 100분위를 표현하기 위한 *20(최대가 5이므로)
		graphAverageScoreContainer.style.width = `${averageScore * 20}%`;
		
		const countContainer = gradeContainer.querySelector('.join_count');
		const countEm = countContainer.querySelector('em');

		countEm.innerText = `${commentsCountAndAverageScore.totalCount}건`;
	}
	
	// 리뷰 배치
	arrangeComments(comments) {
		const commentsContainer = document.querySelector('.list_short_review');
	
		const commentsTemplate = document.querySelector("#comment").innerText;
		const bindCommentsTemplate = Handlebars.compile(commentsTemplate);
		
		let commentsHtml = comments.reduce(function(prevCommentsHtml, nextComment) {
			return prevCommentsHtml + bindCommentsTemplate(nextComment);
		}, "");
		
		commentsContainer.innerHTML += commentsHtml;
	}
	
	// 뒤로 가기 시, displayInfoId detail.html로 넘겨주기.
	setDisplayInfoIdForDetail(displayInfoId) {
		const allCommentsButton = document.querySelector('.btn_back');
		allCommentsButton.href = `/detail.html?displayInfoId=${displayInfoId}`;
	}
	
	// 리뷰 정보 가져오기
	getComments(url) {
		fetch(url)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				this.arrangeComments(data.comments);
				this.arrangeCommentsCountAndAverageScore(data.commentsCountAndAverageScore);
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
	
	initAllComments() {
		const displayInfoId = this.getDisplayInfoId();
		const query = `?displayInfoId=${displayInfoId}`;
		
		// 뒤로 가기 시, displayInfoId detail.html로 넘겨주기.
		this.setDisplayInfoIdForDetail(displayInfoId);	
		
		// 리뷰 정보 가져오기
		this.getComments(URL.allComments + query)
	}
	
	initComments() {
		const displayInfoId = this.getDisplayInfoId();
		const query = `?displayInfoId=${displayInfoId}`;	
		
		// 리뷰 정보 가져오기
		this.getComments(URL.comments + query)
	}
}