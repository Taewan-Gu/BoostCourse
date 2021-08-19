import { moveTop } from "./common/moveTop.js";
import { parameter } from "./common/parameter.js";

import ProductReservation from "./reserve/productReservation.js";
import AnchorButtons from "./reserve/anchorButtons.js";
import Validation from "./reserve/validation.js";
import Agreement from "./reserve/agreement.js";
import Confirm from "./reserve/confirm.js";

document.addEventListener("DOMContentLoaded", () => {
	const displayInfoId = parameter.getDisplayInfoId();	// DisplayInfoId 얻기
	
	new ProductReservation(displayInfoId);				// 상품예약 설명 & 가격
	new AnchorButtons(displayInfoId);					// 뒤로가기 버튼
	new Validation();									// 예약 폼 밸리데이션
	new Agreement();									// 약관 펼쳐보기
	new Confirm();										// 예약
	
	moveTop.initMoveTopButton();						// top 버튼
});