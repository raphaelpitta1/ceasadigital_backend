package com.ceasa.digital.Repository;



import java.util.Collection;
import java.util.List;
import java.util.Optional;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceasa.digital.Model.userAdressModel;



@Repository
public interface userAdressRepository extends JpaRepository<userAdressModel,Integer>{
    

    List<Optional<userAdressModel>> findByidUsuario(int idUsuario);

    List<Optional<userAdressModel>> findByidUsuarioAndNumeroAndCep(int idUsuario, int numero, String cep);


    @Query("select distinct a from Enderecos a, Oferta b where a.idUsuario = b.idVendedor")
    List<Optional<userAdressModel>> findEndProdutores();

  

  
  
    
}
