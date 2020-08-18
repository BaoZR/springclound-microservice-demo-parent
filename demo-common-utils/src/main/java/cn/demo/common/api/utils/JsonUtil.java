package cn.demo.common.api.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ContainerNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-11 13:57
 */
@Slf4j
public class JsonUtil {
    private static ObjectMapper mapper;

    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
        if (createNew) {
            return new ObjectMapper();
        } else if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.setLocale(Locale.CHINA);
        }
        return mapper;
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("json2Object" + json, e);
            return null;
        }
    }

    public static <T> T json2ObjectWithCaseInSensitive(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            ObjectMapper objectMapper = getMapperInstance(true);
            objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            objectMapper.setLocale(Locale.CHINA);
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("json2Object" + json, e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2Map(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
            return (Map<String, Object>) objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            log.error("json2Map:" + json, e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2MapAllowSingleQuotes(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            return (Map<String, Object>) objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            log.error("json2Map:" + json, e);
            return null;
        }
    }

    public static Map<String, Object> json2MapWithException(String json) throws IOException {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        ObjectMapper objectMapper = getMapperInstance(false);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
        return (Map<String, Object>) objectMapper.readValue(json, Map.class);
    }

    public static String object2Json(Object param) {
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            return objectMapper.writeValueAsString(param);
        } catch (Exception e) {
            log.warn("object2Json：", e);
            return StringUtils.EMPTY;
        }
    }

    public static Map<String, Object> object2Map(Object param) {
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            return objectMapper.convertValue(param, Map.class);
        } catch (Exception e) {
            log.warn("object2Map：" + e.getMessage());
            return new HashMap<>();
        }
    }

    public static SortedMap<String, Object> object2TreeMap(Object param) {
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            return objectMapper.convertValue(param, TreeMap.class);
        } catch (Exception e) {
            log.warn("object2TreeMap：" + e.getMessage());
            return new TreeMap();
        }
    }

    public static <T> T map2Object(Object map, Class<T> clazz) {
        String json = object2Json(map);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return json2Object(json, clazz);
    }

    public static String containNullObject2Json(Object param) {
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            objectMapper.setSerializationInclusion(Include.ALWAYS);
            return objectMapper.writeValueAsString(param);
        } catch (Exception e) {
            log.error("object2Json" + e.getMessage(), e);
            return StringUtils.EMPTY;
        }
    }

    public static List jsonToList(String json, Class clazz) {
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            List list = objectMapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            log.error("jsonToList error:json={}", json, e);
            return null;
        }
    }

    /**
     * 获取指定节点下的字符串
     */
    public static String getSubString(String json, String path) {
        if (StringUtils.isBlank(json) || StringUtils.isBlank(path)) {
            return null;
        }
        String result = null;
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode targetNode = rootNode.at(JsonPointer.compile(path));
            result = (targetNode instanceof ContainerNode) ? targetNode.toString() : targetNode.asText();
        } catch (Exception e) {
            log.error("JsonUtil | getSubString error : json={}", json, e);
        }
        return result;
    }
}
