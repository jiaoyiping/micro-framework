package com.jiaoyiping.microframework.mvc;

import com.jiaoyiping.microframework.ioc.BeanHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/4
  * Time: 22:25
  * To change this template use File | Settings | Editor | File and Code Templates
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private HandlerMapping handlerMapping = new DefaultHandlerMapping();
    private HandlerInvoker handlerInvoker = new DefaultHandlerInvoker();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String requestMethod = req.getMethod();
        String path = req.getPathInfo();
        logger.debug("request:{},{}", requestMethod, path);
        RequestHandler requestHandler = handlerMapping.getHandler(requestMethod, path);
        handlerInvoker.invoke(req, resp, requestHandler);
    }
}