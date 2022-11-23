package com.ceasa.digital.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;


import com.ceasa.digital.Model.unMedidaModel;


@Repository
public interface unMedidaRepository extends JpaRepository<unMedidaModel,Integer>{


    Optional<unMedidaModel> findByNome(String nome);
  

    
}
