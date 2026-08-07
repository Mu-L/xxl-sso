package com.xxl.sso.sample.controller;

import com.xxl.sso.core.helper.XxlSsoReactiveHelper;
import com.xxl.sso.core.model.LoginInfo;
import com.xxl.tool.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author xuxueli 2026-08-07
 */
@Controller
public class IndexController {


    @Value("${xxl.sso.server.address}")
    private String serverAddress;
    @Value("${xxl.sso.server.logout.path}")
    private String logoutPath;

    /**
     * cas-server logout path
     */
    private String getLogoutPath(){
        return serverAddress + logoutPath;
    }

    @RequestMapping("/")
    public String index(Model model, ServerWebExchange exchange) {

        Response<LoginInfo> result = XxlSsoReactiveHelper.loginCheckWithAttr(exchange);
        model.addAttribute("loginInfo", result!=null?result.getData():null);
        model.addAttribute("logoutPath", getLogoutPath());
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Response<LoginInfo> json(ServerWebExchange exchange) {
        return XxlSsoReactiveHelper.loginCheckWithAttr(exchange);
    }

}
