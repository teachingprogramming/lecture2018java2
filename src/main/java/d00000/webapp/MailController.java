package d00000.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mail")
public class MailController {

    private MailSender mailSender;

    /** コンストラクタ */
    public MailController(@Autowired MailSender mailSender) {
        this.mailSender = mailSender;
    }

    /** メール送信フォーム */
    @GetMapping("/send")
    public String sendGet() {
        return "mail/send";
    }

    /** メール送信処理 */
    @PostMapping("/send")
    public String sendPost(@RequestParam("to") String to, @RequestParam("subject") String subject,
                           @RequestParam("text") String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        this.mailSender.send(simpleMailMessage);
        return "redirect:/mail/send";
    }
}
