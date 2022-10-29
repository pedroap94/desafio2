package com.pedro.accountmanager.service;

import com.pedro.accountmanager.dto.ExtratoPeriodoDTO;
import com.pedro.accountmanager.dto.TransacoesDTO;
import com.pedro.accountmanager.model.Transacoes;
import com.pedro.accountmanager.repository.TransacoesRepository;
import com.pedro.accountmanager.utils.ContasUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TransacoesServiceTest {
    @InjectMocks
    private TransacoesService transacoesService;

    @Mock
    private TransacoesRepository transacoesRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void quandoEnviarUmaContaEValor_DeveRealizarDeposito() {
        transacoesService.realizarDeposito(ContasUtils.criarContas(), BigDecimal.ONE);

        verify(transacoesRepository, times(1)).save(any());
    }

    @Test
    void quandoEnviadoContaEValor_DeveRealizarSaque() {
        transacoesService.realizarSaque(ContasUtils.criarContas(), BigDecimal.ONE);

        verify(transacoesRepository, times(1)).save(any());
        verify(transacoesRepository, times(1)).save(argThat(x ->
                x.getValor().compareTo(BigDecimal.ONE.negate()) == 0));
    }

    @Test
    void quandoEnviarIdContaParaRecuperarExtrato_DeveRetornarListaDeTransacoesDTO() {
        Map<List<Transacoes>, List<TransacoesDTO>> condicoes = new HashMap<>();
        List<Transacoes> listaTransacoes = List.of(new Transacoes(ContasUtils.criarContas(), BigDecimal.ONE));
        TransacoesDTO transacoesDTO = new TransacoesDTO();
        transacoesDTO.setValor(listaTransacoes.get(0).getValor());
        transacoesDTO.setDataTransacao(listaTransacoes.get(0).getDataTransacao());

        condicoes.put(listaTransacoes, List.of(transacoesDTO));
        condicoes.put(new LinkedList<>(), new LinkedList<>());

        for (var condicao : condicoes.entrySet()) {
            when(transacoesRepository.findAllByIdConta(any())).thenReturn(condicao.getKey());
            List<TransacoesDTO> transacoesDTOS = transacoesService.recuperarExtrato(any());
            assertEquals(condicao.getValue(), transacoesDTOS);
        }
    }

    @Test
    void quandoSolicitadoLimiteDiario_DeveRetornarValor() {
        List<Transacoes> transacoes = criarListaTransacoes();
        when(transacoesRepository.findLimiteDiarioUtilizado(any(), any())).thenReturn(transacoes);
        BigDecimal limiteDiarioUtilizado = transacoesService.limiteDiarioUtilizado(1L);
        assertEquals(transacoes.stream()
                        .map(Transacoes::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                limiteDiarioUtilizado);
    }

    @Test
    void quandoSolicitarExtratoPorPeriodo_deveRetornarListTransacoesDTO() {
        List<Transacoes> transacoes = criarListaTransacoes();
        when(transacoesRepository.findTransacoesPeriodo(anyLong(), any(), any())).thenReturn(transacoes);
        List<TransacoesDTO> resultado = transacoesService.recuperarExtratoPeriodo(criarExtratoPeriodoDTO());
        assertEquals(toDTO(transacoes), resultado);
    }

    private List<Transacoes> criarListaTransacoes() {
        Transacoes transacoes1 = new Transacoes(ContasUtils.criarContas(), BigDecimal.ONE);
        Transacoes transacoes2 = new Transacoes(ContasUtils.criarContas(), BigDecimal.ONE);
        return List.of(transacoes1, transacoes2);
    }

    private ExtratoPeriodoDTO criarExtratoPeriodoDTO() {
        ExtratoPeriodoDTO extratoPeriodoDTO = new ExtratoPeriodoDTO();
        extratoPeriodoDTO.setDataFinal(LocalDate.now());
        extratoPeriodoDTO.setDataInicial(LocalDate.now());
        extratoPeriodoDTO.setIdConta(1L);
        return extratoPeriodoDTO;
    }

    private List<TransacoesDTO> toDTO(List<Transacoes> transacoes) {
        return transacoes.stream()
                .map(transacao -> modelMapper.map(transacao, TransacoesDTO.class)).toList();
    }
}