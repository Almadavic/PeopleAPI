package br.com.almada.people.service.serviceAction.sendEmailService;

import br.com.almada.people.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class SendEmailPersonService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Async
    public void sendEmailPersonCreated(Person person) {
        sendEmail(person, "Cadastro", "cadastrada");

    }

    @Async
    public void sendEmailPersonUpdated(Person person) {
        sendEmail(person, "Atualização", "atualizada");
    }


    private void sendEmail(Person person, String action, String eventVerb) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(person.getEmail());
        message.setSubject(action + " | People API");
        message.setText(messageText(person, eventVerb));

        javaMailSender.send(message);
    }

    private String messageText(Person person, String eventBerb) {

        StringBuilder sb = new StringBuilder();
        sb.append("-".repeat(30) + "\n");
        sb.append("\n");
        sb.append("Caro(a) " + person.getName() + ", sua pessoa foi " + eventBerb + " no sistema. \n");
        sb.append("\n");
        sb.append("-".repeat(30) + "\n");
        return sb.toString();
    }

}
