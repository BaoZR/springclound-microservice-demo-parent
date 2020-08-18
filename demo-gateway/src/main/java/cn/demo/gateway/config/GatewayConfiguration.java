package cn.demo.gateway.config;

import cn.demo.gateway.handler.SentinelBlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/20 14:09
 */
@Configuration
public class GatewayConfiguration {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 限流后异常处理
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelBlockExceptionHandler getSentinelBlockExceptionHandler() {
        return new SentinelBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * 配置SentinelGatewayFilter
     * @return
     */
    @Bean
    @Order(-1)
    public GlobalFilter getGlobalFilter() {
        return new SentinelGatewayFilter();
    }

//    @PostConstruct
//    public void doInit() {
//        initCustomizedApis();
//        initGatewayRules();
//    }
//
//    /**
//     * ApiDefinition：用户自定义的 API 定义分组，可以看做是一些 URL 匹配的组合。
//     * 自定义了一些 API 分组
//     */
//    private void initCustomizedApis() {
//        Set<ApiDefinition> definitions = new HashSet<>();
//        ApiDefinition api1 = new ApiDefinition("some_customized_api")
//                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
//                    add(new ApiPathPredicateItem().setPattern("/ahas"));
//                    add(new ApiPathPredicateItem().setPattern("/product/**")
//                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
//                }});
//        ApiDefinition api2 = new ApiDefinition("another_customized_api")
//                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
//                    add(new ApiPathPredicateItem().setPattern("/**")
//                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
//                }});
//        definitions.add(api1);
//        definitions.add(api2);
//        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
//    }
//
//    /**
//     * GatewayFlowRule：网关限流规则，针对 API Gateway 的场景定制的限流规则，可以针对不同 route 或自定义的 API 分组进行限流，支持针对请求中的参数、Header、来源 IP 等进行定制化的限流。
//     * resource：资源名称，可以是网关中的 route 名称或者用户自定义的API 分组名称。
//     * resourceMode：规则是针对 API Gateway 的route（RESOURCE_MODE_ROUTE_ID）还是用户在 Sentinel 中定义的API 分组（RESOURCE_MODE_CUSTOM_API_NAME），默认是route。
//     * grade：限流指标维度，同限流规则的grade 字段。
//     * count：限流阈值
//     * intervalSec：统计时间窗口，单位是秒，默认是1 秒（目前仅对参数限流生效）。
//     * controlBehavior：流量整形的控制效果，同限流规则的 controlBehavior 字段，目前支持快速失败和匀速排队两种模式，默认是快速失败。
//     * burst：应对突发请求时额外允许的请求数目（目前仅对参数限流生效）。
//     * maxQueueingTimeoutMs：匀速排队模式下的最长排队时间，单位是毫秒，仅在匀速排队模式下生效。
//     * paramItem：参数限流配置。若不提供，则代表不针对参数进行限流，该网关规则将会被转换成普通流控规则；否则会转换成热点规则。其中的字段：
//     * parseStrategy：从请求中提取参数的策略，目前支持提取来源 IP（PARAM_PARSE_STRATEGY_CLIENT_IP）、Host（PARAM_PARSE_STRATEGY_HOST）、任意 Header（PARAM_PARSE_STRATEGY_HEADER）和任意 URL 参数（PARAM_PARSE_STRATEGY_URL_PARAM）四种模式。
//     * fieldName：若提取策略选择 Header 模式或 URL 参数模式，则需要指定对应的 header 名称或 URL 参数名称。
//     * pattern 和 matchStrategy：为后续参数匹配特性预留，目前未实现。
//     * 用户可以通过 GatewayRuleManager.loadRules(rules)手动加载网关规则，或通过 GatewayRuleManager.register2Property(property)注册动态规则源动态推送（推荐方式）。
//     * 自定义route纬度：
//     */
//    private void initGatewayRules() {
//        Set<GatewayFlowRule> rules = new HashSet<>();
//        rules.add(new GatewayFlowRule("storage")
//                .setCount(3)
//                .setIntervalSec(3)
////                .setParamItem(new GatewayParamFlowItem()
////                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
////                        .setFieldName("id")
////                )
//        );
////        rules.add(new GatewayFlowRule("aliyun_route")
////                .setCount(2)
////                .setIntervalSec(2)
////                .setBurst(2)
////                .setParamItem(new GatewayParamFlowItem()
////                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP)
////                )
////        );
////        rules.add(new GatewayFlowRule("httpbin_route")
////                .setCount(10)
////                .setIntervalSec(1)
////                .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
////                .setMaxQueueingTimeoutMs(600)
////                .setParamItem(new GatewayParamFlowItem()
////                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER)
////                        .setFieldName("X-Sentinel-Flag")
////                )
////        );
////        rules.add(new GatewayFlowRule("httpbin_route")
////                .setCount(1)
////                .setIntervalSec(1)
////                .setParamItem(new GatewayParamFlowItem()
////                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
////                        .setFieldName("pa")
////                )
////        );
////        rules.add(new GatewayFlowRule("httpbin_route")
////                .setCount(2)
////                .setIntervalSec(30)
////                .setParamItem(new GatewayParamFlowItem()
////                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
////                        .setFieldName("type")
////                        .setPattern("warn")
////                        .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_CONTAINS)
////                )
////        );
////
////        rules.add(new GatewayFlowRule("some_customized_api")
////                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
////                .setCount(5)
////                .setIntervalSec(1)
////                .setParamItem(new GatewayParamFlowItem()
////                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
////                        .setFieldName("pn")
////                )
////        );
//        GatewayRuleManager.loadRules(rules);
//    }
}
