package com.example.demo.Controller;

import com.example.demo.dao.HeadLineText;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;



public class HeadLineController {
    public final static String ENCODE = "UTF-8";
    public static Date date = new Date();
    /**
     * 调用对方接口方法
     *
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     * @return
     */
    public static String interfaceUtil(String path, String data) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            //请求方式
//          conn.setRequestMethod("POST");
//           //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
            out.print(data);
            //缓冲数据
            out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            StringBuilder sb = new StringBuilder();
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();

            System.out.println("查询结束");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
    //json数据解析

    @RequestMapping(value = "/HeadLine",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin

    public static List<HeadLineText> JsonTest() throws JSONException {
        String Jsondata=interfaceUtil("http://zhouxunwang.cn/data/?id=75&key=XO6V+4FnGNX+iJmB8Yo1RGnJOwTgsJeZ/px16A&type=top", "");
        JSONObject jsonObject = new JSONObject(Jsondata);
        JSONObject jsonObject1 = new JSONObject(jsonObject.getString("result"));
        JSONArray jsonArray = jsonObject1.getJSONArray("data");
        List<HeadLineText> headLineList = new ArrayList<HeadLineText>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
            HeadLineText headLineText = new HeadLineText();
            headLineText.setUniquekey(jsonObject2.getString("uniquekey"));
            headLineText.setAuthor_name(jsonObject2.getString("author_name"));
            headLineText.setCategory(jsonObject2.getString("category"));
            headLineText.setDate(jsonObject2.getString("date"));
            headLineText.setTitle(jsonObject2.getString("title"));
            headLineText.setUrl(jsonObject2.getString("url"));
            headLineText.setThumbnail_pic_s(jsonObject2.getString("thumbnail_pic_s"));
            headLineList.add(headLineText);
        }
        return headLineList;
    }

//    //文本格式文件
//    public static void sendMail() {
//
//        try {
//// 创建一个程序与邮件服务器会话对象session
//            //Properties props = new Properties();
//            Properties props=new Properties();
//            props.setProperty("mail.transport.protocol", "SMTP");
//            props.setProperty("mail.smtp.host", "smtp.yuanian.com");
//            props.setProperty("mail.smtp.port", "25");
//// 指定验证为true
//            props.setProperty("mail.smtp.auth", "true");
//            props.setProperty("mail.smtp.timeout", "4000");
//
//// 验证账号及密码，密码需要是第三方授权码
//            Authenticator auth = new Authenticator() {
//                @Override
//                public PasswordAuthentication getPasswordAuthentication() {
//
//                    return new PasswordAuthentication("zhangtx@yuanian.com", "ghost202435");
//                }
//            };
//
//            Session session = Session.getInstance(props, auth);
//// session.setDebug(true);//设置debug模式，可以查看详细的发送
//// 创建一个message,它相当于是邮件内容
//
//            Message message = new MimeMessage(session);
//// 设置发送者
//            message.setFrom(new InternetAddress("zhangtx@yuanian.com"));
//            message.setRecipient(MimeMessage.RecipientType.TO,
//                    new InternetAddress("zhangtx@yuanian.com"));
////            message.setRecipient(MimeMessage.RecipientType.CC,
////                    new InternetAddress("qiugch@yuanian.com"));
////            message.setRecipient(MimeMessage.RecipientType.CC,
////                    new InternetAddress("wangmh@yuanian.com"));
////            message.setRecipient(MimeMessage.RecipientType.CC,
////                    new InternetAddress("643952105@qq.com"));
//// 设置主题
//            Date date = new Date();
//            message.setSubject("春蕾学员培训-新闻-张天旭"+date);
//
//// 设置内容
//            String s1;
//            String s2="";
//
//            s1="头条新闻列表："+"<br/>";
//            String data = interfaceUtil("http://zhouxunwang.cn/data/?id=75&key=XO6V+4FnGNX+iJmB8Yo1RGnJOwTgsJeZ/px16A&type=top", "");
//            List<HeadLineText> newsList = JsonTest(data);
//            for (int i=0;i<newsList.size();i++){
//                s2="<tr>"+"<td>"+newsList.get(i).getTitle()+"</td>"
//                        +"<td>"+newsList.get(i).getAuthor_name()+"</td>"
//                        +"<td>"+newsList.get(i).getCategory()+"</td>"
//                        +"<td>"+newsList.get(i).getUrl()+"</td>"
//                        +"<td>"+newsList.get(i).getThumbnail_pic_s()+"</td>"
//                        +"<td>"+newsList.get(i).getThumbnail_pic_s02()+"</td>"
//                        +"<td>"+newsList.get(i).getDate()+"</td>"
//                        +"</tr>"+s2;
//            }
//
//            message.setText(s1+"<table border=\\\"5\\\" style=\\\"border:solid 1px #E8F2F9;font-size=14px;;font-size:18px;>" +
//                    "<tr style=\\\"background-color: #428BCA; color:#ffffff\\\"><th>标题</th><th>作者</th><th>类型</th><th>URL</th><th>图片1</th><th>图片2</th><th>时间</th></tr>" +
//                    s2 +
//                    "</table>");
//// 设置发信时间
//            message.setSentDate(new Date());
//// 创建 Transport用于将邮件发送
//            Transport.send(message);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }

//    // 发送Html格式邮件
//    	public static void sendHtmlMail() throws Exception {
//            // spring提供的邮件实现类
//            JavaMailSenderImpl send = new JavaMailSenderImpl();
//            Properties props=new Properties();
//            props.setProperty("mail.transport.protocol", "smtp");
//            props.setProperty("mail.smtp.host", "smtp.yuanian.com");
//            props.setProperty("mail.smtp.port", "25");
//            // 指定验证为true
//            props.setProperty("mail.smtp.auth", "true");
//            props.setProperty("mail.smtp.timeout", "4000");
//
//            send.setUsername("zhangtx@yuanian.com"); // 设置用户名
//            send.setPassword("ghost202435"); // 设置密码
//            send.setJavaMailProperties(props);
//            MimeMessage msg = send.createMimeMessage();
//
//            MimeMessageHelper helper = new MimeMessageHelper(msg, ENCODE);
//            // 发送方邮箱
//            helper.setFrom("zhangtx@yuanian.com");
//            // 接收方邮箱
//            helper.setTo("643952105@qq.com");
//            helper.setSentDate(date);
//            helper.setSubject("春蕾学员培训-新闻-张天旭"+date);
//            // 设置主题
//
//// 设置内容
//            String s1;
//            String s2="";
//
//            s1="头条新闻列表："+"<br/>";
//            String data = interfaceUtil("http://zhouxunwang.cn/data/?id=75&key=XO6V+4FnGNX+iJmB8Yo1RGnJOwTgsJeZ/px16A&type=top", "");
//            List<HeadLineText> newsList = JsonTest(data);
//            for (int i=0;i<newsList.size();i++){
//                s2="<tr>"+"<td>"+newsList.get(i).getTitle()+"</td>"
//                        +"<td>"+newsList.get(i).getAuthor_name()+"</td>"
//                        +"<td>"+newsList.get(i).getCategory()+"</td>"
//                        +"<td>"+newsList.get(i).getUrl()+"</td>"
//                        +"<td>"+newsList.get(i).getThumbnail_pic_s()+"</td>"
//                        +"<td>"+newsList.get(i).getThumbnail_pic_s02()+"</td>"
//                        +"<td>"+newsList.get(i).getDate()+"</td>"
//                        +"</tr>"+s2;
//            }
//            String html = s1+"<table border=\\\"5\\\" style=\\\"border:solid 1px #E8F2F9;font-size=14px;;font-size:18px;>" +
//                    "<tr style=\\\"background-color: #428BCA; color:#ffffff\\\"><th>标题</th><th>作者</th><th>类型</th><th>URL</th><th>图片1</th><th>图片2</th><th>时间</th></tr>" +
//                    s2 +
//                    "</table>";
//            helper.setText(html, true); // 邮件内容，参数true表示是html代码
//            send.send(msg);
//            System.out.println("Successfully  send mail to the user");
//        }
//    public static void main(String[] args) throws Exception {
////        String data = interfaceUtil("http://zhouxunwang.cn/data/?id=75&key=XO6V+4FnGNX+iJmB8Yo1RGnJOwTgsJeZ/px16A&type=top", "");
////        List<HeadLineText> headLineTexts = JsonTest(data);
////        sendMail();
//        sendHtmlMail();
////        for (int i = 0; i <headLineTexts.size() ; i++) {
////            JTable jTable = new JTable();
////
////            System.out.println(headLineTexts.get(i));
////        }
//
//    }
}