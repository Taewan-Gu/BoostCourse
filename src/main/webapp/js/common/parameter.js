export const parameter = {
	// 현재 detail 페이지의 displayInfoId 반환
    getDisplayInfoId() {
        const parameters = new URLSearchParams(location.search);

        return parameters.get("displayInfoId");
    }
}