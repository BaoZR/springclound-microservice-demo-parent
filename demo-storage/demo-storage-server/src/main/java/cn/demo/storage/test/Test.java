package cn.demo.storage.test;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import vip.zywork.wechat.pay.util.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/22 21:10
 */
public class Test {




    public static void main(String[] args) throws IOException {
        //AppKey：203808720     AppSecret：5cygcdna4wo0za2b0cbxwgaeal73ieac
//        String appcode = "8925cb35528740b6a275b1bf52d5f75d";
//        String sendContent = "【红龙】您的验证码是456。";
//        HttpResponse httpResponse = sendSms(appcode, "18710495679", sendContent);
//        String s = EntityUtils.toString(httpResponse.getEntity());
//        System.out.println(s);
    }

    /**
     * 添加模板
     * @param appCode
     * @param content 模板内容,变量用#code#代替
     * @param signature 签名头
     * @return
     */
    public static HttpResponse addTemplate(String appCode,String content,String signature){
        String host = "https://zwp.market.alicloudapi.com";
        String path = "/sms/edittemplete";
        String method = "POST";
        Map headers = createHeaders(appCode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap();
//        querys.put("content", "您的验证码是#code#。");
        querys.put("content", content);
        querys.put("signature", signature);
        String bodys = "";
        HttpResponse response = null;
        try {
            response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    /**
     * 模板列表查询
     * @return
     */
    public static HttpResponse templateList(String appCode){
        String host = "https://zwp.market.alicloudapi.com";
        String path = "/sms/tmplist";
        String method = "GET";
        Map headers = createHeaders(appCode);
        Map<String, String> querys = new HashMap();
        HttpResponse response = null;
        try {
            response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 发送短信
     * @param mobile
     * @param content
     * @return
     */
    public static HttpResponse sendSms(String appCode,String mobile,String content){
        String host = "https://zwp.market.alicloudapi.com";
        String path = "/sms/sendv2";
        String method = "GET";
        Map headers = createHeaders(appCode);
        Map<String, String> querys = new HashMap();
        querys.put("content", content);
        querys.put("mobile", mobile);
        HttpResponse response = null;
        try {
            response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    public static HttpResponse getTemplateStatus(String appCode,String templateId){
        String host = "https://zwp.market.alicloudapi.com";
        String path = "/sms/gettempletestatus";
        String method = "GET";
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        Map headers = createHeaders(appCode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("templateId", templateId);
        HttpResponse response = null;
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    /**
     * 构建头部信息
     * @param appCode
     * @return
     */
    private static Map createHeaders(String appCode){
        Map<String, String> headers = new HashMap();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        return headers;
    }
    public static String httpResponse2Json(HttpResponse response) throws IOException {
        if (Objects.isNull(response)){
            return "";
        }
        return EntityUtils.toString(response.getEntity());
    }
}
