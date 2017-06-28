package com.jiaoyiping.microframework.utils;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/13
  * Time: 16:19
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = null;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }


    public static String jsonToString(Object object, String... properties) {

        try {
            return objectMapper.writer(
                    new SimpleFilterProvider().addFilter(null,
                            SimpleBeanPropertyFilter.filterOutAllExcept(properties))).writeValueAsString(object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public static void stringToJson(OutputStream out, Object object) {

        try {
            objectMapper.writeValue(out, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String jsonToString(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }


    public static void stringToJson(OutputStream out, Object object, String... properties) {

        try {
            objectMapper.writer(
                    new SimpleFilterProvider().addFilter(null,
                            SimpleBeanPropertyFilter.filterOutAllExcept(properties))).writeValue(out, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * <pre> 读取Json字符串，数据绑定到具体实体类
     * Desc
     * @param json
     * @param clazz
     * @return
     * @author felv
     * @refactor felv
     * @date Nov 2, 2014 9:17:43 AM
     * </pre>
     */
    public static <T> T parse(String json, Class<T> clazz) {

        if (json == null || json.length() == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 读取JSON 内容返回HashMap对象
     *
     * @param json
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static HashMap parseMap(String json) {
        if (json == null || json.length() == 0) {
            return null;
        }
        try {

            return objectMapper.readValue(json, HashMap.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<LinkedHashMap<String, Object>> parseList(String json) {

        if (json == null || json.length() == 0) {
            return null;
        }
        try {

            return objectMapper.readValue(json, List.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;


    }

    public static <T> T parseFile(File file, Class<T> clazz) {
        if (file == null) {
            return null;
        }
        try {
            return objectMapper.readValue(file, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <pre>
     * Desc
     * @param jsonData
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     * @author felv
     * @refactor felv
     * @date Nov 2, 2014 9:17:18 AM
     * </pre>
     */
    public static JsonNode getJsonNode(String jsonData) throws JsonProcessingException, IOException {

        return objectMapper.readTree(jsonData);
    }

}
