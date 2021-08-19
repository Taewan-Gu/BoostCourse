export default class AnchorButtons {
    constructor() {
		this.initAnchorButtons();
	}

    // 현재 detail 페이지의 displayInfoId 반환
    getDisplayInfoId() {
        const parameters = new URLSearchParams(location.search);

        return parameters.get("displayInfoId");
    }

    // 모든 리뷰 보기 시, displayInfoId review.html로 넘겨주기.
    setDisplayInfoIdForReview(displayInfoId) {
        const allCommentsButton = document.querySelector('.btn_review_more');
        allCommentsButton.href = `/review.html?displayInfoId=${displayInfoId}`;
    }

    // 예매하기 시, displayInfoId reserve.html로 넘겨주기.
    setDisplayInfoIdForReserve(displayInfoId) {
        const reserveButton = document.querySelector('.section_btn');
        reserveButton.href = `/reserve.html?displayInfoId=${displayInfoId}`;
    }

    initAnchorButtons() {
        const displayInfoId = this.getDisplayInfoId();

        this.setDisplayInfoIdForReview(displayInfoId);
        this.setDisplayInfoIdForReserve(displayInfoId);
    }
}