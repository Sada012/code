書籍管理システムWEB版ver3.0を実行する前に、、以下の準備をお願いいたします。

１）mysqlを使用するのでご利用の準備をお願いいたします。

２）コマンドプロンプト等にてinsert_mybookdb.txtのコードをコピーペーストし、データベースの登録をお願いいたします。




データベース登録確認手順


１）データベースが作成できているか確認

コピペ用コード)
SHOW DATABASES LIKE 'mybookdb';

２）テーブルが登録できているか確認

コピペ用コード)
show tables;

３）データ登録確認


コピペ用コード)
select * from bookinfo;
select * from userinfo;
select * from orderinfo;
(orderinfoはEmpty setで可）