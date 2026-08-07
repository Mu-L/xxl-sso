package com.xxl.sso.sample.weblogin.controller;

import com.xxl.sso.core.constant.Const;
import com.xxl.sso.core.helper.XxlSsoReactiveHelper;
import com.xxl.sso.core.model.LoginInfo;
import com.xxl.sso.sample.weblogin.model.AccountInfo;
import com.xxl.sso.sample.weblogin.service.AccountService;
import com.xxl.tool.id.UUIDTool;
import com.xxl.tool.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * login page (for webflux / web)
 *
 * @author xuxueli 2026-08-07
 */
@Controller
@RequestMapping("/weblogin")
public class WebLoginController {

    @Autowired
    private AccountService accountService;

    /**
     * Login page
     *
     * @param exchange
     * @return
     */
    @RequestMapping(Const.LOGIN_URL)
    public String login(ServerWebExchange exchange) {

        // login check
        Response<LoginInfo> loginCheckResult = XxlSsoReactiveHelper.loginCheckWithCookie(exchange);
        if (loginCheckResult.isSuccess()) {
            return "redirect:/";
        }

        return "login";
    }

    /**
     * Do login
     *
     * WebFlux 中 "application/x-www-form-urlencoded" 表单参数需通过 ServerWebExchange#getFormData() 读取；
     *
     * @param exchange
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Mono<Response<String>> doLogin(ServerWebExchange exchange) {

        return exchange.getFormData().map(formData -> {

            // process param
            String username = formData.getFirst("username");
            String password = formData.getFirst("password");
            String ifRemember = formData.getFirst("ifRemember");
            boolean ifRem = "on".equals(ifRemember);

            // 1、find user
            Response<AccountInfo> accountResult = accountService.findUser(username, password);
            if (!accountResult.isSuccess()) {
                return Response.ofFail(accountResult.getMsg());
            }
            AccountInfo accoutInfo = accountResult.getData();

            // 2、build LoginInfo
            LoginInfo loginInfo = new LoginInfo(
                    accoutInfo.getUserid(),
                    accoutInfo.getUsername(),
                    null,
                    null,
                    accoutInfo.getRoleList(),
                    accoutInfo.getPermissionList(),
                    -1,
                    UUIDTool.getSimpleUUID());

            // 4、login (write store + cookie)
            Response<String> loginResult = XxlSsoReactiveHelper.loginWithCookie(loginInfo, exchange, ifRem);
            if (!loginResult.isSuccess()) {
                return Response.ofFail(accountResult.getMsg());
            }

            // 5、redirect back
            return Response.ofSuccess();
        });
    }

    /**
     * Logout
     *
     * @param exchange
     * @return
     */
    @RequestMapping(Const.LOGOUT_URL)
    @ResponseBody
    public Response<String> logout(ServerWebExchange exchange) {
        return XxlSsoReactiveHelper.logoutWithCookie(exchange);
    }

}
