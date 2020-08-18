//package cn.demo.gateway.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @version 1.0
// * @Author 义云
// * @Description
// * @Date 2020/4/20 14:21
// */
//@Slf4j
//@Component
//public class TokenFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = exchange.getRequest().getHeaders().getFirst("token");
//        if (token == null || token.isEmpty()) {
//            log.info( "token is empty..." );
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//        return chain.filter(exchange);
//    }
//
//    /**
//     * 拦截顺序，值越小，优先级越高
//     * @return
//     */
//    @Override
//    public int getOrder() {
//        return -100;
//    }
//}
