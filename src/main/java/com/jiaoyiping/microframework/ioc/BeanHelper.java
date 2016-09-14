package com.jiaoyiping.microframework.ioc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/12
  * Time: 16:54
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.core.clazz.ClassHelper;
import com.jiaoyiping.microframework.mvc.HandlerInvoker;
import com.jiaoyiping.microframework.mvc.HandlerMapping;
import com.jiaoyiping.microframework.mvc.ViewResolver;
import com.jiaoyiping.microframework.mvc.annotations.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeanHelper {
    private static Map<Class<?>, Object> beanMap = new HashMap<>();

    //todo 20160912 目前只是创建了所有的Controller的实例
    //正式实现IOC模块的时候会创建其他的类的实例
    static {
        Collection<Class<?>> classes = ClassHelper.getAllClasses();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Controller.class) || clazz.isAssignableFrom(ViewResolver.class) || clazz.isAssignableFrom(HandlerMapping.class) || clazz.isAssignableFrom(HandlerInvoker.class)) {
                Object instance = null;
                try {
                    instance = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                beanMap.put(clazz, instance);
            }
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (!beanMap.containsKey(clazz)) {
            throw new RuntimeException("can not get bean by class: " + clazz);
        }
        return (T) beanMap.get(clazz);
    }

    public static void setBean(Class<?> clazz, Object obj) {
        beanMap.put(clazz, obj);
    }

}    