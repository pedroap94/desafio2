package com.pedro.accountmanager.controller;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.facade.ContasFacade;
import com.pedro.accountmanager.service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("conta")
@AllArgsConstructor
public class ContaController {
    private ContasFacade contasFacade;

    @PostMapping("criar-conta")
    public ResponseEntity<Void> criarConta(@RequestBody ContaDTO contaDTO){
        contasFacade.criarConta(contaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
