package com.ceasa.digital.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceasa.digital.Model.ofertaModel;
@Repository
public interface ofertaRepository extends JpaRepository<ofertaModel,Integer> {
    
    List<Optional<ofertaModel>> findByidVendedor(int idVendedor);

    List<Optional<ofertaModel>> findByidProduto(int idProduto);

}
