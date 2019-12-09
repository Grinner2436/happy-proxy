package xyz.grinner.happyproxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xyz.grinner.happyproxy.manager.Fisher;
import xyz.grinner.happyproxy.pool.PearlString;

import java.util.List;

@Service
public class CollectorService {

    @Autowired
    private Fisher fisher;

    @Autowired
    private PearlString<String,String> pool;

    @Scheduled(cron = "0 * * * * ?")
    public void lihe(){
        List<String> data  = fisher.lihe();
        data.forEach(ip -> {
            if(!pool.hasPearl(ip)){
                pool.stringNewPearl(ip);
            }
        });
    }
}
