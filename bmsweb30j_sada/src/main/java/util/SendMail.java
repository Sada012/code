package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bean.Book;
import bean.Order;
import bean.User;

public class SendMail {

	// 取得したArrayListの情報をStringに格納するメソッド
	public String setText(ArrayList<Book> book_list, ArrayList<Order> order_list) {
		String text = "";
		int total = 0;
		if (book_list != null) {
			for (int i = 0; i < book_list.size(); i++) {
				Book book = (Book) book_list.get(i);
				Order order = (Order) order_list.get(i);
				total += book.getPrice() * order.getQuantity();
				text += book.getIsbn() + " ";
				text += book.getTitle() + " ";
				text += book.getPrice() + "円 ";
				text += order.getQuantity() + "冊\n";
			}
		}
		text += "合計 " + total + "円\n";
		return text;
	}

	public void post(User user, ArrayList<Book> book_list, ArrayList<Order> order_list) {
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
			mimeMessage.setRecipients(Message.RecipientType.TO, "k_sada@brain-tr.co.jp");

			// メールのタイトルを指定
			mimeMessage.setSubject("ご購入内容の確認", "iso-2022-jp");

			// メールの内容を指定
			mimeMessage.setText(user.getUserid() + "様\n\n"
					+ "本のご購入ありがとうざいます。\n"
					+ "以下内容でご注文を受け付けましたので、ご連絡致します。\n\n" 
					+ setText(book_list, order_list)
					+ "またのご利用よろしくお願いします。" , "iso-2022-jp");

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
