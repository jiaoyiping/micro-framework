package com.jiaoyiping.microframework.ioc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/5
  * Time: 13:17
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.ioc.annotation.Autowired;
import com.jiaoyiping.microframework.ioc.annotation.Bean;
import com.jiaoyiping.microframework.ioc.annotation.Service;
import com.jiaoyiping.microframework.mvc.annotations.Controller;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

public class IOCHelper {
    private static Logger logger = LoggerFactory.getLogger(IOCHelper.class);

    private static boolean isIocAnnotation(Class<?> clazz) {

        return clazz.isAnnotationPresent(Bean.class)
                || clazz.isAnnotationPresent(Controller.class)
                || clazz.isAnnotationPresent(Service.class);
    }

    //组装bean
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            Class<?> beanEntry = entry.getKey();
            if (isIocAnnotation(beanEntry)) {
                Object instance = entry.getValue();
                Field[] fields = beanEntry.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(fields)) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Autowired.class)) {
                            Class<?> target = field.getType();
                            Object targetInstance = BeanHelper.getBean(target);
                            field.setAccessible(true);
                            try {
                                field.set(instance, targetInstance);
                            } catch (IllegalAccessException e) {
                                logger.error("组装Bean出现异常...");
                                logger.error(e.getLocalizedMessage(),e);
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }


}    