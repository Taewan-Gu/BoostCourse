// TOP 버튼 누를 시, 화면 상단으로 이동
function initMoveTopButton() {
	const topButton = document.getElementsByClassName("lnk_top")[0];
	topButton.addEventListener("click", () => {
	    window.scrollTo(0,0);
	})
}

export { initMoveTopButton };