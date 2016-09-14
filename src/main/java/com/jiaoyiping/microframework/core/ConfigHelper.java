package com.jiaoyiping.microframework.core;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/11
  * Time: 20:39
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {
    private static final String propertiesFileName = "micro.properties";
    private static Logger logger = LoggerFactory.getLogger(ConfigHelper.class);
    private static Properties properties = new Properties();

    static {
        InputStream is = ClassUtils.getClassLoader().getResourceAsStream(propertiesFileName);
        try {
            properties.load(is);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    public static String getConfig(String configName) {
        return properties.getProperty(configName);
    }
}