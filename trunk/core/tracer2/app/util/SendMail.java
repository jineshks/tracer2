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

public class SendMail implements Cloneable{
	
	private static SendMail sendMail = null;
	
	static {
		sendMail = new SendMail();
	}
	 
	
	private SendMail(){
		
	}
	
	public static synchronized SendMail getMailInstance(){
		if(sendMail == null){
			sendMail = new SendMail();
		}
		return sendMail;
	}
	
	public Object  clone()  throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
    public    void sendMail(String receipent, String mail, String subject) {
        try {
            String host = "smtp.gmail.com";

            String from = "tarcertarento@gmail.com";

            String pass = "12qwaszx123";

            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "25");
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
            for (int i = 0; i < toAddress.length; i++) { // changed from a while loop

                    System.out.println(Message.RecipientType.TO + " == " + toAddress[i]);
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            String Text = "" +
                    "<html>" +
                    "<head>" +
                    "</head>" +
                    "<body>" +
                    "<table align='center' width='500' border='0' cellspacing='0' cellpadding='0'>" +
                    "<tr>" +
                    "<td>" +
                    "<img src='' height='31' width='500' />" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    "<table width='100%' border='0' cellspacing='0' cellpadding='0'>" +
                    "<tr>" +
                    "<td width='17px'>" +
                    "<img src='' height='354' width='17' />" +
                    "</td>" +
                    "<td bgcolor='#ebaf02' width='572px' valign='top'>" +
                    "<table width='100%' border='0' cellspacing='0' cellpadding='0'>" +
                    "<tr>" +
                    "<td width='525' valign='top' style='padding-left:10px; font:Helvetica Neue Medium; color:#FFFFFF;'>" +
                    "<br />" +
                    "<br />" +
                    mail +
                    "</td>" +
                    "<td width='60' valign='top' align='right'>" +
                    "<img src='' height='40' width='60' />" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</td>" +
                    "<td width='8px'>" +
                    "<img src='' height='354' width='8'>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    "<img src='' height='21' width='500' alt='' />" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</body>" +
                    "</html>";

            message.setSubject(subject);
            
//            message.setContent(Text, "text/html");
            message.setDataHandler(new DataHandler(new ByteArrayDataSource(Text.toString(), "text/html")));
//            Transport.send(message);

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
    
    public static void main(String[] args) {
		new SendMail().sendMail("manzarul07@gmail.com", "test mail", "new Registration");
	}
}
