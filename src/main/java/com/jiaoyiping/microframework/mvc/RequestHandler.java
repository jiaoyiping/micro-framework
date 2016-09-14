package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/11
  * Time: 16:35
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.lang.reflect.Method;
import java.util.regex.Matcher;

public class RequestHandler {
    private Class<?> actionClass;
    private Method actionMethod;
    private Matcher requestPathMatcher;

    public RequestHandler(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }


    public Class<?> getActionClass() {
        return actionClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public Matcher getRequestPathMatcher() {
        return requestPathMatcher;
    }

    public void setRequestPathMatcher(Matcher requestPathMatcher) {
        this.requestPathMatcher = requestPathMatcher;
    }
}