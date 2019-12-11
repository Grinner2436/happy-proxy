package xyz.grinner.happyproxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.grinner.happyproxy.pool.PearlString;

import java.util.List;

@Service
public class DispatcherService {

    @Autowired
    private PearlString<String,String> pool;

    public String getOneIp(String site){
        String proxy = pool.getHead(site);
        if(proxy == null){
            proxy = "";
        }
        return proxy;
    }

    public List<String> getList(String site){
        if(site == null ){
            return pool.getList();
        }else{
            return pool.getListOf(site);
        }
    }
}
