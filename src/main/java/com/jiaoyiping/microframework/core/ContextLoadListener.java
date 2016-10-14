package com.jiaoyiping.microframework.core;

import com.jiaoyiping.microframework.aop.AopInitHelper;
import com.jiaoyiping.microframework.ioc.BeanHelper;
import com.jiaoyiping.microframework.ioc.IOCHelper;
import com.jiaoyiping.microframework.mvc.ActionUtils;
import com.jiaoyiping.microframework.utils.ClassUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/4
  * Time: 22:31
  * To change this template use File | Settings | Editor | File and Code Templates
 */

/**
 * 初始化类
 */
@WebListener
public class ContextLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void init() {
        Class<?>[] classes = {
                ActionUtils.class,
                BeanHelper.class,
                AopInitHelper.class,
                IOCHelper.class

        };
        for (Class<?> clazz : classes) {
            ClassUtils.loadClass(clazz.getName(), true);
        }

    }
}