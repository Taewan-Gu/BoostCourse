export const parser = {
	urlParameterParsing: () => {
		const parameterMap = {};
		// '?' 스트링 제거
		const rawParameters = location.search.substr(1);
		
		// url 파라미터 '&' 별로 분리 및 '='으로 key, value 구분
		rawParameters.split('&').forEach(rawParameter => {
			const parameterKeyAndValue = rawParameter.split('=');
			parameterMap[parameterKeyAndValue[0]] = parameterKeyAndValue[1];
		})
		
		return parameterMap;
	},
	dateParsing: (timeStamp) => {
		const rawDateTime = new Date(timeStamp);
		const rawDate = rawDateTime.toISOString().split("T")[0];
		const splitedRawDate = rawDate.split("-");
		
		let date = ""
		splitedRawDate.forEach(dateElement => {
			date += `${parseInt(dateElement)}.`;
		})
		
		return date;
	}
}