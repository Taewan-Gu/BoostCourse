// TOP 버튼 누를 시, 화면 상단으로 이동
export const moveTop = {
	initMoveTopButton: function() {
		const topButton = document.querySelector(".lnk_top");
		topButton.addEventListener("click", () => {
		    window.scrollTo(0,0);
		})
	}	
}