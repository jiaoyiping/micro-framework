package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/13
  * Time: 7:22
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对Controller方法执行完毕之后返回的结果进行解析
 */
public interface ViewResolver {
    void resolveView(HttpServletRequest request, HttpServletResponse response,Object actionResult);
}    
