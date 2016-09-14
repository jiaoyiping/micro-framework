package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/11
  * Time: 16:50
  * To change this template use File | Settings | Editor | File and Code Templates
 */

public class RequestInfo {
    private String requestMethod;
    private String requestPath;

    public RequestInfo(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestInfo)) return false;

        RequestInfo that = (RequestInfo) o;

        if (!getRequestMethod().equals(that.getRequestMethod())) return false;
        return getRequestPath().equals(that.getRequestPath());

    }

    @Override
    public int hashCode() {
        int result = getRequestMethod().hashCode();
        result = 31 * result + getRequestPath().hashCode();
        return result;
    }
}