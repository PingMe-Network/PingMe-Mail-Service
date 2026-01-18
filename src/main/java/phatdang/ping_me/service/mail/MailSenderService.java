package phatdang.ping_me.service.mail;

import phatdang.ping_me.dto.request.SendOtpRequest;
import phatdang.ping_me.dto.response.OtpVerificationResponse;

/**
 * @author : user664dntp
 * @mailto : phatdang19052004@gmail.com
 * @created : 15/01/2026, Thursday
 **/
public interface MailSenderService {
    void sendOtpToAdmin(SendOtpRequest request);
}
