package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/12
  * Time: 15:35
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.ioc.BeanHelper;
import com.jiaoyiping.microframework.mvc.annotations.ParamType;
import com.jiaoyiping.microframework.mvc.annotations.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO 处理请求参数的映射的实现太简单,使用起来有一些限制,可以参考一下SpringMVC是怎么实现的
public class DefaultHandlerInvoker implements HandlerInvoker {
    private static final String HTTP_SERVLET_REQUEST = "javax.servlet.http.HttpServletRequest";
    private static final String HTTP_SERVLET_RESPONSE = "javax.servlet.http.HttpServletResponse";
    Logger logger = LoggerFactory.getLogger(DefaultHandlerInvoker.class);
    private ViewResolver viewResolver = new DefaultViewResolver();

    @Override
    public void invoke(HttpServletRequest request, HttpServletResponse response, RequestHandler handler) {
        Method method = handler.getActionMethod();
        Class<?> actionClass = handler.getActionClass();
        Object actionInstance = BeanHelper.getBean(actionClass);
        Collection<Object> params = getRequestParams(request, response, handler);
        Object invokeResult = null;
        try {
            invokeResult = invokeActionMethod(method, actionInstance, params);
        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
        viewResolver.resolveView(request, response, invokeResult);

    }

    private Object invokeActionMethod(Method actionMethod, Object actionInstance, Collection<Object> params) throws InvocationTargetException, IllegalAccessException {
        actionMethod.setAccessible(true);
        return actionMethod.invoke(actionInstance, params.toArray());
    }

    /**
     * 填充请求参数列表,为反射调用做准备
     * TODO 需要参考springMVC的实现方法(参数名称和参数类型交给用户去定义)
     * // TODO: 2017/1/2 需要考虑用问号来传递参数的方式
     *
     * @param request
     * @param response
     * @param requestHandler
     * @return
     */
    private Collection<Object> getRequestParams(HttpServletRequest request, HttpServletResponse response, RequestHandler requestHandler) {
        Collection<Object> params = new ArrayList();
        List<Class<?>> requestTypes = Arrays.asList(requestHandler.getActionMethod().getParameterTypes());
        Pattern pattern = requestHandler.getRequestPathPattern();
        for (int i = 0; i < requestTypes.size(); i++) {
            Class<?> clazz = requestTypes.get(i);
            if (HTTP_SERVLET_REQUEST.equals(clazz.getName())) {
                params.add(request);
            } else if (HTTP_SERVLET_RESPONSE.equals(clazz.getName())) {
                params.add(response);
            }
            // TODO: 2017/1/3 请求参数,肯定会和URL Pattern中的名字相对应
            else if (clazz.isAnnotationPresent(PathParam.class)) {
                PathParam annotation = clazz.getAnnotation(PathParam.class);
                String paramName = annotation.paramName();
                ParamType paramType = annotation.paramType();
                if (pattern!=null){
//                    pattern.m
                }
                String value = null;

            }
            // TODO: 2017/1/3 对象,直接映射
            else {
                Matcher matcher = requestHandler.getRequestPathPattern().matcher(request.getRequestURI());
                if (matcher.matches() && matcher.group(i + 1) != null) {
                    params.add(matcher.group(i + 1));
                }
            }


        }
        return params;
    }

    private Object buildParam(ParamType paramType, String paramValue) {
        return null;
    }
}