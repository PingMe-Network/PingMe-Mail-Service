package phatdang.ping_me.service.mail.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import phatdang.ping_me.dto.request.SendOtpRequest;
import phatdang.ping_me.model.constant.OtpType;
import phatdang.ping_me.service.mail.MailSenderService;

/**
 * @author : user664dntp
 * @mailto : phatdang19052004@gmail.com
 * @created : 15/01/2026, Thursday
 **/
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailSenderServiceImpl implements MailSenderService {

    TemplateEngine templateEngine;
    JavaMailSender javaMailSender;

    @NonFinal
    @Value("${spring.mail.username}")
    String mailSender;

    @NonFinal
    @Value("${spring.mail.timeout}")
    String timeout;

    @Override
    public void sendOtp(SendOtpRequest request) {
        Context context = new Context();
        context.setVariable("otp", request.getOtp());
        context.setVariable("expiry", timeout);

        String template = request.getOtpType() == OtpType.ADMIN_VERIFICATION
                ? "mail/admin-otp-verification.html"
                : "mail/forget-password-otp-verification";

        String htmlContent = templateEngine.process(template, context);

        MimeMessage mime = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mime, true);
            helper.setFrom(mailSender);
            helper.setTo(request.getToMail());
            helper.setSubject("OTP Verification");
            helper.setText(htmlContent, true);
            javaMailSender.send(mime);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
