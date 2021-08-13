import { URL } from "../common/urlMapper.js";

export const comment = {
	// 현재 detail 페이지의 displayInfoId 반환
	getDisplayInfoId: () => {
		const parameters = new URLSearchParams(location.search);
		
		return parameters.get("displayInfoId");
	},
	
	// 집계함수 배치 (totalCount, averageScore)
	arrangeCommentsCountAndAverageScore(commentsCountAndAverageScore) {
		// id 박아버리기.
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
	},
	
	// 리뷰 배치
	arrangeComments(comments) {
		const commentsContainer = document.querySelector('.list_short_review');
	
		const commentsTemplate = document.querySelector("#comment").innerText;
		const bindCommentsTemplate = Handlebars.compile(commentsTemplate);
		
		let commentsHtml = comments.reduce(function(prevCommentsHtml, nextComment) {
			return prevCommentsHtml + bindCommentsTemplate(nextComment);
		}, "");
		
		commentsContainer.innerHTML += commentsHtml;
	},
	
	// 모든 리뷰 보기 시, displayInfoId Review.html로 넘겨주기.
	setDisplayInfoIdForReview: (displayInfoId) => {
		const allCommentsButton = document.querySelector('.btn_review_more');
		allCommentsButton.href = `./review.html?displayInfoId=${displayInfoId}`;
	},
	
	// 뒤로 가기 시, displayInfoId detail.html로 넘겨주기.
	setDisplayInfoIdForDetail: (displayInfoId) => {
		const allCommentsButton = document.querySelector('.btn_back');
		allCommentsButton.href = `./detail.html?displayInfoId=${displayInfoId}`;
	},
	
	initAllComments: function() {
		const displayInfoId = this.getDisplayInfoId();
		const query = `?displayInfoId=${displayInfoId}`;
		
		// 뒤로 가기 시, displayInfoId detail.html로 넘겨주기.
		this.setDisplayInfoIdForDetail(displayInfoId);	
		
		// 리뷰 정보 가져오기
		fetch(URL.allComments + query)
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
	},
	
	initComments: function() {
		const displayInfoId = this.getDisplayInfoId();
		const query = `?displayInfoId=${displayInfoId}`;
		
		this.setDisplayInfoIdForReview(displayInfoId);	
		
		// 리뷰 정보 가져오기
		fetch(URL.comments + query)
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
}