export const cookie = {
	setCookie: function(key, value, expiredHours = 0.5) {
		const date = new Date();
		// 60: 분, 60: 초, 1000: 나노초, 세션과 시간 맞게 기본 30분 설정.
		date.setTime(date.getTime() + expiredHours*60*60*1000);
		document.cookie = key + "=" + value + "; expires=" + date.toUTCString();
	},
	
	deleteCookie: function(key) {
		document.cookie = key + "=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
	},
	
	getCookie: function(key) {
		const cookies = document.cookie.split("; ");
		let cookieValue;
		cookies.forEach(cookie => {
			const keysAndValues = cookie.split("=");
			if (keysAndValues[0] === key) {
				cookieValue = keysAndValues[1];
			}
		})
		return cookieValue;
	},
}