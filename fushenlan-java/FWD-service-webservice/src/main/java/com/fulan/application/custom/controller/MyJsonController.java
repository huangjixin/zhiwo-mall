package com.fulan.application.custom.controller;

import com.fulan.application.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perform")
public class MyJsonController {


    @Autowired
    private TextService textService;

    @PostMapping("/getPerform")
    public String getPerform(@RequestParam(value = "agentCode") String agentCode, @RequestParam(value = "month") String month, @RequestParam(value = "type") String type){
        return textService.getPerform(agentCode,month,type);
    }

}
