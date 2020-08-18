package cn.demo.gateway.handler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @Author 义云 限流后操作
 * @Description
 * @Date 2020/4/21 14:51
 */
public class SentinelBlockExceptionHandler implements WebExceptionHandler {

    private List<ViewResolver> viewResolvers;
    private List<HttpMessageWriter<?>> messageWriters;

    public SentinelBlockExceptionHandler(
            List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolvers;
        this.messageWriters = serverCodecConfigurer.getWriters();
    }
    /**
     * 自定义返回
     * @param response
     * @param exchange
     * @return
     */
    private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange) {
        ServerHttpResponse resp = exchange.getResponse();
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        Map map = new HashMap();
        map.put("code","666");
        map.put("message","系统限流");
        map.put("data","");
        map.put("success",false);
        String json = JSON.toJSONString(map);
        DataBuffer buffer = resp.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Mono.just(buffer));
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        if (!BlockException.isBlockException(ex)) {
            return Mono.error(ex);
        }
        return handleBlockedRequest(exchange, ex)
                .flatMap(response -> writeResponse(response, exchange));
    }

    private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable) {
        return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
    }
    private final Supplier<ServerResponse.Context> contextSupplier = () -> new ServerResponse.Context() {
        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return SentinelBlockExceptionHandler.this.messageWriters;
        }
        @Override
        public List<ViewResolver> viewResolvers() {
            return SentinelBlockExceptionHandler.this.viewResolvers;
        }
    };
}