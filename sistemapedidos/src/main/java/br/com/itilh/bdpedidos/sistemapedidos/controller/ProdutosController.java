package br.com.itilh.bdpedidos.sistemapedidos.controller;

import br.com.itilh.bdpedidos.sistemapedidos.model.Produtos;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ProdutosRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class ProdutosController {
    private final ProdutosRepository repositorio;

    public ProdutosController(ProdutosRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/produtos")
    public List<Produtos> getProdutoTodos() {
        return (List<Produtos>) repositorio.findAll();
    }

    @GetMapping("/produto/{id}")
    public Produtos getProdutosPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(()-> new Exception("Id não encontrado"));
    }

    @PostMapping("/produto")
    public Produtos AdicinarProduto(@RequestBody Produtos entity) throws Exception {
        try {
            return repositorio.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível adicionar o produto no banco de dados." + ex.getMessage());
        }
    }

    @PutMapping("produto/{id}")
    public Produtos AlterarProduto(@PathVariable BigInteger id, @RequestBody Produtos novosDados) throws Exception {
        Optional<Produtos> produtoArmazenado = repositorio.findById(id);
        if(produtoArmazenado.isPresent()) {
            //Atribuir novo nome aos objetos que já existem no banco de dados
            produtoArmazenado.get().setDescricao(novosDados.getDescricao());

            return repositorio.save(produtoArmazenado.get());
        }
        throw new Exception("Alteração não realizada");
    }

    @DeleteMapping("produto/{id}")
    public String deleteProdutoPorId(@PathVariable BigInteger id) throws Exception {
        Optional<Produtos> produtoArmazenado = repositorio.findById(id);
        if(produtoArmazenado.isPresent()) {
            repositorio.delete(produtoArmazenado.get());
            return "Excluído";
        }
        throw new Exception("Id não localizado");
    }
}
