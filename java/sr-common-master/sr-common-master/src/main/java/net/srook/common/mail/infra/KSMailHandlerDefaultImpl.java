package net.srook.common.mail.infra;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import net.srook.common.exception.SREmailException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KSMailHandlerDefaultImpl implements MailHandler {
    private static final String UTF_8_NAME = StandardCharsets.UTF_8.name();
    private final JavaMailSender javaMailSender;

    public KSMailHandlerDefaultImpl(final String host, final int port, final String userName, final String password) {
        final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(userName);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(getDefaultProperties(javaMailSender));
        this.javaMailSender = javaMailSender;
    }

    private Properties getDefaultProperties(final JavaMailSenderImpl javaMailSender) {
        final Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

    @Override
    public void sendEmail(final String from, final String to, final String title, final String content,
                          final boolean isHtmlText) {
        try {
            sendEmailOrThrow(from, to, title, content, isHtmlText);
        } catch (MessagingException e) {
            throw new SREmailException("메일 생성 중 오류가 발생하였습니다.");
        }
    }

    @Override
    public void sendEmailWithAttachment(final String from, final String to, final String title, final String content,
                                        final String attachmentName, final InputStream attachment, final boolean isHtmlText) {
        try {
            sendEmailWithAttachmentOrThrow(from, to, title, content, attachmentName, attachment, isHtmlText);
        } catch (IOException e) {
            throw new SREmailException("첨부파일 생성 중 오류가 발생하였습니다.");
        } catch (Exception e) {
            throw new SREmailException("메일 생성 중 오류가 발생하였습니다.");
        }
    }

    private void sendEmailOrThrow(final String from, final String to, final String title, final String content,
                                  final boolean isHtmlText) throws MessagingException {
        final MimeMessageHelper mimeMessageHelper =
                makeDefaultMimeMessageHelper(from, to, title, content, false, isHtmlText);
        this.javaMailSender.send(mimeMessageHelper.getMimeMessage());
    }

    private void sendEmailWithAttachmentOrThrow(final String from, final String to, final String title,
                                                final String content, final String attachmentName,
                                                final InputStream attachment, final boolean isHtmlText)
            throws MessagingException, IOException {
        final MimeMessageHelper mimeMessageHelper =
                makeDefaultMimeMessageHelper(from, to, title, content, true, isHtmlText);
        mimeMessageHelper.addAttachment(attachmentName, new ByteArrayResource(attachment.readAllBytes()));
        this.javaMailSender.send(mimeMessageHelper.getMimeMessage());
    }

    private MimeMessageHelper makeDefaultMimeMessageHelper(final String from, final String to, final String title,
                                                           final String content, final boolean isMultipart, final boolean isHtmlText)
            throws MessagingException {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, isMultipart, UTF_8_NAME);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(content, isHtmlText);
        return mimeMessageHelper;
    }
}
