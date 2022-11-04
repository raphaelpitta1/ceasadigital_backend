package com.ceasa.digital.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.ceasa.digital.Model.userModel;

@Repository
public interface userRepository extends JpaRepository<userModel,Integer>{


    Optional<userModel> findByDocumento(String documento);
  

    
}
