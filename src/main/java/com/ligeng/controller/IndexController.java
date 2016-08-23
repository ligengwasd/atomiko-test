package com.ligeng.controller;

import com.ligeng.service.ITraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dev on 16-4-11.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
//    @Autowired
//    private ITraService traService;

    @RequestMapping(value = "/demo")
    @ResponseBody
    public String index(@RequestParam() int id){
//        traService.test(id);
        return "index";
    }





}
