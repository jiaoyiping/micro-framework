package com.jiaoyiping.microframework.core;

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

    }
}