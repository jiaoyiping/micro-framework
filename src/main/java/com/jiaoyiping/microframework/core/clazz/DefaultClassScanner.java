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
    public Collection<Class<?>> getClasses(String pkgName) {
        return null;
    }

    @Override
    public Collection<Class<?>> getClassesBySuperClass(String pkgName, Class<?> superClass) {
        return null;
    }

    @Override
    public Collection<Class<?>> getClassesByAnnotation(String pkgName, Class<? extends Annotation> annotationClass) {
        return null;
    }
}