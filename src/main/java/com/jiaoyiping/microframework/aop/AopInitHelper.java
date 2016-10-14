package com.jiaoyiping.microframework.aop;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/8
  * Time: 15:52
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.aop.annotation.Aspect;
import com.jiaoyiping.microframework.core.clazz.ClassHelper;
import com.jiaoyiping.microframework.ioc.BeanHelper;
import com.jiaoyiping.microframework.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collection;

public class AopInitHelper {
    private static Logger logger = LoggerFactory.getLogger(AopInitHelper.class);

    //TODO 没有考虑到代理链的问题
    static {
        Collection<Class<?>> aspectCollection = ClassHelper.getClassesBySuper(BaseAspect.class);
        aspectCollection.stream().filter(clazz -> clazz.isAnnotationPresent(Aspect.class)).forEach(clazz -> {
            Aspect aspect = clazz.getAnnotation(Aspect.class);
            String packageName = aspect.packageName();
            String className = aspect.className();
            String methodNme = aspect.methodNmae();
            logger.debug("add aspect {}.{},for method:{}", packageName, className, methodNme);
            Class<?> targetClass = ClassUtils.loadClass(packageName + "." + className, false);
            try {
                Object targetInstance = BeanHelper.getBean(targetClass);
                if (targetInstance != null) {
                    BaseAspect baseAspect = (BaseAspect) clazz.newInstance();
                    Object proxyInstance = baseAspect.getProxy(targetClass);
                    //复制目标实例的字段到代理实例
                    for (Field field : targetClass.getDeclaredFields()) {
                        field.setAccessible(true); // 可操作私有字段
                        field.set(proxyInstance, field.get(targetInstance));
                    }
                    //用代理实例覆盖目标实例
                    BeanHelper.setBean(targetClass, proxyInstance);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        });
    }
}