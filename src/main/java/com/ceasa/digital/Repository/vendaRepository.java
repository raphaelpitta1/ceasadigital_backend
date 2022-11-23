package com.ceasa.digital.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceasa.digital.Model.vendaModel;

@Repository
public interface vendaRepository extends JpaRepository<vendaModel, Integer> {

    Optional<vendaModel> findByidVendedor(int idVendedor);

    Optional<vendaModel> findByidComprador(int idComprador);

    Optional<vendaModel> findByIdCompradorAndIdOfertaAndVendaStatus(int idComprador, int idOferta, String Status);
    
}
