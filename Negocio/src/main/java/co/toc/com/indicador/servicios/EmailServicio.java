package co.toc.com.indicador.servicios;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServicio {

    private final JavaMailSender sender;

    public EmailServicio(JavaMailSender sender) {
        this.sender = sender;
    }

    public boolean EnviarEmail (String asunto, String contenido, String destinatario){

        MimeMessage mensaje = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {
            helper.setText(contenido, true);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            sender.send(mensaje);

            return true;

        } catch (MessagingException e) {
            System.out.println("INFORMACIÓN "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
