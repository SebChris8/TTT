package live.dounine.study;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void sendTime(int n) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获取日期格式
        EmailUtil emailUtil = new EmailUtil();
        for (int i = 0; i < n; i++) {
            String time = df.format(new Date()); // new Date()获取系统时间
            emailUtil.send("1398775803@qq.com", "1398775803@qq.com", "Time", time);  //发送邮件
        }
    }

    public static void main(String [] agrs) throws Exception {
        sendTime(1);  
    }
}
