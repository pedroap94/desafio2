package com.pedro.accountmanager.facade;

import com.pedro.accountmanager.interfaces.ContaInterface;
import com.pedro.accountmanager.interfaces.TransacoesInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ContasFacadeTest {
    @InjectMocks
    private ContasFacade contasFacade;

    @Mock
    private ContaInterface contaInterface;

    @Mock
    private TransacoesInterface transacoesInterface;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void quadnoEnviarContaDTO_deveCriarConta() {

    }
}