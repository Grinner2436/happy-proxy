package xyz.grinner.happyproxy.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.grinner.happyproxy.config.UserConfig;
import xyz.grinner.happyproxy.constant.API;
import xyz.grinner.happyproxy.tool.Extractor;
import xyz.grinner.happyproxy.tool.Extractors;

import java.io.IOException;
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

    public List<HttpHost> crossin(){
        List<HttpHost> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(API.CROSSIN_HTML).get();
            Elements trs = doc.select("tr:gt(0)");
            getResult(result,trs,Extractors.tdText(0),Extractors.tdInteger(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<HttpHost> freeip(){
        List<HttpHost> result = new ArrayList<>();
        try {
            for (int i = 1; ; i++) {
                String nextUrl = API.FREE_IP_HTML + i;
                Document doc = Jsoup.connect(nextUrl).get();
                Elements trs = doc.select("tbody tr");
                if(trs.size() < 15){
                    break;
                }
                getResult(result,trs,Extractors.tdText(0),Extractors.tdInteger(1));
                Thread.sleep(3000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<HttpHost> bajiu(){
        List<HttpHost> result = new ArrayList<>();
        try {
            for (int i = 1;i <= 10; i++) {
                String nextUrl = API.BAJIU_IP_HTML.replace("{page}",i+"");
                Document doc = Jsoup.connect(nextUrl).get();
                Elements trs = doc.select("tbody tr");
                if(trs.size() < 15){
                    break;
                }
                getResult(result,trs,Extractors.tdText(0),Extractors.tdInteger(1));
                Thread.sleep(3000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<HttpHost> xici(){
        List<HttpHost> result = new ArrayList<>();
        try {
            for (int i = 1;i <= 2; i++) {
                String nextUrl = API.XICIDAILI_HTML + i;
                Document doc = Jsoup.connect(nextUrl).get();
                Elements trs = doc.select("tr:gt(0)");
                getResult(result,trs,Extractors.tdText(0),Extractors.tdInteger(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<HttpHost> kuaidaili(){
        List<HttpHost> result = new ArrayList<>();
        try {
            String nextUrl = API.KUAIDAILI_HTML;
            Document doc = Jsoup.connect(nextUrl).get();
            Elements trs = doc.select("tr:gt(0)");
            getResult(result,trs,Extractors.tdText(0),Extractors.tdInteger(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<HttpHost> liuliu(){
        List<HttpHost> result = new ArrayList<>();
        try {
            String nextUrl = API.LIULIU_HTML;
            for (int i = 1; i <= 34; i++) {
                nextUrl = nextUrl.replace("{page}",i+"");
                Document doc = Jsoup.connect(nextUrl).get();
                Elements trs = doc.select("tbody tr:gt(0)");
                getResult(result,trs,Extractors.tdText(0),Extractors.tdInteger(1));
                Thread.sleep(3000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<HttpHost> yun(){
        List<HttpHost> result = new ArrayList<>();
        try {
            String nextUrl = API.YUN_HTML;
            for (int i = 1; i <= 10; i++) {
                nextUrl = nextUrl + i;
                Document doc = Jsoup.connect(nextUrl).get();
                Elements trs = doc.select("tbody tr");
                getResult(result,trs,Extractors.tdText(0),Extractors.tdInteger(1));
                Thread.sleep(3000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<HttpHost> proxyList(){
        List<HttpHost> result = new ArrayList<>();
        try {
            String nextUrl = API.PROXY_LIST_HTML;
            for (int i = 1; i <= 5; i++) {
                nextUrl = nextUrl + i;
                Document doc = Jsoup.connect(nextUrl).get();
                Elements trs = doc.select("table.bg tbody tr:gt(1)");
                getResult(result,trs,Extractors.tdText(1),Extractors.tdInteger(2));
                Thread.sleep(3000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<HttpHost> iphai(){
        List<HttpHost> result = new ArrayList<>();
        try {
            String nextUrl = API.IPHAI_HTML;
            Document doc = Jsoup.connect(nextUrl).get();
            Elements trs = doc.select("tbody tr:gt(0)");
            getResult(result,trs,Extractors.tdText(0),Extractors.tdInteger(1));

            String free = API.IPHAI_FREE_HTML;
            Document docFree = Jsoup.connect(free).get();
            Elements freeTrs = docFree.select("tbody tr:gt(0)");
            getResult(result,freeTrs,Extractors.tdText(0),Extractors.tdInteger(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean wash(HttpHost proxy){
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        HttpGet httpget = new HttpGet(API.IP_CHECK);
        try(CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
            CloseableHttpResponse response = httpClient.execute(httpget)) {
            HttpEntity entity = response.getEntity();
            String entityString = EntityUtils.toString(entity).trim();
            if(entityString.equals(proxy.getHostName())){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
        return false;
    }

    private void getResult(List<HttpHost> result, Elements elements,
                           Extractor<String> hostExtractor,Extractor<Integer> portExtractor){//int hostIndex,int portIndex
        for(Element element : elements){
            try{
                String host = hostExtractor.extract(element);
                int port = portExtractor.extract(element);
                HttpHost httpHost = new HttpHost(host,port);
                result.add(httpHost);
            }catch (Exception e){

            }
        }
    }

}
