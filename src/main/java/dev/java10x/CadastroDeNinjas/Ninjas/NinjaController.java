package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Pegar informacoes
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota!";
    }


    // Criar ninja (Create)
    @PostMapping("/criar")
    public NinjaModel criar(@RequestBody NinjaModel ninja) {
        return ninjaService.criarNinja(ninja);
    }

    // Mostrar Ninja por ID (Read)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Mostrar todos os ninjas (Read)
    @GetMapping("/listar/{id}")
    public Optional<NinjaModel> listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    // Alterar dados dos ninjas (Update)
    @PutMapping("/alterarID")
    public String alterarNinjaPorId() {
        return "Alterar Ninjas";
    }


    // Deletar Ninja (Delete)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId() {
        return "Ninja deletado por ID";
    }

}
