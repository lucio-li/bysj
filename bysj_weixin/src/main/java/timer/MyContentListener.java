package timer;

import org.springframework.web.context.ContextLoaderListener;
import thread.StartTaskThread;
import utils.AppConfig;

import javax.servlet.ServletContextEvent;

/**
 * Created by liqiao on 2018/1/21.
 * 创建listener, 监听服务器启动，自动获取access_token
 */
public class MyContentListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        //------------------生成流程线程-----------------
        StartTaskThread startTaskThread = new StartTaskThread();
        startTaskThread.start();//启动线程定时获取access_token
        System.out.println("启动线程");
//
        //自定义菜单
        while ("".equals(AppConfig.access_token)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
