package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/12/29
  * Time: 10:04
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式路径匹配测试
 */
public class TestPathRegex {
    @Test
    public void testPathWithoutParams() {
        String path = "/user/list";
        String target = "/user/list";
        Pattern pattern = Pattern.compile(path);
        Matcher matcher = pattern.matcher(target);
        System.out.println(matcher.matches());
    }

    @Test
    public void testPathWithParams() {
        String path = "/user/{id}";
        String target = "/user/zhangsan";

        if (path.matches(".+\\{\\w+\\}.*")) {
            System.out.println("路径中包含占位符,替换占位符为表达式");
            String targetRegex = path.replaceAll("\\{\\w+\\}", "(\\\\w+)");
            Pattern pattern = Pattern.compile(targetRegex);
            Matcher matcher = pattern.matcher(target);
            if (matcher.matches()){
                String result = matcher.group(1);
                Assert.assertEquals("zhangsan",result);
            }

        }


    }


}    