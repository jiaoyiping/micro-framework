package com.jiaoyiping.microframework.event;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/12/1
  * Time: 16:39
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {
    private static final String LISTENER_TYPE_NAME = "com.jiaoyiping.microframework.event.ApplicationListener";
    private List<ApplicationListener<?>> listeners = new ArrayList<>();

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        synchronized (listeners) {
            if (listeners.contains(listener)) {
                listeners.remove(listener);
            }
        }
    }

    @Override
    public void removeAllListeners() {
        synchronized (listeners) {
            listeners.clear();
        }
    }

    /**
     * 本类作用:根据Event找到注册到这个Event上的Listener
     *
     * @return
     */
    protected Collection<ApplicationListener<?>> getApplicationListeners(ApplicationEvent event) {
        String eventClassName = event.getClass().getTypeName();
        return listeners.stream().filter(listener -> eventClassName.equals(getEventTypename(listener))).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 取出接口上注解的泛型类型
     *
     * @param obj
     * @return
     */
    private String getEventTypename(Object obj) {
        String result = null;
        Type[] types = obj.getClass().getGenericInterfaces();
        for (Type type : types) {
            if (type.getTypeName().startsWith(LISTENER_TYPE_NAME)) {
                Type[] genericType = ((ParameterizedType) types[0]).getActualTypeArguments();
                if (genericType.length != 0) {
                    result = genericType[0].getTypeName();
                }
            }
        }
        return result;

    }


}