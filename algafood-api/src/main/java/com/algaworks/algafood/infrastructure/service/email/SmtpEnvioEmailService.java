package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

/**
 * 15.3. Implementando o serviço de infraestrutura de envio de e-mails com Spring<p>
 * 15.4. Usando o serviço de envio de e-mails na confirmação de pedidos<p>
 * 15.5. Processando template do corpo de e-mails com Apache FreeMarker<p>
 * 15.8. Desafio: implementando serviço de envio de e-mail fake<p>
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

    @Autowired
    private Configuration freemarkerConfig;

    public void enviar(Mensagem mensagem) {
        try {
            String corpo = processarTemplate(mensagem);
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            helper.setSubject(mensagem.getAssunto());
            helper.setText(corpo, true);

            mailSender.send(mimeMessage);


        } catch (Exception e) {
            throw new EmailException("Nao foi possivel enviar e-mail", e);
        }
    }

    protected String processarTemplate(Mensagem mensagem) {
        try {
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Nao foi possivel montar o template do e-mail", e);
        }

    }
}