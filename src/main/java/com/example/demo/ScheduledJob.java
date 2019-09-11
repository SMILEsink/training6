package com.example.demo;

import com.example.demo.dao.user_info;
import org.jboss.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 自定义定时任务
 */
public class ScheduledJob implements Job {

    private static final Logger logger= Logger.getLogger(ScheduledJob.class);
    @Autowired
    private com.example.demo.mapper.user_infoMapper user_infoMapper;

    /**
     * 调用对方接口方法
     *
     * @param path 对方或第三方提供的路径
     * @return
     */
    public static String interfaceUtil(String path) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            //请求方式
            conn.setRequestMethod("POST");
            //设置通用的请求属性
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
    public  void JsonAnalysis(String Jsondata) throws JSONException {
        JSONObject jsonObjects = new JSONObject(Jsondata);
        System.out.println(jsonObjects.getString("username"));
        user_info user_info = new user_info();
        user_info.setUsername(jsonObjects.getString("username"));
        user_info.setUsercode(jsonObjects.getString("usercode"));
        user_info.setDpartmentCode(jsonObjects.getString("department"));
        user_info.setStationCode(jsonObjects.getString("station"));
        if (user_infoMapper.selectByUsercode(user_info.getUsercode())==null){
            user_infoMapper.insert(user_info);
        }
        else {
            return;
        }
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

//        String data = interfaceUtil("http://192.168.51.159:80/userInfos");
//        try {
//            JsonAnalysis(data);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //执行任务逻辑....
        logger.info("执行自定义定时任务");
    }
}
