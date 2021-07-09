package cn.demo.gateway.handler;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author 义云
 * @Description 自定义异常处理
 * <p>异常时用JSON代替HTML异常信息<p>
 * @Date 2020/4/20 15:14
 */
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {

  public JsonExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties,
                              ApplicationContext applicationContext) {

    super(errorAttributes, resourceProperties, errorProperties, applicationContext);
  }

  /**
   * 获取异常属性
   */
  @Override
  protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {

    int code = 500;
    Throwable error = super.getError(request);
    if (error instanceof org.springframework.cloud.gateway.support.NotFoundException) {
      code = 404;
    }
    if (error instanceof ResponseStatusException) {
      code = ((ResponseStatusException) error).getStatus().value();
    }
    if (error instanceof com.alibaba.csp.sentinel.slots.block.flow.FlowException) {
      code = 999;
    }
    return response(code, this.buildMessage(request, error));
  }

  /**
   * 指定响应处理方法为JSON处理的方法
   * @param errorAttributes
   */
  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

    return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
  }

  /**
   * 根据code获取对应的HttpStatus
   * @param errorAttributes
   */
  @Override
  protected int getHttpStatus(Map<String, Object> errorAttributes) {

    int statusCode = (int) errorAttributes.get("code");
    return statusCode;
  }

  /**
   * 构建异常信息
   * @param request
   * @param ex
   * @return
   */
  private String buildMessage(ServerRequest request, Throwable ex) {

    StringBuilder message = new StringBuilder("Failed to handle request [");
    message.append(request.methodName());
    message.append(" ");
    message.append(request.uri());
    message.append("]");
    if (ex != null) {
      message.append(": ");
      message.append(ex.getMessage());
    }
    return message.toString();
  }

  /**
   * 构建返回的JSON数据格式
   * @param status    状态码
   * @param errorMessage  异常信息
   * @return
   */
  public static Map<String, Object> response(int status, String errorMessage) {

    Map<String, Object> map = new HashMap<>();
    map.put("code", status);
    map.put("message", errorMessage);
    map.put("data", null);
    map.put("success", false);
    return map;
  }

}
