package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.ContaDTO;
import com.pedro.accountmanager.exception.ContaException;
import com.pedro.accountmanager.model.Contas;
import com.pedro.accountmanager.model.Pessoas;
import com.pedro.accountmanager.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ContaServiceTest {
    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void quandoEnviarContaDTO_deveCriarConta() {
        contaService.criarConta(new ContaDTO());
        verify(contaRepository, times(1)).save(any());
    }

    @Test
    void quandoDadosDepositoCorretos_deveAtualizarContaAoDepositar() {
        Contas conta = criarContas();
        BigDecimal valorDeposito = BigDecimal.ONE;

        when(contaRepository.findByIdContaAndFlagAtivoTrue(anyLong())).thenReturn(Optional.of(conta));
        when(contaRepository.save(any())).thenReturn(conta);
        Contas retorno = contaService.depositoConta(1L, valorDeposito);

        assertEquals(criarContas().getSaldo().add(valorDeposito), retorno.getSaldo());
    }

    @Test
    void quandoContaNaoEncontrada_deveRetornarExceptionAoTentarDepositar() {
        when(contaRepository.findByIdContaAndFlagAtivoTrue(anyLong())).thenReturn(Optional.empty());
        assertThrows(ContaException.class, () -> contaService.depositoConta(1L, BigDecimal.ONE));
    }

    @Test
    void quandoExistirConta_deveRetornarSaldo() {
        Contas conta = criarContas();
        when(contaRepository.findByIdContaAndFlagAtivoTrue(anyLong())).thenReturn(Optional.of(conta));
        BigDecimal retorno = contaService.saldoConta(1L);
        assertEquals(conta.getSaldo(), retorno);
    }

    @Test
    void quandoNaoExistirConta_deveRetornarExceptionAoTentarBuscarSaldo() {
        when(contaRepository.findByIdContaAndFlagAtivoTrue(anyLong())).thenReturn(Optional.empty());
        assertThrows(ContaException.class, () -> contaService.saldoConta(1L));
    }

    @Test
    void quandoContaExistir_deveSacarValor() {
        var conta = criarContas();
        var contaRetorno = criarContas();
        var valorSaque = BigDecimal.ONE;
        contaRetorno.setSaldo(contaRetorno.getSaldo().subtract(valorSaque));
        when(contaRepository.findByIdContaAndFlagAtivoTrue(anyLong())).thenReturn(Optional.of(conta));
        when(contaRepository.save(any())).thenReturn(contaRetorno);

        Contas retorno = contaService.saqueConta(1L, valorSaque, BigDecimal.ONE);

        assertEquals(retorno.getSaldo(), contaRetorno.getSaldo());
    }

    @Test
    void quandoContaNaoExistir_deveRetornarExceptionAoTentarSacar() {
        when(contaRepository.findByIdContaAndFlagAtivoTrue(any())).thenReturn(Optional.empty());
        assertThrows(ContaException.class, () -> contaService.saqueConta(1L, BigDecimal.ONE, BigDecimal.ONE));
    }

    @Test
    void quandoValorSaqueMaiorQueSaldo_deveRetornarExceptionAoTentarSacar() {
        var contas = criarContas();
        BigDecimal valorSaque = new BigDecimal(100);
        contas.setLimiteSaqueDiario(new BigDecimal(1000));
        when(contaRepository.findByIdContaAndFlagAtivoTrue(anyLong())).thenReturn(Optional.of(contas));
        assertThrows(ContaException.class, () -> contaService.saqueConta(1L, valorSaque , BigDecimal.ZERO));
    }

    @Test
    void quandoLimiteSaqueDiarioMenorQueValorSaque_deveRetornarExceptionAoTentarSacar() {
        var contas = criarContas();
        BigDecimal valorSaqueUtilizado = new BigDecimal(999);
        contas.setLimiteSaqueDiario(new BigDecimal(1000));
        when(contaRepository.findByIdContaAndFlagAtivoTrue(anyLong())).thenReturn(Optional.of(contas));
        assertThrows(ContaException.class, () -> contaService.saqueConta(1L, BigDecimal.TEN , valorSaqueUtilizado));
    }

    @Test
    void quandoContaExistir_deveBloquear() {
        when(contaRepository.updateFlagAtivoFalse(anyLong())).thenReturn(1);
        contaService.bloquearConta(1L);

        verify(contaRepository, times(1)).updateFlagAtivoFalse(1L);
    }

    @Test
    void quandoContaNaoExistir_deveRetornarExceptionAoTentarBloquear() {
        when(contaRepository.updateFlagAtivoFalse(anyLong())).thenReturn(0);
        assertThrows(ContaException.class, () -> contaService.bloquearConta(1L));
    }

    private Contas criarContas() {
        return new Contas(new Pessoas("Teste", "1234",
                LocalDate.now()), BigDecimal.TEN,
                BigDecimal.TEN, 1);
    }
}