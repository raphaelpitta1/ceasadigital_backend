package com.ceasa.digital.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceasa.digital.Model.vendaModel;

@Repository
public interface vendaRepository extends JpaRepository<vendaModel, Integer> {

    List<Optional<vendaModel>> findByidVendedor(int idVendedor);

    List<Optional<vendaModel>> findByidComprador(int idComprador);

    Optional<vendaModel> findByIdCompradorAndIdOfertaAndVendaStatus(int idComprador, int idOferta, String Status);
    
}
