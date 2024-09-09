package com.luyan.utils;

import com.luyan.entity.exception.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImagePathUtil {
    private static final Pattern imgPattern = Pattern.compile("!\\[]\\((.*?)\\)");
    private static final Pattern imgPattern2 = Pattern.compile("(<img.*?src=\")(.*?)(\".*?>)");
    private static final Pattern urlPattern = Pattern.compile("^https?://[^/]+(/.*)$");

    // 去除域名部分仅保留请求路径
    private static String getRequestPath(String url) {
        Matcher matcher = urlPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new ServiceException(String.format("图片路径{%s}不合法", url));
    }

    // 获取文章内容中上传的图片路径，去除里面的域名部分
    public static String removeDomain(String content) {
        StringBuilder builder = new StringBuilder();
        Matcher matcher = imgPattern.matcher(content);
        while (matcher.find()) {
            String s = String.format("![](%s)", getRequestPath(matcher.group(1)));
            matcher.appendReplacement(builder, s);
        }
        matcher.appendTail(builder);

        content = builder.toString();
        builder = new StringBuilder();
        matcher = imgPattern2.matcher(content);
        while (matcher.find()) {
            String s = String.format("%s%s%s", matcher.group(1), getRequestPath(matcher.group(2)), matcher.group(3));
            matcher.appendReplacement(builder, s);
        }
        matcher.appendTail(builder);
        return builder.toString();
    }
}
