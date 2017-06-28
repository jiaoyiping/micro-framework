package com.jiaoyiping.microframework.event;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2017/6/28
  * Time: 15:45
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.core.clazz.ClassHelper;
import com.jiaoyiping.microframework.ioc.BeanHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.Executors;

public class EventHelper {
    private static Logger logger = LoggerFactory.getLogger(EventHelper.class);

    static {
        //生成内置对象(实例化SimpleApplicationEventMulticaster,添加到beanMap,作为内置对象)
        //注册Listener(根据父类找到所有的Listener,注册到实例化EventMulticaster)
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        simpleApplicationEventMulticaster.setTaskExecutor(Executors.newCachedThreadPool());
        BeanHelper.getBeanMap().put(SimpleApplicationEventMulticaster.class, simpleApplicationEventMulticaster);
        Collection<Class<?>> eventListener = ClassHelper.getClassesBySuper(ApplicationListener.class);
        eventListener.stream().forEach(clazz -> {
            try {
                ApplicationListener<?> applicationListener = (ApplicationListener<?>) clazz.newInstance();
                simpleApplicationEventMulticaster.addApplicationListener(applicationListener);
            } catch (InstantiationException e) {
                logger.error(e.getLocalizedMessage(), e);
            } catch (IllegalAccessException e) {
                logger.error(e.getLocalizedMessage(), e);
            }
        });
    }
}    