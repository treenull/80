package com.wyj.cloudopen.controller;

import com.wyj.cloudopen.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @Author WYJ
 * @Data 2021/10/29 9:24
 * @Description:
 */
@Controller
public class ApplicationTest {


    /**
     * Java模拟postman接口测试  下载合同文档
     * @throws IOException
     */
    @GetMapping("/getFile")
    public String postmanGetFileTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ) throws IOException, NoSuchAlgorithmException {

        //从URL加载HTML
        String host = "http://127.0.0.1:9182";
        //表头时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        //表头登录AppToken
        String token = "qDiEwGfFcI";
        //表头登录AppSecret
        String secret = "CtY9B9Bc5KYdGzHHxFgQ0lA1HvMmyJ";
        /* MD5加密 */
        //表头登录签名signature
        String signature = md5(token.trim()+secret.trim()+timestamp.trim());


        //请求头参数设置
        HashMap<String,String> header = new HashMap<>();

        header.put("Cache-Control","no-cache");
        header.put("Host", "127.0.0.1:9182");
        header.put("User-Agent", "PostmanRuntime/7.28.3");
        header.put("Accept", "*/*");
        header.put("Accept-Encoding","gzip, deflate, br");
        header.put("Connection","keep-alive");
        header.put("Content-Type","application/zip");
        header.put("Postman-Token","07133faf-5ffc-4752-a68f-4532bbdb8bf8");

        header.put("x-qys-timestamp",timestamp);
        header.put("x-qys-accesstoken",token);
        header.put("x-qys-signature",signature);

        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(host+"/document/download?documentId=2888983431425429508");

        // 创建http GET请求 添加表头header参数
        for (String key : header.keySet()) {
            httpGet.addHeader(key, header.get(key));
        }

        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();

        //获得浏览器代理信息
        final String userAgent = httpServletRequest.getHeader("USER-AGENT");
        //获取文件名
        String fileName = HttpClientUtil.getFileName(response);
        String finalFileName = null;
        if(StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent,"Trident")){//IE浏览器
            finalFileName = URLEncoder.encode(fileName,"UTF-8");
            System.out.println("IE浏览器");
        }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
            finalFileName = new String(fileName.getBytes(), "ISO8859-1");
        }else{
            finalFileName = URLEncoder.encode(fileName,"UTF-8");//其他浏览器
        }

        httpServletResponse.setContentType("application/x-download");//告知浏览器下载文件，而不是直接打开，浏览器默认为打开
        httpServletResponse.addHeader("Content-Disposition" ,"attachment;filename=\"" +finalFileName+ "\"");//下载文件的名称

        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len;
        while ((len = inputStream.read(b)) > 0){
            httpServletResponse.getOutputStream().write(b, 0, len);
        }
        inputStream.close();
        httpServletResponse.getOutputStream().close();
        return "";

    }

    public static String md5(String text) {
        String result="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
//       System.out.println("result: " + buf.toString());// 32位的加密
//       System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

}
