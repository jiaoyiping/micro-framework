package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2017/6/29
  * Time: 16:00
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import org.junit.Test;

public class TestPathRegex3 {
    @Test
    public void testChineseLetter(){
        String chinesePath = "/publishEvent/张三";
        String englishPath = "/publishEvent/zhangsan";
        String regex = "/publishEvent/([\\s\\S]+?)";
        System.out.println(chinesePath.matches(regex));
        System.out.println(englishPath.matches(regex));

    }
}    