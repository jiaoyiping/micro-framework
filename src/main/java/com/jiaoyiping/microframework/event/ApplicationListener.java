package com.jiaoyiping.microframework.event;

import java.util.EventListener;

/*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/12/1
  * Time: 16:21
  * To change this template use File | Settings | Editor | File and Code Templates
 */
@FunctionalInterface
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}    
