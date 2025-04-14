package net.srook.common.mail.infra;

import net.srook.common.mail.dto.EmailMessageId;

import software.amazon.awssdk.services.sesv2.model.Template;

public interface SesV2MailHandler {
    EmailMessageId sendEmailByTemplate(final String from, final String to, final Template template);
}
