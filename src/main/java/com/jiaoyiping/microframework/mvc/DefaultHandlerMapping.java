package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/14
  * Time: 17:18
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.util.Map;

public class DefaultHandlerMapping implements HandlerMapping {
    private Map<RequestInfo, RequestHandler> handlerMap = ActionUtils.getRequestMap();

    @Override
    public RequestHandler getHandler(String requestMethod, String requestPath) {
        return handlerMap.get(new RequestInfo(requestMethod, requestPath));
    }
}