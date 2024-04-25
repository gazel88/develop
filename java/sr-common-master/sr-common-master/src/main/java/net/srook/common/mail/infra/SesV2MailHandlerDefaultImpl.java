package net.srook.common.mail.infra;

import org.springframework.stereotype.Component;

import net.srook.common.exception.SREmailException;
import net.srook.common.mail.dto.EmailMessageId;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.*;

@Slf4j
@Component
public class SesV2MailHandlerDefaultImpl implements SesV2MailHandler {
    private final SesV2Client sesv2Client;

    public SesV2MailHandlerDefaultImpl(final SesV2Client sesv2Client) {
        this.sesv2Client = sesv2Client;
    }

    @Override
    public EmailMessageId sendEmailByTemplate(final String from, final String to, final Template template) {
        try {
            SendEmailRequest emailRequest = SendEmailRequest.builder()
                    .destination(destination -> destination.toAddresses(to))
                    .content(content -> content.template(template))
                    .fromEmailAddress(from)
                    .build();
            final SendEmailResponse sendEmailResponse = this.sesv2Client.sendEmail(emailRequest);
            return new EmailMessageId(sendEmailResponse.messageId());
        } catch (SesV2Exception e) {
            log.warn("ERROR: Email Error. {}", e.awsErrorDetails().errorMessage());
            throw new SREmailException("이메일 전송 중 오류가 발생했습니다.", e);
        }
    }
}
