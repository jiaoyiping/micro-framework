package com.jiaoyiping.microframework.mvc.annotations;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/29
  * Time: 23:22
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于将请URL中的值映射为参数值
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestParam {
    String name();

    ParamType paramType();
}
