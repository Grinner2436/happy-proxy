package xyz.grinner.happyproxy.manager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.grinner.happyproxy.config.UserConfig;
import xyz.grinner.happyproxy.constant.API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Fisher {

    @Autowired
    private UserConfig userConfig;

    public List<String> lihe(){
        List<String> result = new ArrayList<>();
        HttpGet httpget = new HttpGet(API.LIHE_API+userConfig.getLiheToken());
        try(CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpget)) {
            HttpEntity entity = response.getEntity();
            String entityString = EntityUtils.toString(entity);
            if(!entityString.contains("error")){
                String[] ips = entityString.split("\r\n");
                result = Arrays.asList(ips);
            }
        } catch (Exception e) {

        }
        return result;
    }
}
