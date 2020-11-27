package live.dounine.study;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class EmailUtilTest {

   
     //����apache.commons.mail��

    @Test
    public void test_mail() {
        Email email = new SimpleEmail();

        // ����Email.setHostName()�������Ƿ�ɹ�����
        String host = "stmp.qq.com";
        email.setHostName(host);
        assertEquals(host, email.getHostName());

        // ����Email.setFrom()�������Ƿ��ܳɹ��׳��쳣
        try {
            email.setFrom("123456");	//�����˴���ʱ�ܹ��׳��쳣
            fail();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    
     //����Email.isEmpty()����
     
    @Test
    public void test_isEmpty() {
   // 	EmailUtil emailUtil =new EmailUtil();
        EmailUtil emailUtil =null;
        try {
            emailUtil = new EmailUtil();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        assertEquals(true, emailUtil.isEmpty("", "�ַ���Ϊ�գ�"));
        assertEquals(false, emailUtil.isEmpty("abcde", ""));
    }

    
     //  ����EmailManager.send()����
    
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
   public void send_mock() {    //ͨ��׮ģ���send()�������м̳в߲���
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
       //��Ҫ���õ�isEmpty()��������׮ģ��
       EmailUtil email_mock = mock(EmailUtil.class);
       when(email_mock.isEmpty(From, "������Email����Ϊ�գ�")).thenReturn(false); // ����Ԥ�ڷ��ؽ��
       when(email_mock.isEmpty(To, "�ռ�����Email����Ϊ�գ�")).thenReturn(false);
       when(email_mock.isEmpty(sub, "�ʼ����ⲻ��Ϊ�գ�")).thenReturn(false);
       when(email_mock.isEmpty(msg, "�ʼ����ݲ���Ϊ�գ�")).thenReturn(false);
       assertEquals(true, emailUtil.send(From, To, sub, msg)); 
   }
}
