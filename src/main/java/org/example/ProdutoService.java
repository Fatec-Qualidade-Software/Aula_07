package org.example;

public class ProdutoService {
  private final ProdutoRepository produtoRepository;
  private final EstoqueClient estoqueClient;

  public ProdutoService(ProdutoRepository produtoRepository, EstoqueClient estoqueClient) {
    this.produtoRepository = produtoRepository;
    this.estoqueClient = estoqueClient;
  }

  public void reservarEstoque(Long produtoId, int quantidade) {
    estoqueClient.reservar(produtoId, quantidade);
  }
}