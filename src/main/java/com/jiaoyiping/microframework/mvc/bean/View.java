package com.jiaoyiping.microframework.mvc.bean;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/13
  * Time: 7:36
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class View implements Serializable {

    private static final long serialVersionUID = 5520578606815538650L;
    private String path;
    private Map<String, Object> data;

    public View(String path) {
        this.path = path;
        this.data = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof View)) return false;

        View view = (View) o;

        if (path != null ? !path.equals(view.path) : view.path != null) return false;
        return data != null ? data.equals(view.data) : view.data == null;

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "View{" +
                "path='" + path + '\'' +
                ", data=" + data +
                '}';
    }
}