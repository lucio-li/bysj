package thread;


import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import utils.AppConfig;
import utils.HttpRequestUtils;
/**
 * Created by liqiao on 2018/1/21.
 * 启动线程获取access_token
 */
public class StartTaskThread extends Thread{
    private static Logger logger = Logger.getLogger(StartTaskThread.class);
    public void run() {
        while (true) {
            try{
                String param = "grant_type=client_credential&appid=" + AppConfig.appid +
                        "&secret=" + AppConfig.appsecret;
                String result = HttpRequestUtils.sendGet(AppConfig.wx_token_url, param);
                System.out.println(result);
                JSONObject object = JSONObject.fromObject(result);
                AppConfig.access_token = object.getString("access_token");
                System.out.println("access_token" + AppConfig.access_token);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("流程启动线程报错！");
                logger.error(e);
            }
            try {
                Thread.sleep(1*60*60*1000);//每个小时获取一次
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
