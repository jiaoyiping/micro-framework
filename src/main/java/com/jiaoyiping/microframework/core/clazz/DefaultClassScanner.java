package com.jiaoyiping.microframework.core.clazz;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/4
  * Time: 22:53
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.lang.annotation.Annotation;
import java.util.Collection;

public class DefaultClassScanner implements ClassScanner {
    @Override
    public Collection<Class<?>> getClasses(String packageName) {
        return new ClassInitTemplate(packageName) {
            @Override
            protected boolean classCanBeAdd(Class<?> clazz) {
                String className = clazz.getName();
                String classPackageName = className.substring(0, className.lastIndexOf("."));
                return classPackageName.startsWith(packageName);
            }
        }.getClasses();
    }

    @Override
    public Collection<Class<?>> getClassesBySuperClass(String packageName, Class<?> superClass) {
        return new ClassInitTemplate(packageName) {
            @Override
            protected boolean classCanBeAdd(Class<?> clazz) {
                return superClass.isAssignableFrom(clazz) && !superClass.equals(clazz);
            }
        }.getClasses();
    }

    @Override
    public Collection<Class<?>> getClassesByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return new ClassInitTemplate(packageName) {
            @Override
            protected boolean classCanBeAdd(Class<?> clazz) {
                return clazz.isAnnotationPresent(annotationClass);
            }
        }.getClasses();
    }
}