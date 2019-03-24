import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

/**
 * Created by wangliyong on 2019/3/20.
 */
public class EmailTest {
    @Test
    public void EmialTest(){
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");
            email.addTo("18260095973@163.com");
            email.setFrom("18260095973@163.com","Activity sharing");
            email.setAuthentication("18260095973@163.com","Gn270588");
            email.setSubject("测试邮件服务!");
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + "12345");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
