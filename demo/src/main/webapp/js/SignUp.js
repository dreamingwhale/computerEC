document.addEventListener("DOMContentLoaded", function () {
	document.getElementById('KaiinToroku').addEventListener('submit', function(event) {
		event.preventDefault();
		const userID = document.getElementById('userID').value.trim();
	    const userPW = document.getElementById('userPW').value.trim();
	    const userPWConfirm = document.getElementById('userPWConfirm').value.trim();
	    const userAddress = document.getElementById('userAddress').value.trim();
		
	    // ID 유효성 검사
	    if (!userID) {
	        alert('ユーザーIDを入力してください。');
	        return false;
	    }
	
	    // 비밀번호 유효성 검사
	    if (userPW.length < 8) {
	        alert('パスワードは8文字以上にしてください。');
	        return false;
	    }
	
	    // 비밀번호 확인 유효성 검사
	    if (userPW !== userPWConfirm) {
	        alert('パスワードとパスワード確認が一致しません。');
	        return false;
	    }
	
	    // 주소 유효성 검사
	    if (!userAddress) {
	        alert('住所を入力してください。');
	        return false;
	    }
		
		if(userAddress.length>100){
			alert('住所が100文字を超えています。');
			return false;
		}
		
	    // 추가적으로 ID와 비밀번호 형식 검사 가능
	    const idRegex = /^[a-zA-Z0-9_]+$/; // 영문, 숫자, 밑줄(_)만 허용
	    if (!idRegex.test(userID)) {
	        alert('ユーザーIDには英数字またはアンダースコアのみ使用できます。');
	        return false;
	    }
	
	    const pwRegex = /^(?=.*[a-zA-Z])(?=.*\d).{8,20}$/; // 영문 + 숫자 포함, 최소 8자
	    if (!pwRegex.test(userPW)) {
	        alert('パスワードは英文字と数字を含む8文字以上にしてください。');
	        return false;
	    }
	
		event.target.submit();
	    alert('登録成功！');
	});
});