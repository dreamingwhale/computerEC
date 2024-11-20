document.getElementById('KaiinToroku').addEventListener('submit', function(event) {
    
    const userID = document.getElementById('userID').value;
    const userPW = document.getElementById('userPW').value;
	const userPWConfirm = document.getElementByID('userPWConfirm').value;
	
    if (userID && userPW) {
        // 로그인 로직을 여기에 추가
		if(userPW==userPWConfirm){
        	console.log('ユーザー名:', userID, 'パスワード:', userPW);
        	alert('登録成功！');
		}else{
			alert('パスワードとパスワード確認が違います。');
			event.preventDefault(); // 기본 동작을 막음
			return;
		}
    } else {
        alert('すべてのフィールドを入力してください。');
    }
});
