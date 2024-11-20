document.getElementById('loginForm').addEventListener('submit', function(event) {

    let userID = document.getElementById('userID').value;
    let userPW = document.getElementById('userPW').value;

    if (userID && userPW) {
        // 로그인 로직을 여기에 추가
        alert('ログイン成功！');
		window.location.href = '/Main';
    } else {
        alert('すべてのフィールドを入力してください。');
    }
});
