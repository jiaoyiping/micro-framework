package com.jiaoyiping.microframework.mvc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2017/1/3
  * Time: 23:05
  * To change this template use File | Settings | Editor | File and Code Templates
 */
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryParam {
    String paramName();

    ParamType paramType();
}
