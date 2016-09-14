package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/12
  * Time: 15:30
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerInvoker {
    void invoke(HttpServletRequest request, HttpServletResponse response, RequestHandler handler);
}    