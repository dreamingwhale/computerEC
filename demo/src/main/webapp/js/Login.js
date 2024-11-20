document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 동작을 막음

    const userID = document.getElementById('userID').value;
    const userPW = document.getElementById('userPW').value;

    if (userID && userPW) {
        // 로그인 로직을 여기에 추가
        console.log('ユーザー名:', userID, 'パスワード:', userPW);
        alert('ログイン成功！');
    } else {
        alert('すべてのフィールドを入力してください。');
    }
});
