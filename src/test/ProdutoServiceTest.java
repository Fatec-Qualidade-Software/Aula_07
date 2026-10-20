package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

  @Mock
  private ProdutoRepository produtoRepository;

  @Mock
  private EstoqueClient estoqueClient;

  @InjectMocks
  private ProdutoService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Deve reservar estoque com sucesso quando houver quantidade disponível")
  void deveReservarEstoqueComSucesso() {
    // Cenário: Nenhuma exceção é lançada pelo client de estoque
    doNothing().when(estoqueClient).reservar(1L, 10);

    // Ação & Verificação: Não deve lançar nenhuma exceção
    assertDoesNotThrow(() -> service.reservarEstoque(1L, 10));
    verify(estoqueClient, times(1)).reservar(1L, 10);
  }

  @Test
  @DisplayName("Deve falhar e lançar exceção quando o estoque for insuficiente (Desafio)")
  void deveFalharQuandoEstoqueInsuficiente() {
    // Cenário: Simula falha/exceção na chamada HTTP/Client
    doThrow(new EstoqueInsuficienteException())
        .when(estoqueClient).reservar(anyLong(), anyInt());

    // Ação & Verificação
    assertThrows(EstoqueInsuficienteException.class, () -> {
      service.reservarEstoque(1L, 100);
    });
// Alteração para validação de PR e esteira de CI
    verify(estoqueClient, times(1)).reservar(1L, 100);
  }
}