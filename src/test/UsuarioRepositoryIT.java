package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
class UsuarioRepositoryIT {

  @Container
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

  @BeforeAll
  static void setup() {
    // Obtém a URL dinâmica atribuída pelo container e aplica na aplicação
    String jdbcUrl = postgres.getJdbcUrl();
    String username = postgres.getUsername();
    String password = postgres.getPassword();

    // Aqui liga o seu DataSource ou EntityManager ao container
  }

  @Test
  void deveSalvarUsuarioNoBanco() {
    Usuario usuario = new Usuario("Maria");
    // Exemplo de chamada de persistência
    // repository.salvar(usuario);

    // Usuario salvado = repository.buscarPorId(usuario.getId());
    // assertEquals("Maria", salvado.getNome());
  }
}