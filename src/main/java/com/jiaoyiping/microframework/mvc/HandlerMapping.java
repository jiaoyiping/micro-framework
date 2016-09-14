package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/14
  * Time: 17:17
  * To change this template use File | Settings | Editor | File and Code Templates
 */

public interface HandlerMapping {
    RequestHandler getHandler(String requestMethod, String requestPath);
}    
