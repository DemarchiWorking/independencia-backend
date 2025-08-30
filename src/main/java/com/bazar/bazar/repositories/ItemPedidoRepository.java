package com.bazar.bazar.repositories;

import com.bazar.bazar.model.ItemPedido;

import jakarta.websocket.server.PathParam;

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
    
   
    @Query("SELECT ip FROM ItemPedido ip " +
           "JOIN FETCH ip.pedido p " +
           "JOIN FETCH p.cliente " +
           "JOIN FETCH p.vendedor " +
           "JOIN FETCH ip.produto pr " +
        //   "JOIN FETCH pr.autor " +
           "WHERE p.vendedor.id = :vendedorId") // Acessando a propriedade 'id' de vendedor em pedido
    List<ItemPedido> buscarPorVendedorId(@Param("vendedorId") UUID vendedorId);

       
    @Query("SELECT ip FROM ItemPedido ip " +
           "JOIN FETCH ip.pedido p " +
           "JOIN FETCH p.cliente " +
           "JOIN FETCH p.vendedor " +
           "JOIN FETCH ip.produto pr " +
        //   "JOIN FETCH pr.autor " +
           "WHERE p.cliente.id = :clienteId") // Acessando a propriedade 'id' de vendedor em pedido
        List<ItemPedido> buscarPorCompradorId(@Param("clienteId") UUID clienteId);
}
