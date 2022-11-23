package com.ceasa.digital.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceasa.digital.Model.negociacaoModel;

public interface negociacaoRepository extends JpaRepository<negociacaoModel, Integer> {

    Optional<negociacaoModel> findByidVenda(int idVenda);
    
}
