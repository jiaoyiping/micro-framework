package com.jiaoyiping.microframework.utils;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/13
  * Time: 16:16
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebUtils {
    private static Logger logger = LoggerFactory.getLogger(WebUtils.class);

    public static void writeJson(HttpServletRequest request, HttpServletResponse response, Object data) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(JsonUtils.jsonToString(data));
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("写入数据出错", e);
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }

    }

    public static void writeHTML(HttpServletRequest request, HttpServletResponse response, Object data) {
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(JsonUtils.jsonToString(data));
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("写入HTML数据出错");
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 重定向
     *
     * @param path
     * @param request
     * @param response
     */
    public static void fowardRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 请求转发
     *
     * @param path
     * @param request
     * @param response
     */
    public static void redirectRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    public static void sendError(int code, String message, HttpServletResponse response) {
        try {
            response.sendError(code, message);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

}    