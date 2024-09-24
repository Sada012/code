package util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bean.Merchandise;
import bean.User;

public class SendMail {


	public void post(User recipient, Merchandise merchandise ,int status) {
		try {
			Properties props = System.getProperties();

			// SMTPサーバのアドレスを指定（今回はxserverのSMTPサーバを利用）
			props.put("mail.smtp.host", "sv5215.xserver.jp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(
					props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							//メールサーバにログインするメールアドレスとパスワードを設定
							return new PasswordAuthentication("test.sender@kanda-it-school-system.com", "kandaSender202208");
						}
					}
					);

			MimeMessage mimeMessage = new MimeMessage(session);

			// 送信元メールアドレスと送信者名を指定
			mimeMessage.setFrom(new InternetAddress("test.sender@kanda-it-school-system.com", "神田IT School", "iso-2022-jp"));

			// 送信先メールアドレスを指定（ご自分のメールアドレスに変更）
			mimeMessage.setRecipients(Message.RecipientType.TO, "system.project.team39@kanda-it-school-system.com");

			// メールのタイトルを指定
			if(status == 0) {
				mimeMessage.setSubject("商品が購入されました", "iso-2022-jp");

			}
			
			else{
				mimeMessage.setSubject("商品が発送されました", "iso-2022-jp");
				
				
			}
			// メールの内容を指定
			if(status == 0) {
				
			
			mimeMessage.setText(recipient.getName() + "様\n\n"
					+ "ご出品ありがとうございました。"
					+ "以下の商品が購入されましたので、ご連絡致します。\n\n" 
					+ "商品名：" + merchandise.getProduct_name() + "\n価格：" + merchandise.getPrice()
					+ "円\n備考：" + merchandise.getRemarks()
					+ "\n\nまたのご利用よろしくお願いします。" , "iso-2022-jp");

			}else {
				mimeMessage.setText(recipient.getName() + "様\n\n"
						+ merchandise.getProduct_name() + "のご購入ありがとうざいます。\n"
						+ "以下商品を発送いたしましたので、ご連絡致します。\n\n" 
						+ "商品名：" + merchandise.getProduct_name() + "\n価格：" + merchandise.getPrice()
						+ "円\n備考：" + merchandise.getRemarks()
						+ "\n\nまたのご利用よろしくお願いします。" , "iso-2022-jp");
			}
			
			// メールの形式を指定
			mimeMessage.setHeader("Content-Type", "text/plain; charset=iso-2022-jp");

			// 送信日付を指定
			mimeMessage.setSentDate(new Date());

			// 送信します
			Transport.send(mimeMessage);

			// 送信成功
			System.out.println("送信に成功しました。");

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("送信に失敗しました。\n" + e);
		}
	}
}
