package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

  @Mock
  private UsuarioRepository repository;

  @InjectMocks
  private UsuarioService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Deve retornar utilizador quando o ID existir")
  void deveRetornarUsuarioQuandoExistir() {
    // Cenário
    when(repository.buscarPorId(1L)).thenReturn(new Usuario("João"));

    // Ação
    Usuario resultado = service.buscarUsuario(1L);

    // Verificação
    assertNotNull(resultado);
    assertEquals("João", resultado.getNome());
    verify(repository, times(1)).buscarPorId(1L);
  }

  @Test
  @DisplayName("Deve retornar null quando o utilizador não existir (Exercício)")
  void deveRetornarNullQuandoUsuarioNaoExistir() {
    // Cenário
    when(repository.buscarPorId(99L)).thenReturn(null);

    // Ação
    Usuario resultado = service.buscarUsuario(99L);

    // Verificação
    assertNull(resultado);
    verify(repository, times(1)).buscarPorId(99L);
  }

  @Test
  @DisplayName("Deve salvar o utilizador com sucesso (Exercício)")
  void deveSalvarUsuarioComSucesso() {
    // Cenário
    Usuario usuario = new Usuario("Maria");

    // Ação
    service.salvarUsuario(usuario);

    // Verificação
    verify(repository, times(1)).salvar(usuario);
  }
}