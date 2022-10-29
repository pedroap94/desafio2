package com.pedro.accountmanager.facade;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.dto.OperacaoDTO;
import com.pedro.accountmanager.exception.ContaException;
import com.pedro.accountmanager.interfaces.ContaInterface;
import com.pedro.accountmanager.interfaces.TransacoesInterface;
import com.pedro.accountmanager.utils.ContasUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
    void quandoEnviarContaDTO_deveCriarConta() {
        when(contaInterface.criarConta(any())).thenReturn(ContasUtils.criarContas());
        contasFacade.criarConta(new ContaDTO());
        verify(contaInterface, times(1)).criarConta(any());
        verify(transacoesInterface, times(1)).realizarDeposito(any(), any());
    }

    @Test
    void quandoOperacaoDTOCorreto_deveRealizarDeposito() {
        when(contaInterface.depositoConta(any(), any())).thenReturn(ContasUtils.criarContas());
        contasFacade.depositar(new OperacaoDTO());
        verify(contaInterface, times(1)).depositoConta(any(), any());
        verify(transacoesInterface, times(1)).realizarDeposito(any(), any());
    }

    @Test
    void quandoOperacaoDTOIncorreto_deveRetornarExceptionAoTentarRealizarDeposito() {
        OperacaoDTO operacaoDTO = new OperacaoDTO();
        when(contaInterface.depositoConta(any(), any())).thenThrow(ContaException.class);
        assertThrows(ContaException.class, () -> contasFacade.depositar(operacaoDTO));
    }

    @Test
    void quandoOperacaoDTOCorreto_deveRealizarSaque() {
        OperacaoDTO operacaoDTO = new OperacaoDTO();
        operacaoDTO.setIdConta(1L);
        when(transacoesInterface.limiteDiarioUtilizado(anyLong())).thenReturn(BigDecimal.TEN);
        when(contaInterface.saqueConta(any(), any(), any())).thenReturn(ContasUtils.criarContas());

        contasFacade.sacar(operacaoDTO);
        verify(contaInterface, times(1)).saqueConta(any(), any(), any());
        verify(transacoesInterface, times(1)).limiteDiarioUtilizado(anyLong());
        verify(transacoesInterface, times(1)).realizarSaque(any(), any());
    }

    @Test
    void quandoOperacaoDTOIncorreto_deveRetornarExceptionAoTentarRealizarSaque() {
        OperacaoDTO operacaoDTO = new OperacaoDTO();
        operacaoDTO.setIdConta(1L);
        when(transacoesInterface.limiteDiarioUtilizado(anyLong())).thenReturn(BigDecimal.TEN);
        when(contaInterface.saqueConta(any(), any(), any())).thenThrow(ContaException.class);

        assertThrows(ContaException.class, () -> contasFacade.sacar(operacaoDTO));
    }
}