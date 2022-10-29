package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.PessoaDTO;
import com.pedro.accountmanager.exception.PessoaException;
import com.pedro.accountmanager.model.Pessoas;
import com.pedro.accountmanager.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void quandoPessoaNaoExsitir_deveCadastrar() {
        when(pessoaRepository.findByNome(any())).thenReturn(null);

        pessoaService.cadastrarPessoa(new PessoaDTO());

        verify(pessoaRepository, times(1)).save(any());
    }

    @Test
    void quandoPessoaExistir_deveRetornarException() {
        when(pessoaRepository.findByNome((any()))).thenReturn(new Pessoas("Teste", "1234", LocalDate.now()));

        Throwable throwable = assertThrows(Throwable.class, () -> pessoaService.cadastrarPessoa(new PessoaDTO()));

        assertEquals(PessoaException.class, throwable.getClass());
    }
}