package com.junfeng.platform.payment.bank.constructionbank.untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by lixinshuo on 2018/10/27.
 */
public class SocketUtil {
    private final static Logger logBase = LoggerFactory.getLogger(SocketUtil.class);

    protected static Socket server;

    private boolean isConnected=true;
    public SocketUtil(int port){//链接自己，本机上测试的时候用
        try {
            server = new Socket(InetAddress.getLocalHost(), port);
        } catch (Exception e) {
            isConnected=false;
            logBase.error("初始化socket出现异常:{}",e);
        }
    }
    public SocketUtil(String url, int port){
        try {
            server = new Socket(url,port);
        } catch (Exception e) {
            isConnected=false;
            logBase.error("初始化socket出现异常:{}",e);
        }
    }
    //		 向服务端程序发送数据
    public void send(String data){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(server.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            logBase.info("开始向服务端发送数据:{}",data);
            bw.write(data+"\r\n");
            bw.flush();
//			DataOutputStream dos=new DataOutputStream(server.getOutputStream());
//			dos.write(data.getBytes());
//			dos.flush();
        } catch (Exception e) {
            logBase.error("发送socket出现异常:{}",e);
        }
    }
    /**
     * 从服务端程序接收数据,返回一个BufferedReader
     * @return
     */
    public String recieve(){
        InputStreamReader isr=null;
        BufferedReader br=null;
        String result = "";
        try {
            isr = new InputStreamReader(server.getInputStream(),"GBK");
            br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                result += line;
                line = br.readLine();
            }
        } catch (Exception e) {
            logBase.error("socket接收服务器返回出现异常:{}",e);
        }
        logBase.info("socket接收到服务器返回:{}",result);
        return result;
    }

    public void close(){
        try {
            if(server!=null||server.isConnected()){
                server.close();
            }
        } catch (IOException e) {
        }
    }
    public boolean isConnected() {
        return isConnected;
    }
    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public static void main(String[] args) {
        SocketUtil util=new SocketUtil("39.107.153.66",12345);
        util.send("<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\" ?><TX><USER_ID>105000065134260-123</USER_ID><PASSWORD>111111</PASSWORD><REQUEST_SN>123123123</REQUEST_SN><CUST_ID>105000065134260</CUST_ID><TX_CODE>5W1003</TX_CODE><LANGUAGE>CN</LANGUAGE><TX_INFO><KIND>0</KIND><ORDER>123123123</ORDER><NORDERBY>2</NORDERBY><PAGE>1</PAGE>><STATUS>3</STATUS></TX_INFO></TX> ");
        String result = util.recieve();
        System.out.println("请求结果:"+result);
        util.close();
    }

}
