package util;

import java.io.*;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * this api is used to send mail.
 * 
 * @author Manzarul.Haque
 * 
 */
public class SendMail implements Cloneable {

	private static final String className = SendMail.class.getName();
	private static SendMail sendMail = null;

	static {
		sendMail = new SendMail();
	}

	private SendMail() {

	}

	/**
	 * this method will provide mail class instance.
	 * 
	 * @return
	 */
	public static synchronized SendMail getMailInstance() {
		if (sendMail == null) {
			sendMail = new SendMail();
		}
		return sendMail;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * this method is for sending mail.
	 * 
	 * @param receipent
	 *            user email id String.
	 * @param mail
	 *            mail body String.
	 * @param subject
	 *            mail subject String.
	 */
	public void sendMail(String receipent, String mail, String subject) {
		try {
			String host = "smtp.gmail.com";

			String from = "tracertarento@gmail.com";

			String pass = "12qwaszx123";

			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");

			String[] to = new String[1];

			to[0] = receipent;
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) { // changed from a while loop

				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) { // changed from a while
														 // loop

				System.out.println(Message.RecipientType.TO + " == " + toAddress[i]);
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			String Text = "<html><head>"
			        + "</head> <body align='center'>"
			        + " <div align='center'> "
			        + " <div style='width:550;height:550; color:blue; border-style: none;border-color: #008000;'  >"
			        + "<div style='width:550;height:550; background-color: #EBAF02;text-align: left;' > "
			        + " <img src='file:///C:/Users/Manzarul.Haque/Desktop/cabhound-logo.png' height='30' width='50' alt='' align='right' />"
			        + " <br/><br/>"
			        + "  <p>"
			        + " Hi  Manzarul ,"
			        + "</p>"
			        + "<p style='word-wrap:break-word;'>"
			        + mail
			        + "</p><br/><br/>"
			        + "<div style='background-color: black;'><p style='color:white;' align='center'>"
			        + " contact us:"
			        + " <br/>"
			        + " phone: 9663890445 "
			        + "<br/>"
			        + "Email:manzarul07@gmail.com "
			        + "</p></div>"
			        + "<div style='margin_bottom:10px;'>"
			        + " <img src='file:///C:/Users/Manzarul.Haque/Desktop/cabhound-logo.png' height='30' width='50' alt='' align='left' />"
			        + "</div><br/><br/></div></div> </div></body></html>";

			message.setSubject(subject);

			// message.setContent(Text, "text/html");
			message.setDataHandler(new DataHandler(new ByteArrayDataSource(Text.toString(), "text/html")));
			// Transport.send(message);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			System.err.println("From sending Email " + e);
		}
	}

	class ByteArrayDataSource implements DataSource {

		private byte[] data; // data
		private String type; // content-type

		/* Create a DataSource from an input stream */
		public ByteArrayDataSource(InputStream is, String type) {
			this.type = type;
			try {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				int ch;

				while ((ch = is.read()) != -1) {
					os.write(ch);
				}
				data = os.toByteArray();

			} catch (IOException ioex) {
				TrackLogger.error(ioex.getMessage(), className);
			}
		}

		/* Create a DataSource from a byte array */
		public ByteArrayDataSource(byte[] data, String type) {
			this.data = data;
			this.type = type;
		}

		/* Create a DataSource from a String */
		public ByteArrayDataSource(String data, String type) {
			try {
				// Assumption that the string contains only ASCII
				// characters! Otherwise just pass a charset into this
				// constructor and use it in getBytes()
				this.data = data.getBytes("iso-8859-1");
			} catch (UnsupportedEncodingException uex) {
				TrackLogger.error(uex.getMessage(), className);
			}
			this.type = type;
		}

		public InputStream getInputStream() throws IOException {
			if (data == null) {
				throw new IOException("no data");
			}
			return new ByteArrayInputStream(data);
		}

		public OutputStream getOutputStream() throws IOException {
			throw new IOException("cannot do this");
		}

		public String getContentType() {
			return type;
		}

		public String getName() {
			return "dummy";
		}
	}

	/**
	 * main method to check mail.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new SendMail()
		        .sendMail(
		                "manzarul.haque@tarento.com",
		                "test mail fkdjgggggggggggggggggggggggggggggfhhjdfriouwerjgkfgfkjkjgfdkjhkgkjhhkjhkjhkhyttiyfnmgmngfnhghjhfdioihhhjgfjgytuioiudfriouwerjgkfgfkjkjgfdkjhkgkjhhkjhkjhkhyttiyfnmgmngfnhghjhfdioihhhjgfjgytuioiudfriouwerjgkfgfkjkjgfdkjhkgkjhhkjhkjhkhyttiyfnmgmngfnhghjhfdioihhhjgfjgytuioiu",
		                "new Registration");
	}
}
