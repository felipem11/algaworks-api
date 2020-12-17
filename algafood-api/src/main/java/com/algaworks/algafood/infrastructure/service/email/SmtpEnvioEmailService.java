package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * 15.3. Implementando o serviço de infraestrutura de envio de e-mails com Spring<p>
 * 15.4. Usando o serviço de envio de e-mails na confirmação de pedidos<p>
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    public void enviar(Mensagem mensagem) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            helper.setSubject(mensagem.getAssunto());
            helper.setText(mensagem.getCorpo(), true);

            System.out.println(emailProperties.getRemetente());
            System.out.println(mimeMessage.getFrom());
            System.out.println(mimeMessage.getSender());
            System.out.println(mimeMessage.getSubject());
            System.out.println(mimeMessage.getContent());
            System.out.println(mimeMessage.getSize());

            mailSender.send(mimeMessage);


        } catch (Exception e) {
            throw new EmailException("Nao foi possivel enviar e-mail", e);
        }
    }
}