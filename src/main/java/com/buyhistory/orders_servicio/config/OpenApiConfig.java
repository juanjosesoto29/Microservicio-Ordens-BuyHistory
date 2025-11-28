package com.buyhistory.orders_servicio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ordersOpenAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("BuyHistory - API de Órdenes")
                    .description("Microservicio de gestión de órdenes y proceso de compra de la tienda BuyHistory.")
                    .version("v1.0"))
            .servers(List.of(
                    new Server()
                            .url("http://localhost:8087")
                            .description("Servidor local - Órdenes (8087)")
            ));
    }
}
