package com.xxl.sso.sample.controller;

import com.xxl.sso.core.annotation.XxlSso;
import com.xxl.sso.core.helper.XxlSsoReactiveHelper;
import com.xxl.sso.core.model.LoginInfo;
import com.xxl.tool.response.Response;
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

    @RequestMapping("/")
    public String index(Model model, ServerWebExchange exchange) {

        // login check
        Response<LoginInfo> loginCheckResult = XxlSsoReactiveHelper.loginCheckWithCookie(exchange);

        if (loginCheckResult!=null && loginCheckResult.isSuccess()) {
            model.addAttribute("loginInfo", loginCheckResult.getData());
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * 示例：API方式获取登录用户信息（LoginInfo）；API方式校验角色、权限；
     *
     * @return
     */
    @RequestMapping("/test41")
    @ResponseBody
    @XxlSso
    public Response<String> test41(ServerWebExchange exchange) {

        Response<LoginInfo> loginCheckResult = XxlSsoReactiveHelper.loginCheckWithAttr(exchange);
        Response<String> hasRole01 = XxlSsoReactiveHelper.hasRole(loginCheckResult.getData(), "role01");
        Response<String> hasRole02 = XxlSsoReactiveHelper.hasRole(loginCheckResult.getData(), "role02");
        Response<String> hasPermission01 = XxlSsoReactiveHelper.hasPermission(loginCheckResult.getData(), "user:query");
        Response<String> hasPermission02 = XxlSsoReactiveHelper.hasPermission(loginCheckResult.getData(), "user:delete");

        String data = "LoginInfo:" + loginCheckResult.getData() +
                ", hasRole01:" + hasRole01.isSuccess() +
                ", hasRole02:" + hasRole02.isSuccess() +
                ", hasPermission01:" + hasPermission01.isSuccess() +
                ", hasPermission02:" + hasPermission02.isSuccess();

        return Response.ofSuccess(data);
    }

}
