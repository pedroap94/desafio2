package com.pedro.accountmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransacoesDTO {
    private BigDecimal valor;
    private LocalDateTime dataTransacao;
}
