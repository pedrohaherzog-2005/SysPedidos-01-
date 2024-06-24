package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import br.com.itilh.bdpedidos.sistemapedidos.repository.EstadoRepository;
import br.com.itilh.bdpedidos.sistemapedidos.util.ModoBusca;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
public class EstadoController {

    private final EstadoRepository repositorio;

    public EstadoController(EstadoRepository repositorio){
        this.repositorio = repositorio;
    }

    @GetMapping("/estados")
    public List<Estado> getTodos() {
        return  (List<Estado>) repositorio.findAll();
    }

    @GetMapping("/estados/nome/{nome}")
    public List<Estado> getEstadoPorNome(@PathVariable String nome, @RequestParam(required = true) ModoBusca modoBusca) {
        if(modoBusca.equals(ModoBusca.EXATO)) {
            return repositorio.findByNome(nome);
        }else if (modoBusca.equals(ModoBusca.INICIADO)) {
            return repositorio.findByNomeStartingWithIgnoreCase(nome);
        }else if (modoBusca.equals(ModoBusca.FINALIZADO)) {
            return repositorio.findByNomeEndingWithIgnoreCase(nome);
        }else {
            return repositorio.findByNomeContainingIgnoreCase(nome);
        }
    }

    @GetMapping("/estado/{id}")
    public Estado getPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow( 
            () -> new Exception("ID inválido")
        );
    }

  /*@GetMapping("estado/{id}")
    public ResponseEntity<Estado> getEstadoPorId(@PathVariable BigInteger id) {
        Optional<Estado> estado = repositorio.findById(id);
        return ResponseEntity.ok(estado.get());
        return ResponseEntity.badRequest();

    }*/
    

    @PostMapping("/estado")
    public Estado criarEstado(@RequestBody Estado entity) throws Exception { 
        try{
            return repositorio.save(entity);
        }catch(Exception e){
            throw new Exception("Erro ao salvar o estado");
        }
    }

    @PutMapping("estado/{id}")
    public Estado alterarEstado(@PathVariable BigInteger id, @RequestBody Estado novosDados) throws Exception {
        Optional<Estado> estadoArmazenado = repositorio.findById(id);
        if(estadoArmazenado.isPresent()) {
            //Atribuir novo nome aos objetos que já existem no banco de dados
            estadoArmazenado.get().setNome(novosDados.getNome());
            //
            return repositorio.save(estadoArmazenado.get());
        }

        throw new Exception("Alteração não realizada");
    }

    @DeleteMapping("estado/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {
        Optional<Estado> estadoArmazenado = repositorio.findById(id);
        if(estadoArmazenado.isPresent()) {
            repositorio.delete(estadoArmazenado.get());
            return "Excluído";
        }
        throw new Exception("Id não localizado");
    }
}
