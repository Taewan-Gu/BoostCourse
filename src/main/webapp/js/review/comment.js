import { URL } from "../common/urlMapper.js";
import { parser } from "../common/parser.js"

export const comment = {
	// 현재 detail 페이지의 displayInfoId 반환
	getDisplayInfoId: () => {
		const urlParameters = parser.urlParameterParsing();
		return urlParameters.displayInfoId;
	},
	
	// Date 파싱
	replaceDateFormat: (comments) => {
		comments.forEach(comment => {
			const formattedReservationDate = parser.dateParsing(comment.reservationDate);
			comment.reservationDate = formattedReservationDate;
		})
		return comments;
	},
	
	// 집계함수 배치 (totalCount, averageScore)
	arrangeCommentsCountAndAverageScore(commentsCountAndAverageScore) {
		const gradeContainer = document.getElementsByClassName('grade_area')[0];
		
		const graphAverageScoreContainer = gradeContainer.getElementsByClassName('graph_value')[0];
		const averageScoreContainer = gradeContainer.getElementsByClassName('text_value')[0];
		const averageScoreSpan = averageScoreContainer.getElementsByTagName('span')[0];
		
		// 소수점 한자리까지만 출력
		const averageScore = commentsCountAndAverageScore.averageScore.toString().substr(0, 3);
		averageScoreSpan.innerText = averageScore;
		
		// 100분위를 표현하기 위한 *20(최대가 5이므로)
		graphAverageScoreContainer.style.width = `${averageScore * 20}%`;
		
		const countContainer = gradeContainer.getElementsByClassName('join_count')[0];
		const countEm = countContainer.getElementsByTagName('em')[0];

		countEm.innerText = `${commentsCountAndAverageScore.totalCount}건`;
	},
	
	// 리뷰 배치
	arrangeComments(comments) {
		const commentsContainer = document.getElementsByClassName('list_short_review')[0];
	
		const commentsTemplate = document.querySelector("#comment").innerText;
		const bindCommentsTemplate = Handlebars.compile(commentsTemplate);
		
		let commentsHtml = comments.reduce(function(prevCommentsHtml, nextComment) {
			return prevCommentsHtml + bindCommentsTemplate(nextComment);
		}, "");
		
		commentsContainer.innerHTML += commentsHtml;
	},
	
	// 모든 리뷰 보기 시, displayInfoId Review.html로 넘겨주기.
	setDisplayInfoIdForReview: (displayInfoId) => {
		const allCommentsButton = document.getElementsByClassName('btn_review_more')[0];
		allCommentsButton.href = `./review.html?displayInfoId=${displayInfoId}`;
	},
	
	// 뒤로 가기 시, displayInfoId detail.html로 넘겨주기.
	setDisplayInfoIdForDetail: (displayInfoId) => {
		const allCommentsButton = document.getElementsByClassName('btn_back')[0];
		allCommentsButton.href = `./detail.html?displayInfoId=${displayInfoId}`;
	},
	
	initAllComments: function() {
		const displayInfoId = this.getDisplayInfoId();
		const query = `?displayInfoId=${displayInfoId}`
		
		this.setDisplayInfoIdForDetail(displayInfoId);
		
		// 리뷰 정보 가져오기
		fetch(URL.allComments + query)
		    .then(response => {
		      	return response.json();
		    })
			.then(data => {
				const comments = this.replaceDateFormat(data.comments);
				this.arrangeComments(comments);
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
				const comments = this.replaceDateFormat(data.comments);
				this.arrangeComments(comments);
				this.arrangeCommentsCountAndAverageScore(data.commentsCountAndAverageScore);
			})
		    .catch(error => {
		    	console.error(error);
		    })
	}
}