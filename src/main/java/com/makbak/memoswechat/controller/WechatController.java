package com.makbak.memoswechat.controller;

import cn.hutool.log.StaticLog;
import com.makbak.memoswechat.service.WechatMessageService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class WechatController {

    @Resource
    private WechatMessageService wechatMessageService;

    @GetMapping("/verifyToken")
    public String verifyToken(HttpServletRequest request) {

        String signature = request.getParameter("signature");
        try {
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            StaticLog.info("开始验证signature：{}", signature);
            if (wechatMessageService.verifyBaseToken(signature, timestamp, nonce)) {
                StaticLog.info("signature: {}, 验证成功", signature);
                return echostr;
            }
        } catch (Exception e) {
            StaticLog.error("signature: {}, 验证失败，失败信息：", signature, e.getMessage());
        }
        return null;
    }
}