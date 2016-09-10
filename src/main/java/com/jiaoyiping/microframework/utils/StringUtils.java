package com.jiaoyiping.microframework.utils;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/10
  * Time: 23:16
  * To change this template use File | Settings | Editor | File and Code Templates
 */

public class StringUtils {
    public static boolean isEmpty(String input) {
        return input == null || "".equals(input);
    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }
}    