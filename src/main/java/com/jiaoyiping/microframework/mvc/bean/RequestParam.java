package com.jiaoyiping.microframework.mvc.bean;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/13
  * Time: 6:47
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.io.Serializable;

public class RequestParam implements Serializable {

    private static final long serialVersionUID = 1940204838705878369L;
    private String paramName;
    private Object paramValue;

    public RequestParam(String paramName, Object paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Object getParamValue() {
        return paramValue;
    }

    public void setParamValue(Object paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestParam)) return false;

        RequestParam that = (RequestParam) o;

        if (!getParamName().equals(that.getParamName())) return false;
        return getParamValue() != null ? getParamValue().equals(that.getParamValue()) : that.getParamValue() == null;

    }

    @Override
    public int hashCode() {
        int result = getParamName().hashCode();
        result = 31 * result + (getParamValue() != null ? getParamValue().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "paramName='" + paramName + '\'' +
                ", paramValue=" + paramValue +
                '}';
    }
}