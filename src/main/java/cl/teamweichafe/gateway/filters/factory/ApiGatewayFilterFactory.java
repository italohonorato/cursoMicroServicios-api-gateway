package cl.teamweichafe.gateway.filters.factory;

import cl.teamweichafe.gateway.configs.ApiGatewayFilterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ApiGatewayFilterFactory extends AbstractGatewayFilterFactory<ApiGatewayFilterConfig> {

    private final Logger logger = LoggerFactory.getLogger(ApiGatewayFilterFactory.class);

    public ApiGatewayFilterFactory() {
        super(ApiGatewayFilterConfig.class);
    }

    @Override
    public GatewayFilter apply(ApiGatewayFilterConfig config) {

        return (exchange, chain) -> {
            logger.info("Ejecutando PRE Gateway filter...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Ejecutando POST Gateway filter...");
            }));
        };
    }
}
