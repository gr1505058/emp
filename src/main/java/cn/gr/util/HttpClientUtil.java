package cn.gr.util;

import org.apache.http.*;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rong.gao on 2018/4/9.
 *
 * BasicClientCookie cookie = new BasicClientCookie("name", "zhangshan");
 cookie.setVersion(0);
 cookie.setDomain("/pms/");   //设置范围
 cookie.setPath("/");

 new BasicHeader("Cookie", cookies)
 */
@Component
public class HttpClientUtil {
    @Value("${socket.timeout}")
    private static int socketTimeout;
    @Value("${connect.timeout}")
    private static int connectTimeout;
    @Value("${nm.url.home}")
    private String homeUrl;

    public static String doPost(String url, Map<String,String> paramMap) throws Exception{
        String response = "";
        CloseableHttpClient httpClient = HttpClients.custom().build();
        List<NameValuePair> nameValuePairArrayList = new ArrayList<NameValuePair>();
        for(String k : paramMap.keySet()){
            nameValuePairArrayList.add(new BasicNameValuePair(k,paramMap.get(k)));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairArrayList, "utf-8");
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity!=null) {
            response = EntityUtils.toString(httpEntity, "UTF-8");
        }
        EntityUtils.consume(httpEntity);
        return response;
    }

    public static String doGet(String url) throws Exception{
        String response = "";
        CloseableHttpClient httpClient = HttpClients.custom().build();
        StringBuilder urlStringBuilder=new StringBuilder(url);
        HttpGet httpGet=new HttpGet(urlStringBuilder.toString());
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity=httpResponse.getEntity();
        if (httpEntity!=null) {
            response = EntityUtils.toString(httpEntity, "UTF-8");
        }
        EntityUtils.consume(httpEntity);
        return response;
    }

    public static String doGetFromHeader(String url,String key) throws Exception{
        String response = "";
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(){
            public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) {
                for(Header header : response.getAllHeaders()){
                    if(header.getName().equals("Location")){
                        if(header.getValue().contains("www.yohobuy.com/product")){
                            return false;
                        }
                    }
                }
                return true;
            }
        };
        CloseableHttpClient httpClient = HttpClients.custom().setRedirectStrategy(redirectStrategy).build();
        StringBuilder urlStringBuilder=new StringBuilder(url);
        HttpGet httpGet=new HttpGet(urlStringBuilder.toString());
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        Header[] headers = httpResponse.getAllHeaders();
        for(Header header : headers){
            if(header.getName().equals(key)){
                response = header.getValue();
            }
        }
        return response;
    }

    public void getAllCatalogAndBrandAndSize() throws Exception{
        String res = doGet(homeUrl);

    }

}
