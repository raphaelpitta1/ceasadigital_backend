package com.ceasa.digital.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.ceasa.digital.Model.produtoModel;


@Repository
public interface produtoRepository extends JpaRepository<produtoModel,Integer>{


    Optional<produtoModel> findByNome(String nome);
  
    
    
}
