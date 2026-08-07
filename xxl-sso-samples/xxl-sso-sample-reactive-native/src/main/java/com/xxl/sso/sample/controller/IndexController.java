package com.xxl.sso.sample.controller;

import com.xxl.sso.core.helper.XxlSsoReactiveHelper;
import com.xxl.sso.core.model.LoginInfo;
import com.xxl.tool.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author xuxueli 2026-08-07
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public Response<LoginInfo> index(ServerWebExchange exchange) {
        return XxlSsoReactiveHelper.loginCheckWithAttr(exchange);
    }

}
