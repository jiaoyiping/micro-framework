package com.jiaoyiping.microframework.mvc.annotations;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/12/29
  * Time: 11:29
  * To change this template use File | Settings | Editor | File and Code Templates
 */

public enum ParamType {
    INT("int", Integer.class),
    STRING("string", String.class),
    DOUBLE("double", Double.class),
    BOOLEAN("boolean", Boolean.class),
    OBJECT("object", Object.class);

    ParamType(String typeName, Class clazz) {
        this.typeName = typeName;
        this.clazz = clazz;
    }

    private String typeName;
    private Class clazz;

    public Class<?> getType() {
        return this.clazz;
    }
}
