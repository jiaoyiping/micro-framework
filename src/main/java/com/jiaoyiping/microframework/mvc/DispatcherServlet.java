package com.jiaoyiping.microframework.mvc;

import com.jiaoyiping.microframework.utils.WebUtils;
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
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String CONTEXT_BASE = "/";
    private Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private HandlerMapping handlerMapping = new DefaultHandlerMapping();
    private HandlerInvoker handlerInvoker = new DefaultHandlerInvoker();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DEFAULT_ENCODING);
        String requestMethod = req.getMethod();
        String path = req.getPathInfo();
        logger.debug("request:{},{}", requestMethod, path);

        //todo 需要解决以下bug:
        //                 1.当路径不匹配就时报错的问题
        //                 2.向主页的重定向问题
        //                 3.用户自定义400,404和500页面
        //                 4.如何分离对静态资源的请求

        if (CONTEXT_BASE.equals(path)) {
            req.getRequestDispatcher(CONTEXT_BASE).forward(req, resp);
        }

        RequestHandler requestHandler = handlerMapping.getHandler(requestMethod, path);
        if (requestHandler == null) {
            WebUtils.sendError(404, "未找到对应的资源", resp);
        }
        handlerInvoker.invoke(req, resp, requestHandler);
    }
}