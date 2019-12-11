package xyz.grinner.happyproxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import xyz.grinner.happyproxy.service.DispatcherService;

import java.util.List;

@RestController
@RequestMapping("/proxy")
public class ProxyController {
    @Autowired
    private DispatcherService dispatcherService;

    @ResponseBody
    @GetMapping("/")
    public String getOneProxyOf(@Param("site") String site){
        return dispatcherService.getOneIp(site);
    }

    @ResponseBody
    @GetMapping("/list")
    public List<String> listAllProxy(){
        return dispatcherService.getList(null);
    }

    @ResponseBody
    @GetMapping("/list/{site}")
    public List<String> listAllProxyOf(@PathVariable("site") String site){
        return dispatcherService.getList(site);
    }
}
