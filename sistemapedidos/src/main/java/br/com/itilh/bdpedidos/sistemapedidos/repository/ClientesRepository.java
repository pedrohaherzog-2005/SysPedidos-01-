package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
//import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Clientes;
//import br.com.itilh.bdpedidos.sistemapedidos.model.Municipios;

@Repository
public interface ClientesRepository extends CrudRepository<Clientes, BigInteger> {
    
    //List<Municipios> findByMunicipiosId(BigInteger id);
    //List<Municipios> findByMunicipiosNomeIgnoreCase(String nome);

    /*// Select * From tb_clientes where tx_nome = 'nome' //
    List<Clientes> findByNomeRazaoSocial(String nome);

    // Select * From tb_clientes where UPPER(tx_nome) like UPPER('nome%') //
    List<Clientes> findByNomeRazaoSocialStartingWithIgnoreCase(String nome);

    // Select * From tb_clientes where UPPER(tx_nome) like UPPER('%nome") //
    List<Clientes> findByNomeRazaoSocialEndingWithIgnoreCase(String nome);

    // Select * From tb_clientes where UPPER(tx_nome) like UPPER('%nome%') //
    List<Clientes> findByNomeRazaoSocialContainingIgnoreCase(String nome);

    @Query("FROM Clientes e WHERE e.nome like %?1")
    List<Clientes> findByMinhaQuery(String nome);*/
}
