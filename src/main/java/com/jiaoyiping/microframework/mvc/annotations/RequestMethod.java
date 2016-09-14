package com.jiaoyiping.microframework.mvc.annotations;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/4
  * Time: 22:19
  * To change this template use File | Settings | Editor | File and Code Templates
 */

public enum RequestMethod {
    GET,
    POST,
    PUT,
    DELETE;

    @Override
    public String toString() {
        return this.name();
    }

}
