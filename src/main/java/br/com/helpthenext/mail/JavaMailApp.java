package br.com.helpthenext.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.helpthenext.util.Uteis;

public class JavaMailApp
{
	
	private String remetente = "helpthenext.info@gmail.com";
	private String senha = "helpthenext";
	
      public void enviarEmail(String destinatario, String mensagem, String assunto) {
            Properties props = new Properties();
            
           destinatario = "ana_bavati@hotmail.com";
          
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication(remetente, senha);
                             }
                        });
          
            session.setDebug(true);
            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress(remetente));

                  Address[] toUser = InternetAddress.parse(destinatario);  
                  
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject(assunto);
                  message.setText(mensagem);
                 
                  Transport.send(message);
                 
                  Uteis.MensagemInfo("E-mail enviado com sucesso!");
                  
             } catch (MessagingException e) {
            	 System.err.println(e);
                 Uteis.MensagemInfo("Ocorreu um erro ao enviar o e-mail!");
            }
      }
}
