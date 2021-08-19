export default class AnchorButtons {
    constructor(displayInfoId) {
		this.initAnchorButtons(displayInfoId);
	}

    // 뒤로 가기 시, displayInfoId review.html로 넘겨주기.
    setDisplayInfoIdForBack(displayInfoId) {
        const backButton = document.querySelector('.btn_back');
        backButton.href = `/detail.html?displayInfoId=${displayInfoId}`;
    }

    initAnchorButtons(displayInfoId) {
        this.setDisplayInfoIdForBack(displayInfoId);
    }
}