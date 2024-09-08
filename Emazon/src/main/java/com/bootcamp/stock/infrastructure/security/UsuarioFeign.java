package com.bootcamp.stock.infrastructure.security;

import com.bootcamp.stock.infrastructure.configuration.FeingClientConfiguration;
import com.bootcamp.stock.ports.aplication.http.dto.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "usuario-service", url = "http://localhost:8080", configuration = FeingClientConfiguration.class)
public interface UsuarioFeign {

    @PostMapping("/auth/validate-token/{token}")
    UsuarioResponse validateToken(@PathVariable String token);
}
