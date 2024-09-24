<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<script>
// スクロールトップボタンの表示制御
window.addEventListener('scroll', () => {
    if (window.scrollY > 200) {
        scrollTopBtn.style.display = 'block';
    } else {
        scrollTopBtn.style.display = 'none';
    }
});

// トップへ戻るボタンの動作
scrollTopBtn.addEventListener('click', () => {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    });
});
</script>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>フッター</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <footer>
        <div class="footer-container">
            <div class="footer-links">
                <a href="about.jsp">アプリについて</a>
                <a href="contact.jsp">お問い合わせ</a>
                <a href="privacy.jsp">プライバシーポリシー</a>
                <a href="terms.jsp">利用規約</a>
            </div>
            <p>&copy; 2024 フリマアプリ</p>
        </div>
    </footer>
</body>
</html>
