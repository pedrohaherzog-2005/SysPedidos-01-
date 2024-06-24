package br.com.itilh.bdpedidos.sistemapedidos.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import br.com.itilh.bdpedidos.sistemapedidos.model.Municipios;
import br.com.itilh.bdpedidos.sistemapedidos.repository.MunicipiosRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class MunicipiosController {

    @SuppressWarnings("rawtypes")
    private final MunicipiosRepository repositorio;

    public MunicipiosController(@SuppressWarnings("rawtypes") MunicipiosRepository repositorio){
        this.repositorio = repositorio;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/municipios")
    public List<Municipios> getMunicipios() {
        return  (List<Municipios>) repositorio.findAll();
    }

    /*@SuppressWarnings("unchecked")
    @GetMapping("/municipios/estado/{id}")
    public List<Municipios> getMunicipiosPorEstadoId(@PathVariable BigInteger id) {
        return  (List<Municipios>) repositorio.findByEstadoId(id);
    }*/

    /*@SuppressWarnings("unchecked")
    @GetMapping("/municipios/estado/{nome}")
    public List<Municipios> getMunicipiosPorEstadoNome(@PathVariable String nome) {
        return repositorio.findByEstadoNomeIgnoreCase(nome);
    }*/

    @SuppressWarnings("unchecked")
    @GetMapping("/municipios/{id}")
    public Municipios getMunicipiosPorId(@PathVariable BigInteger id) throws Throwable {
        return (Municipios) repositorio.findById(id).orElseThrow(()-> new Exception("Id não encontrado"));
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/municipio")
    public Municipios postMunicipios(@RequestBody Municipios entity) throws Exception {
        try{
            return (Municipios) repositorio.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível criar o município." + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @PutMapping("municipios/{id}")
    public Municipios putMunicipios(@PathVariable BigInteger id, @RequestBody Municipios entity) throws Exception{
        try{
            return (Municipios) repositorio.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível alterar o município." + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @DeleteMapping("/municipio/{id}")
    public String deleteMunicipio(@PathVariable BigInteger id) throws Exception{
        try{ 
             repositorio.deleteById(id);
             return "Excluído";
        }catch (Exception ex){
            throw new Exception("Não foi possível excluir o id informado." + ex.getMessage());
        }
    }
}
