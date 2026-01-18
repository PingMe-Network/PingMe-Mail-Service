package phatdang.ping_me.controller.mail;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import phatdang.ping_me.dto.request.SendOtpRequest;
import phatdang.ping_me.dto.response.ApiResponse;
import phatdang.ping_me.service.mail.MailSenderService;

/**
 * @author : user664dntp
 * @mailto : phatdang19052004@gmail.com
 * @created : 15/01/2026, Thursday
 **/
@RestController
@RequestMapping("mail-management/api/v1/mails")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailSenderController {
    MailSenderService mailSenderService;

    @PostMapping("/admin-otp-verification")
    ApiResponse<Boolean> sendOtpToAdmin(@RequestBody SendOtpRequest request){
        boolean valid = true;
        try {
            mailSenderService.sendOtpToAdmin(request);
        }catch (Exception e){
            valid = false;
        }

        return ApiResponse.<Boolean>builder()
                .errorCode(HttpStatus.OK.value())
                .errorMessage(HttpStatus.OK.name())
                .data(valid)
                .build();
    }
}
