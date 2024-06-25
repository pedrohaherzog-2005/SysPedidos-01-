package br.com.itilh.bdpedidos.sistemapedidos.controller;

import br.com.itilh.bdpedidos.sistemapedidos.model.Clientes;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ClientesRepository;
//import br.com.itilh.bdpedidos.sistemapedidos.util.ModoBusca;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;






@RestController
public class ClientesController {
    private final ClientesRepository repositorio;

    public ClientesController(ClientesRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/clientes")
    public List<Clientes> getClientesTodos() {
        return (List<Clientes>) repositorio.findAll();
    }

    /*@GetMapping("/clientes/municipios/{nome}")
    public List<Clientes> getClientePorNome(@PathVariable String nomeRazaoSocial, @RequestParam String param (required = true) ModoBusca ModoBusca) {
        if(modoBusca.equals(ModoBusca.EXATO)) {
            return repositorio.findByNomeRazaoSocial(nomeRazaoSocial);
        }else if (modoBusca.equals(ModoBusca.INICIADO)) {
            return repositorio.findByNomeRazaoSocialStartingWithIgnoreCase(nomeRazaoSocial);
        }else if (modoBusca.equals(ModoBusca.FINALIZADO)) {
            return repositorio.findByNomeRazaoSocialEndingWithIgnoreCase(nomeRazaoSocial);
        }else {
            return repositorio.findByNomeRazaoSocialContainingIgnoreCase(nomeRazaoSocial);
        }
    }*/
    

    @GetMapping("/cliente/{id}")
    public Clientes getClientesPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(()-> new Exception("Id não encontrado"));
    }

    @PostMapping("/cliente")
    public Clientes AdicinarClientes(@RequestBody Clientes entity) throws Exception {
        try {
            return repositorio.save(entity);
        }catch (Exception ex) {
            throw new Exception("Não foi possível incluir o cliente no banco de dados." + ex.getMessage());
        }
    }

    @PutMapping("cliente/{id}")
    public Clientes AlterarCliente(@PathVariable BigInteger id, @RequestBody Clientes novosDados) throws Exception {
        Optional<Clientes> clienteArmazenado = repositorio.findById(id);
        if(clienteArmazenado.isPresent()) {
            //Atribuir novo nome aos objetos que já existem no banco de dados
            clienteArmazenado.get().setNomeRazaoSocial(novosDados.getNomeRazaoSocial());

            return repositorio.save(clienteArmazenado.get());
        }
        throw new Exception("Alteração não realizada");
    }

    @DeleteMapping("cliente/{id}")
    public String deleteClientePorId(@PathVariable BigInteger id) throws Exception {
        Optional<Clientes> clienteArmazenado = repositorio.findById(id);
        if(clienteArmazenado.isPresent()) {
            repositorio.delete(clienteArmazenado.get());
            return "Excluído";
        }
        throw new Exception("Id não localizado");
    }
}
