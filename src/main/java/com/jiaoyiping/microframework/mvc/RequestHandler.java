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
import java.util.regex.Pattern;

public class RequestHandler {
    private Class<?> actionClass;
    private Method actionMethod;
    private Pattern requestPathPattern;

    public RequestHandler(Class<?> actionClass, Method actionMethod) {
        this(actionClass, actionMethod, null);
    }

    public RequestHandler(Class<?> actionClass, Method actionMethod, Pattern requestPathPattern) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
        this.requestPathPattern = requestPathPattern;
    }


    public Class<?> getActionClass() {
        return actionClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public Pattern getRequestPathPattern() {
        return requestPathPattern;
    }

    public void setRequestPathPattern(Pattern requestPathPattern) {
        this.requestPathPattern = requestPathPattern;
    }
}