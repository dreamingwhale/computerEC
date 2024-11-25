document.getElementById('InsertShohin').addEventListener('submit', function(event) {
	event.preventDefault(); // 기본 동작을 막음
	const shohinId = document.getElementById("shohin_id").value;
		// 상품 ID 중복 검사
        for (let i = 0; i < shohins.length; i++) {
            if (shohins[i].shohin_id === shohinId) {
                alert("商品IDが存在します。");
                document.getElementById('shohin_id').value = '';
                return false;
            }
        }
	    if (!shohinId) {
	        alert("商品IDを入力してください。");
	        return false;
	    }
	    if (isNaN(shohinId)) {
	        alert("商品IDは数字のみ入力してください。");
	        return false;
	    }
	    if (shohinId.length > 4) {
	        alert("商品IDは最大4桁まで入力してください。");
	        return false;
	    }

	    // 商品名チェック
	    const shohinName = document.getElementById("shohin_name").value;
	    if (!shohinName) {
	        alert("商品名を入力してください。");
	        return false;
	    }
	    if (shohinName.length > 100) {
	        alert("商品名は最大100文字まで入力してください。");
	        return false;
	    }

	    // 単価チェック
	    const tanka = document.getElementById("tanka").value;
	    if (!tanka) {
	        alert("単価を入力してください。");
	        return false;
	    }
	    if (isNaN(tanka)) {
	        alert("単価は数字のみ入力してください。");
	        return false;
	    }
	    if (tanka.split(".")[0].length > 8) {
	        alert("単価は整数部分が最大8桁までです。");
	        return false;
	    }
	    if (tanka.includes(".") && tanka.split(".")[1].length > 2) {
	        alert("単価は小数点以下2桁までです。");
	        return false;
	    }

	    // パーツチェック
	    const part = document.getElementById("part").value;
	    const validParts = ["1", "2", "3", "4", "5"]; // 有効なパーツの値
	    if (!part) {
	        alert("パーツを選択してください。");
	        return false;
	    }
	    if (!validParts.includes(part)) {
	        alert("無効なパーツの値です。");
	        return false;
	    }

	    // イメージチェック
	    const img = document.getElementById("img").value;
	    const urlPattern = /^(https?:\/\/)?([\w\-])+\.{1}([a-zA-Z]{2,63})([\/\w\-.]*)*\/?$/;
	    if (!img) {
	        alert("イメージURLを入力してください。");
	        return false;
	    }
	    if (!urlPattern.test(img)) {
	        alert("正しいURL形式でイメージを入力してください。");
	        return false;
	    }

	    // すべてのチェックを通過した場合
	    return true;
});
