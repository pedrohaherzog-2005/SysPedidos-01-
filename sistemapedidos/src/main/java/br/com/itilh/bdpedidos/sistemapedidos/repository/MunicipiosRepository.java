package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
//import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import br.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import br.com.itilh.bdpedidos.sistemapedidos.model.Municipios;

@Repository
public interface MunicipiosRepository<municipio> extends CrudRepository<Municipios, BigInteger> {

    //List<Estado> findByEstadoId(BigInteger id);
    //List<Estado> findByEstadoNomeIgnoreCase(String nome);

    /*// Select * From tb_municipios where tx_nome = 'nome' //
    List<Municipios> findByNome(String nome);

    // Select * From tb_municipios where UPPER(tx_nome) like UPPER('nome%') //
    List<Municipios> findByNomeStartingWithIgnoreCase(String nome);

    // Select * From tb_municipios where UPPER(tx_nome) like UPPER('%nome") //
    List<Municipios> findByNomeEndingWithIgnoreCase(String nome);

    // Select * From tb_municipios where UPPER(tx_nome) like UPPER('%nome%') //
    List<Municipios> findByNomeContainingIgnoreCase(String nome);

    @Query("FROM Municipios e WHERE e.nome like %?1")
    List<Municipios> findByMinhaQuery(String nome);*/
}
