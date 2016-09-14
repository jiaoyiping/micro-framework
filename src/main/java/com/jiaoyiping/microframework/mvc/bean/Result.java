package com.jiaoyiping.microframework.mvc.bean;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/13
  * Time: 8:08
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = -6488353106658834323L;
    private boolean success;
    private int code;
    private Object data;

    public Result(boolean success, int code, Object data) {
        this.success = success;
        this.code = code;
        this.data = data;
    }

    public Result() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result result = (Result) o;

        if (isSuccess() != result.isSuccess()) return false;
        if (getCode() != result.getCode()) return false;
        return getData() != null ? getData().equals(result.getData()) : result.getData() == null;

    }

    @Override
    public int hashCode() {
        int result = (isSuccess() ? 1 : 0);
        result = 31 * result + getCode();
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }

}
