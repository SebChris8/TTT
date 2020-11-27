package live.dounine.study;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

//import java.io.IOException;

 
 // 邮件工具类

public class EmailUtil {
    private String host="smtp.qq.com";   //邮件服务器地址
    private String port="465";				//邮件服务器端口
    private String username="1398775803@qq.com";    //登录用户名
    private String passwd="kyutjzrzprajjcgf";	//第三方登录验证码

   
    public EmailUtil() throws Exception{

        // 检查邮件服务器信息
        if (host.equals("") || port.equals("") || username=="" || passwd.equals("")) {
        		//System.out.println("error!!!");
        		throw new Exception("存在为空项");
        }
    }

   
      //构造函数，设置邮件服务器相关信息
     // host 邮件服务器地址
     // port 邮件服务器端口
     // username 登录邮件服务器的用户名
     //passwd 登录邮件服务器的密码
     // Exception 当存在为空项时，抛出异常
     
    public EmailUtil(String host, String port, String username, String passwd) throws Exception {
      //  this.host = host;
       // this.port = port;
       // this.username = username;
       // this.passwd = passwd;

        // 检查邮件服务器信息
        if (isEmpty(host, "邮件服务器地址不能为空！")
                || isEmpty(port, "邮件服务器端口不能为空！")
                || isEmpty(username, "用户名不能为空！")
                || isEmpty(passwd, "密码不能为空！")) {
            throw new Exception("存在为空项");
        }
    }

  
    //检查字符串是否为空，若为空则输出错误信息
      //string 待测字符串
     //errMsg 错误信息
     
    public boolean isEmpty(String string, String errMsg) {
        if (string.equals("")) {
        	return true;
       //     System.out.println(errMsg);
            
        }
        else {
            return false;
        }
    }

  
     // 发送邮件
    // fromEmail 发件人Email
     //toEmail 收件人Email
     // subject 主题
      //msg 内容
    
    public boolean send(String fromEmail, String toEmail, String subject, String msg) {
    	 // 检查邮件信息
        if (isEmpty(fromEmail, "发件人Email不能为空！")
                || isEmpty(toEmail, "收件人人Email不能为空！")
                || isEmpty(subject, "邮件主题不能为空！")
                || isEmpty(msg, "邮件内容不能为空！")) {
            System.out.println("存在为空项！邮件发送失败！");
            return false;
        }

        try {
            Email email = new SimpleEmail(); // org.apache.commons.mail.Email对象

            // 设置邮件服务器信息
            email.setHostName(host);
            email.setSSLOnConnect(true);
            email.setSslSmtpPort(port);
            email.setAuthentication(username, passwd);

            // 设置额外信息
            email.setCharset("utf-8");
           // email.setDebug(true); // 开启debug调试输出信息

            // 设置邮件的发件人、主题、内容、收件人
            email.setFrom(fromEmail);
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(toEmail);
       
            // 发送邮件
            String msgId = email.send();

            System.out.println("邮件已发送：" + msgId);
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败！");
            return false;
        }
    }
}
