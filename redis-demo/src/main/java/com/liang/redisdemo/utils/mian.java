package com.liang.redisdemo.utils;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class mian {
    public static void main(String[] args) throws Exception {

       Connection conn=getTestConnection();
       System.out.println(conn);


//        JSONObject jsonObject = new JSONObject();
//        System.out.println(Integer.valueOf(null));

/*
        Request request = new Request();
        try {
            request.setKey("b344e411-3ba1-4522-b7a5-b39468962816"); //创建集成应用时得到
            request.setSecret("ec5cf1f6c5637d0c68cab8d73b63daa8b6981c5348a4a2afe264033d4b395bc2"); //创建集成应用时得到
            request.setMethod("POST");
            request.setUrl("http://dwi-faculty.std-data-api.seu.edu.cn/facultyfield_faculty_basic_info");
            //url地址在创建API时得到
            //子域名在创建API分组时得到
            //request.addQueryStringParam("name", "value");
            request.addHeader("Content-Type", "text/plain");
            //request.addHeader("x-stage", "publish_env_name"); //如果API发布到非RELEASE环境，需要增加自定义的环境名称
            request.setBody("demo");
            getData(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    private static Connection getTestConnection() throws Exception {
        String username = "USR_NEWRYZX";
        String password = "sudy@123";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@172.20.8.70:1521/ehall";
        //String url = "jdbc:oracle:thin:@(description=(address=(protocol=tcp)(port=1521)(host=172.20.8.70)(PORT = 1521))(connect_data=(SERVER = DEDICATED)(service_name=ehall)))";
        //String url="jdbc:oracle:thin:@(description=(ADDRESS_LIST=(ADDRESS = (PROTOCOL = TCP)(HOST = 172.20.8.70)(PORT = 1521)))(FAILOVER=yes)(LOAD_BALANCE=yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = ehall)))";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }


//    private static void getData(Request request) {
//        CloseableHttpClient client = null;
//        try {
//            HttpRequestBase signedRequest = Client.sign(request);
//
//            client = HttpClients.custom().build();
//            HttpResponse response = client.execute(signedRequest);
//            System.out.println(response.getStatusLine().toString());
//            Header[] resHeaders = response.getAllHeaders();
//            for (Header h : resHeaders) {
//                System.out.println(h.getName() + ":" + h.getValue());
//            }
//            HttpEntity resEntity = response.getEntity();
//            if (resEntity != null) {
//                System.out.println(System.getProperty("line.separator") + EntityUtils.toString(resEntity, "UTF-8"));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (client != null) {
//                    client.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
