package cnblogs.chenbenbuyi;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author chen
 * @date 2021/4/6 21:33
 * @Description
 */

@SpringBootTest
public class MailTest {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    /**
     * 简单邮件发送
     */
    @Test
    public void simpleMailTest() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("邮件主题");
        simpleMailMessage.setText("邮件文本内容");
        simpleMailMessage.setTo("jicky2008chen@126.com");
        simpleMailMessage.setFrom("jicky2008chen@126.com");
        javaMailSender.send(simpleMailMessage);
    }


    /**
     * 复杂邮件发送
     *  测试遇异常：IllegalStateException: Not in multipart mode
     *  原因： new MimeMessageHelper(mimeMessage) 应该设置 第二个参数 multipart 为true
     */
    @Test
    public void mimeMessageTest() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("邮件主题");
        helper.setText("<p style='color:red'>复杂邮件文本内容!</p>", true);
        // 附件
        helper.addAttachment("0.jpg", FileUtil.file("C:\\Users\\Administrator\\Desktop\\0.jpg"));
        helper.setTo("jicky2008chen@126.com");
        helper.setFrom("jicky2008chen@126.com");
        javaMailSender.send(mimeMessage);
    }
}
