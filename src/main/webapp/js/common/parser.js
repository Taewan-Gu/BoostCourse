export const parser = {
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