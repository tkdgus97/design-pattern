package facade.after;

import java.util.Properties;

public class EmailSender {
    private EmailSettings emailSettings;

    public EmailSender(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

//    public void sendEmail(EmailMessage message) {
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//
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
//    }
}
