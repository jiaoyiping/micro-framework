package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/14
  * Time: 17:18
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.util.Iterator;
import java.util.Map;

public class DefaultHandlerMapping implements HandlerMapping {
    private Map<RequestInfo, RequestHandler> handlerMap = ActionUtils.getRequestMap();

    @Override
    public RequestHandler getHandler(String requestMethod, String requestPath) {
        RequestHandler requestHandler = handlerMap.get(new RequestInfo(requestMethod, requestPath));
        if (requestHandler == null) {
            Iterator<RequestInfo> requestInfoIterator = handlerMap.keySet().iterator();
            while (requestInfoIterator.hasNext()) {
                RequestInfo requestInfo = requestInfoIterator.next();
                if (requestPath.matches(requestInfo.getRequestPath())) {
                    requestHandler = handlerMap.get(requestInfo);
                    break;
                }
            }
        }
        return requestHandler;
    }
}