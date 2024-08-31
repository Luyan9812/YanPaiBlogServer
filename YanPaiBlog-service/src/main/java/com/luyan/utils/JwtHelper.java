package com.luyan.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtHelper {
    private long expiration;
    private String signature;

    private JWTSigner getSigner() {
        return JWTSignerUtil.hs512(signature.getBytes(StandardCharsets.UTF_8));
    }

    /**
     *  根据用户 id 创建 token
     */
    public String createToken(Integer userId) {
        return JWT.create()
                .setPayload("userId", userId)
                // 设置过期时间，单位毫秒
                .setExpiresAt(new Date(System.currentTimeMillis() + expiration * 60 * 60 * 1000))
                .setSigner(getSigner())
                .sign();
    }

    /**
     *  解析 token 获取用户 id
     */
    public Integer getUserId(String token) {
        return Integer.parseInt(JWT.of(token).getPayload("userId").toString());
    }

    /**
     *  检验 token 是否失效
     */
    public boolean isExpiration(String token) {
        if (!StringUtils.hasText(token)) return true;
        // 下面的方法会同时检查签名与有效期，注意要取反
        return !JWT.of(token).setSigner(getSigner()).validate(0);
    }
}
