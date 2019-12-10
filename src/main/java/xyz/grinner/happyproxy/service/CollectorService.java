package xyz.grinner.happyproxy.service;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xyz.grinner.happyproxy.manager.Fisher;
import xyz.grinner.happyproxy.pool.PearlString;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class CollectorService {

    @Autowired
    private Fisher fisher;

    @Autowired
    private PearlString<String,String> pool;

    private ConcurrentLinkedQueue<HttpHost> proxiesTobeCheck = new ConcurrentLinkedQueue<>();

    @Scheduled(cron = "0 0/5 * * * ?")
    public void checkAndSave(){
        while (!proxiesTobeCheck.isEmpty()){
            HttpHost httpHost = proxiesTobeCheck.poll();
            String hostString = httpHost.toHostString();
            if(httpHost != null && !pool.hasPearl(hostString) && fisher.wash(httpHost)){
                pool.stringNewPearl(hostString);
            }
        }
    }

    @Scheduled(cron = "0 * * * * ?")
    public void lihe(){
        List<String> data  = fisher.lihe();
        data.forEach(ip -> {
            if(ip.contains(":")){
                String[] params = ip.split(":");
                proxiesTobeCheck.offer(new HttpHost(params[0],Integer.parseInt(params[1])));
            }else{
                proxiesTobeCheck.offer(new HttpHost(ip,80));
            }
        });
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void freeip(){
        List<HttpHost> data  = fisher.freeip();
        proxiesTobeCheck.addAll(data);
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void bajiu(){
        List<HttpHost> data  = fisher.bajiu();
        proxiesTobeCheck.addAll(data);
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void xici(){
        List<HttpHost> data  = fisher.xici();
        proxiesTobeCheck.addAll(data);
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void kuaidaili(){
        List<HttpHost> data  = fisher.kuaidaili();
        proxiesTobeCheck.addAll(data);
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void liuliu(){
        List<HttpHost> data  = fisher.liuliu();
        proxiesTobeCheck.addAll(data);
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void crossin(){
        List<HttpHost> data  = fisher.crossin();
        proxiesTobeCheck.addAll(data);
    }

}
