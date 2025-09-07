package com.bazar.bazar.repositories;

import com.bazar.bazar.model.ItemPedido;

import jakarta.websocket.server.PathParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {

    List<ItemPedido> findByVendedor(UUID vendedorId);


    //@Query("SELECT ip FROM ItemPedido ip WHERE ip.vendedor = :vendedorId")
    //List<ItemPedido> buscarPorVendedor(@PathParam("vendedorId") UUID vendedorId);
    
   
  @Query(value = "SELECT ip FROM ItemPedido ip " +
            "JOIN FETCH ip.pedido p " +
            "JOIN FETCH p.cliente " +
            "JOIN FETCH p.vendedor " +
            "JOIN FETCH ip.produto pr " +
            "WHERE p.vendedor.id = :vendedorId",
            countQuery = "SELECT COUNT(ip) FROM ItemPedido ip WHERE ip.pedido.vendedor.id = :vendedorId")
    Page<ItemPedido> buscarPorVendedorId(@Param("vendedorId") UUID vendedorId, Pageable pageable);

    @Query(value = "SELECT ip FROM ItemPedido ip " +
            "JOIN FETCH ip.pedido p " +
            "JOIN FETCH p.cliente " +
            "JOIN FETCH p.vendedor " +
            "JOIN FETCH ip.produto pr " +
            "WHERE p.cliente.id = :clienteId",
            countQuery = "SELECT COUNT(ip) FROM ItemPedido ip WHERE ip.pedido.cliente.id = :clienteId")
    Page<ItemPedido> buscarPorCompradorId(@Param("clienteId") UUID clienteId, Pageable pageable);
}
