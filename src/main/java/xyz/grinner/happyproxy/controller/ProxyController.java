package xyz.grinner.happyproxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.grinner.happyproxy.service.DispatcherService;

import java.util.List;

@RestController
@RequestMapping("/proxy")
public class ProxyController {
    @Autowired
    private DispatcherService dispatcherService;

    @ResponseBody
    @GetMapping("/{site}")
    public String getOneProxyOf(@PathVariable("site") String site){
        return dispatcherService.getOneIp(site);
    }

    @ResponseBody
    @GetMapping("/list/{site}")
    public List<String> listAllProxyOf(@PathVariable("site") String site){
        return dispatcherService.getList(site);
    }
}
