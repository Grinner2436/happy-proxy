package xyz.grinner.happyproxy.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpUtils {

    @Autowired
    RequestConfig requestConfig;

    private BasicCookieStore cookieStore = new BasicCookieStore();

    public JSONObject get(String url){
        JSONObject jsonResult = null;
        HttpGet httpget = new HttpGet(url);
        try(CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
                                            .setDefaultCookieStore(cookieStore).build();
            CloseableHttpResponse response = httpClient.execute(httpget)) {
            HttpEntity entity = response.getEntity();
            String entityString = EntityUtils.toString(entity);
            jsonResult = JSONObject.parseObject(entityString);
        } catch (Exception e) {

        }
        return jsonResult;
    }
}
