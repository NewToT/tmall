package com.wanczy.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {
	@RequestMapping("registPage")
    public String registPage() {
        return "fore/regist";
    }
    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage() {
        return "fore/registerSuccess";
    }
    @RequestMapping("loginPage")
    public String loginPage() {
        return "fore/login";
    }
    @RequestMapping("forealipay")
    public String alipay(){
        return "fore/alipay";
    }
}
