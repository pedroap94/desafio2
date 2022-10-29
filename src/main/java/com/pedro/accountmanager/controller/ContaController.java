package com.pedro.accountmanager.controller;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.dto.OperacaoDTO;
import com.pedro.accountmanager.facade.ContasFacade;
import com.pedro.accountmanager.interfaces.ContaInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("conta")
@AllArgsConstructor
public class ContaController {
    private ContasFacade contasFacade;
    private ContaInterface contaService;

    @PostMapping("criar-conta")
    public ResponseEntity<Void> criarConta(@Valid @RequestBody ContaDTO contaDTO) {
        contasFacade.criarConta(contaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("depositar")
    public ResponseEntity<Void> depositar(@Valid @RequestBody OperacaoDTO operacaoDTO) {
        contasFacade.depositar(operacaoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("saldo/{id}")
    public ResponseEntity<BigDecimal> saldo(@PathVariable("id") Long id) {
        return new ResponseEntity<>(contaService.saldoConta(id), HttpStatus.OK);
    }

    @PostMapping("sacar")
    public ResponseEntity<Void> realizarSaque(@Valid @RequestBody OperacaoDTO operacaoDTO) {
        contasFacade.sacar(operacaoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("inativar-conta/{id}")
    public ResponseEntity<Void> inativarConta(@PathVariable("id") Long id) {
        contaService.bloquearConta(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
