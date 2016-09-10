package com.jiaoyiping.microframework.utils;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/4
  * Time: 22:42
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class ClassUtils {
    private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取类路径
     *
     * @return
     */
    public static String getClassPath() {
        String classpath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
        return cls;
    }

}    