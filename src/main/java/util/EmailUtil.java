package util;

import bean.Exam;
import bean.Student;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by nick on 2017/12/10.
 */
public class EmailUtil {

    public static final String HOST = "smtp.163.com";
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 25;
    public static final String FROM = "ots_system@163.com";//发件人的email
    public static final String PWD = "ots123456";//发件人密码

    public static void sendCode(String email, String code, Exam exam, Student student) {
        ///邮件的内容
        StringBuffer sb = new StringBuffer(student.getStudent_name() + "同学，这是你的考试代码：</br>");
        sb.append("<h3>" + code + "</h3>");
        sb.append("考试名称:" + exam.getExam_title() + "</br>");
        sb.append("考试时间:" + exam.getStart_time() + "  ~  " + exam.getEnd_time() + "</br>");
        sb.append("请务必按时参加考试。</br>");

        send(email, "考试代码", sb.toString());
    }

    public static void sendReport(Student student, Exam exam, int score) {
        ///邮件的内容
        StringBuffer sb = new StringBuffer(student.getStudent_name() + "同学，这是你的考试结果：</br>");
        sb.append("考试名称:" + exam.getExam_title() + "</br>");
        sb.append("考试时间:" + exam.getStart_time() + "  ~  " + exam.getEnd_time() + "</br>");
        sb.append("考试成绩:" + "<h3>" + score + "</h3>");

        send(student.getEmail(), "考试成绩", sb.toString());
    }

    /**
     * 获取Session
     *
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol", PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props, authenticator);

        return session;
    }

    private static void send(String toEmail, String subject, String content) {
        Session session = getSession();
        session.setDebug(true);
        try {
            System.out.println("--send--" + content);
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);

            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");

            //Send the message
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
