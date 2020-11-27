package live.dounine.study;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class EmailUtilTest {

   
     //测试apache.commons.mail库

    @Test
    public void test_mail() {
        Email email = new SimpleEmail();

        // 测试Email.setHostName()函数，是否成功设置
        String host = "stmp.qq.com";
        email.setHostName(host);
        assertEquals(host, email.getHostName());

        // 测试Email.setFrom()函数，是否能成功抛出异常
        try {
            email.setFrom("123456");	//发件人错误时能够抛出异常
            fail();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    
     //测试Email.isEmpty()函数
     
    @Test
    public void test_isEmpty() {
   // 	EmailUtil emailUtil =new EmailUtil();
        EmailUtil emailUtil =null;
        try {
            emailUtil = new EmailUtil();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        assertEquals(true, emailUtil.isEmpty("", "字符串为空！"));
        assertEquals(false, emailUtil.isEmpty("abcde", ""));
    }

    
     //  测试EmailManager.send()函数
    
    @Test
    public void test_send() {
        EmailUtil emailUtil = null;
        try {
            emailUtil = new EmailUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true, emailUtil.send("1398775803@qq.com", "1398775803@qq.com", "Test", "test"));
        assertEquals(false, emailUtil.send("1398775803@qq.com", "", "Test", "test"));
        assertEquals(false, emailUtil.send("", "1398775803@qq.com", "Test", "test"));
        assertEquals(false, emailUtil.send("1398775803@qq.com", "1398775803@qq.com", "", "test"));
        assertEquals(false, emailUtil.send("1398775803@qq.com", "1398775803@qq.com", "Test", ""));
    }

   @Test
   public void send_mock() {    //通过桩模块对send()方法进行继承策测试
	   EmailUtil emailUtil = null;
       try {
           emailUtil = new EmailUtil();
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       String From = "1398775803@qq.com";
       String To = "1398775803@qq.com";
       String sub = "Test";
       String msg = "test";
       
       assertEquals(true, emailUtil.send(From, To, sub, msg)); 
       //对要调用的isEmpty()方法设置桩模块
       EmailUtil email_mock = mock(EmailUtil.class);
       when(email_mock.isEmpty(From, "发件人Email不能为空！")).thenReturn(false); // 设置预期返回结果
       when(email_mock.isEmpty(To, "收件人人Email不能为空！")).thenReturn(false);
       when(email_mock.isEmpty(sub, "邮件主题不能为空！")).thenReturn(false);
       when(email_mock.isEmpty(msg, "邮件内容不能为空！")).thenReturn(false);
       assertEquals(true, emailUtil.send(From, To, sub, msg)); 
   }
}
