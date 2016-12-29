package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/4
  * Time: 22:39
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.core.clazz.ClassHelper;
import com.jiaoyiping.microframework.mvc.annotations.Controller;
import com.jiaoyiping.microframework.mvc.annotations.RequestMapping;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ActionUtils {
    private static Map<RequestInfo, RequestHandler> requestMap = new LinkedHashMap<>();

    //获取类路径下所有的Controller注解,并解析
    static {
        Collection<Class<?>> annotations = ClassHelper.getClassesByAnnotation(Controller.class);
        if (CollectionUtils.isNotEmpty(annotations)) {
            for (Class<?> clazz : annotations) {
                Method[] methods = clazz.getDeclaredMethods();
                if (methods != null && methods.length != 0) {
                    for (Method method : methods) {
                        handleActionMethod(clazz, method, requestMap);
                    }

                }
            }

        }


    }

    private static void handleActionMethod(Class<?> actionClass, Method actionMethod, Map<RequestInfo, RequestHandler> actionMap) {
        String basePath = "";
        if (actionClass.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping baseMapping = actionClass.getAnnotation(RequestMapping.class);
            basePath = baseMapping.value();
        }
        if (actionMethod.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping annotation = actionMethod.getAnnotation(RequestMapping.class);
            String requestMappingPath = annotation.value();
            String path = basePath + requestMappingPath;
            String method = annotation.method().name();
            RequestHandler requestHandler = new RequestHandler(actionClass, actionMethod);
            if (isRegexPath(requestMappingPath)) {
                path = path.replaceAll("\\{\\w+\\}", "(\\\\w+)");
                requestHandler.setRequestPathPattern(Pattern.compile(path));
            }
            actionMap.put(new RequestInfo(method, path), requestHandler);


        }
    }

    public static Map<RequestInfo, RequestHandler> getRequestMap() {
        return requestMap;
    }

    private static boolean isRegexPath(String requestMappingPath) {
        return requestMappingPath.matches(".+\\{\\w+\\}.*");
    }
}
