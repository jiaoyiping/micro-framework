package com.jiaoyiping.microframework.core.clazz;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/11
  * Time: 17:00
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.core.ConfigHelper;

import java.lang.annotation.Annotation;
import java.util.Collection;

public class ClassHelper {

    private static ClassScanner classScanner = new DefaultClassScanner();

    private static final String basePackage = ConfigHelper.getConfig("basepackage");

    //获取基础包中的所有的类
    public static Collection<Class<?>> getAllClasses() {
        return classScanner.getClasses(basePackage);
    }

    //获取基础包中的有指定的父类或者接口的类
    public static Collection<Class<?>> getClassesBySuper(Class<?> superClass) {
        return classScanner.getClassesBySuperClass(basePackage, superClass);
    }

    //获取基础包中有指定的注解的类
    public static Collection<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotationClass) {
        return classScanner.getClassesByAnnotation(basePackage, annotationClass);
    }

}