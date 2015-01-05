package com.gxbl.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

/**
 * websocket简单的命令行echo例子,为了测试采用命令行方式,事件开发可用Android。
 * @author xianghan
 */
public class WebSocketClientMain extends WebSocketClient {

    public final static String serverUri = "ws://localhost:8080/MyWebSocketServer/enp";
    
    /**
     * 入口函数不解释
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        WebSocketClientMain wc = null;
        try {
            //初始化客户端
            wc = new WebSocketClientMain(new URI(serverUri),new Draft_17());
            //连接
            wc.connect();
        } catch (URISyntaxException ex) {
            Logger.getLogger(WebSocketClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        //失败退出
        if(wc==null) return;
        
        //读取命令行输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line =br.readLine();
        
        //如果输入ex退出
        while(!line.equals("ex")) {
            
            //自定义的json消息
            JsonMessage json = new JsonMessage(1,JsonMessage.USER_LOGIN,line);
            
            //转换成json字符串发送到服务器
            wc.send(json.toJson());
            
            //阻塞程序直到下一行输入才返回。
            line = br.readLine();
        }
        //关闭客户端
        wc.close();
    }

    public WebSocketClientMain(URI serverURI) {
        super(serverURI);
    }

    public WebSocketClientMain(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }
    
    /**
     * 连接打开时触发
     * @param sh 
     */
     @Override
    public void onOpen(ServerHandshake sh) {
        System.out.println("HttpStatus: " + sh.getHttpStatus());
        System.out.println("HttpStatusMessage:" + sh.getHttpStatusMessage());
         //send("hi how are you?");
    }

    /**
     * 接受消息时触发
     * @param string 
     */
    @Override
    public void onMessage(String string) {
        //直接打印服务器穿过来的消息
        System.out.println(string);
    }

    /**
     * 连接关闭是触发
     * @param i
     * @param string
     * @param bln 
     */
    @Override
    public void onClose(int i, String string, boolean bln) {
       System.out.println("WebSocket Close. code:"+i+" reason:"+string+", bln:"+bln);
    }

    /**
     * 出错时触发
     * @param excptn 
     */
    @Override
    public void onError(Exception excptn) {
        System.out.println(excptn.toString());
    }

}
