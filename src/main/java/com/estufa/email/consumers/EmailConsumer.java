package com.estufa.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.estufa.email.dtos.EmailDto;
import com.estufa.email.dtos.EstufaEmailDto;
import com.estufa.email.models.EmailModel;
import com.estufa.email.services.EmailService;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EstufaEmailDto emailDto) {
        EmailModel emailModel = new EmailModel();

        emailModel.setOwnerRef("Sistema Estufa");
        emailModel.setEmailFrom("patrick.dias@estudante.ifms.edu.br");
        emailModel.setEmailTo(emailDto.emailTo());
        emailModel.setSubject("Alerta: Condições Críticas na Estufa");

        String corpo = String.format(
                "Monitoramento:\nTemperatura: %.2f°C\nSensação Térmica: %.2f°C\nUmidade: %.2f%%",
                emailDto.temperaturaEstufa(),
                emailDto.sensacaoTermicaEstufa(),
                emailDto.umidadeEstufa());
        emailModel.setBody(corpo);

        emailService.sendEmail(emailModel);
        System.out.println("Email status: " + emailModel.getStatusEmail().toString());
    }
}
