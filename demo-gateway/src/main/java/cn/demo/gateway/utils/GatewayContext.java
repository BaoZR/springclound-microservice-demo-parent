package cn.demo.gateway.utils;

import org.springframework.util.MultiValueMap;

import lombok.Data;


@Data
public class GatewayContext {
    public static final String CACHE_GATEWAY_CONTEXT = "cacheGatewayContext";
    /**
     * path路径
     */
    private String path;
    /**
     * body数据
     */
    private String cacheBody;
    /**
     * form-data数据
     */
    private MultiValueMap<String, String> formData;
    /**
     * param数据
     */
    private MultiValueMap<String, String> requestData;
}
