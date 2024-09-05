package com.bootcamp.stock.ports.aplication.http.dto;

import lombok.Data;

@Data
public class UsuarioResponse {
    private String username;
    private String role;
    private Boolean authorized;
}
