package com.makbak.memoswechat.service;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.util.Arrays;

@Service
public class WechatMessageService {

    @Value("${wechat.verifyToken}")
    private String verifyToken;

    public boolean verifyBaseToken(String signature, String timestamp, String nonce) {
        if (StringUtils.isEmpty(signature)) {
            return false;
        }
        String[] params = new String[]{verifyToken, timestamp, nonce};
        Arrays.sort(params);
        String concat = params[0].concat(params[1]).concat(params[2]);

        byte[] bytes = DigestUtil.sha1(concat);
        String hashResult = HexUtil.encodeHexStr(bytes);

        return StringUtils.equals(hashResult, signature);
    }

    public void getAccessToken() {
        Document document = XmlUtil.readXML("");

    }
}