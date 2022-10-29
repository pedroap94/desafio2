package com.pedro.accountmanager.controller;

import com.pedro.accountmanager.dto.ExtratoPeriodoDTO;
import com.pedro.accountmanager.dto.TransacoesDTO;
import com.pedro.accountmanager.interfaces.TransacoesInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("transacoes")
@AllArgsConstructor
public class TransacoesController {
    private TransacoesInterface transacoesService;

    @GetMapping("{idConta}")
    public ResponseEntity<List<TransacoesDTO>> gerarExtrato(@PathVariable("idConta") Long idConta) {
        return new ResponseEntity<>(transacoesService.recuperarExtrato(idConta), HttpStatus.OK);
    }

    @PostMapping("extrato-periodo")
    public ResponseEntity<List<TransacoesDTO>> extratoPeriodo(@Valid @RequestBody ExtratoPeriodoDTO extratoPeriodoDTO) {
        return new ResponseEntity<>(transacoesService.recuperarExtratoPeriodo(extratoPeriodoDTO), HttpStatus.OK);
    }
}
