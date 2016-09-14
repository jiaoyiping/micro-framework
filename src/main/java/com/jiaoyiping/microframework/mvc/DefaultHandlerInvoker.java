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
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//TODO 处理请求参数的映射的实现太简单,使用起来有一些限制,可以参考一下SpringMVC是怎么实现的
public class DefaultHandlerInvoker implements HandlerInvoker {
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

    private Collection<Object> getRequestParams(HttpServletRequest request, HttpServletResponse response, RequestHandler requestHandler) {
        Collection<Object> params = new ArrayList();
        Collection<Class<?>> requestTypes = Arrays.asList(requestHandler.getActionMethod().getParameterTypes());
        if (!CollectionUtils.isEmpty(requestTypes) && requestTypes.size() == 2 && requestTypes.contains(HttpServletRequest.class) && requestTypes.contains(HttpServletResponse.class)) {
            params.add(request);
            params.add(response);
        }
        if (!CollectionUtils.isEmpty(requestTypes) && requestTypes.size() == 1 && requestTypes.contains(HttpServletRequest.class)) {
            params.add(request);
        }
        if (!CollectionUtils.isEmpty(requestTypes) && requestTypes.size() == 1 && requestTypes.contains(HttpServletResponse.class)) {
            params.add(response);
        }
        //TODO 20160913(目前第一个快速验证的版本,只支持使用HttpServletRequest和HttpServletResponse来作为方法参数)
        /*Enumeration<String> requestNames = request.getParameterNames();
        while (requestNames.hasMoreElements()) {
            String name = requestNames.nextElement();
        }*/
        return params;
    }
}