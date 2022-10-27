package com.pedro.accountmanager.controller;

import com.pedro.accountmanager.dto.PessoaDTO;
import com.pedro.accountmanager.exception.PessoaException;
import com.pedro.accountmanager.model.Pessoas;
import com.pedro.accountmanager.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pessoa")
@AllArgsConstructor
public class PessoaController {
    private PessoaService pessoaService;

    @PostMapping("cadastrar")
    public ResponseEntity<Void> cadastrarPessoa(@RequestBody PessoaDTO pessoaDTO){
        try{
            pessoaService.cadastrarPessoa(pessoaDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PessoaException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
