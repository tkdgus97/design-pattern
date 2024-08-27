package facade.before;

import java.util.Properties;

public class Client {
    public static void main(String[] args) {
        String to = "받는 사람";
        String from = "보내는 사람";
        String host = "127.0.0.1";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

//        Session session = Session.getDefaultInstance(properties);
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientTyp.TO, new InternetAddress(to));
//            message.setSubject("Test mail");
//
//            message.setText("message");
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.pritStackTrace();
//        }
    }
}
