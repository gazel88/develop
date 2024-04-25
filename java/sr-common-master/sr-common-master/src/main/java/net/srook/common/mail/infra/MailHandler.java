package net.srook.common.mail.infra;

import java.io.InputStream;

public interface MailHandler {

    void sendEmail(final String from, final String to, final String title, final String content, final boolean isHtmlText);

    void sendEmailWithAttachment(final String from, final String to, final String title, final String content,
                                 final String attachmentName, final InputStream attachment, final boolean isHtmlText);
}
