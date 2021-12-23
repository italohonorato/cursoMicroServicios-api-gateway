package cl.teamweichafe.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(GatewayGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Ejecutando Filtro PRE...");

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("Ejecutando Filtro POST...");
        }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
