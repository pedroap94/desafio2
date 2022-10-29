package com.pedro.accountmanager.utils;

import com.pedro.accountmanager.model.Contas;
import com.pedro.accountmanager.model.Pessoas;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContasUtils {
    public static Contas criarContas() {
        return new Contas(new Pessoas("Teste", "1234",
                LocalDate.now()), BigDecimal.TEN,
                BigDecimal.TEN, 1);
    }
}
